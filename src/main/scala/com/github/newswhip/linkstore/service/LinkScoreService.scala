package com.github.newswhip.linkstore.service

import com.github.newswhip.linkstore.LinkVO
import com.github.newswhip.linkstore.common.{CommonUtils, Constants}
import com.github.newswhip.linkstore.repo.LinkVORepository
import com.github.newswhip.linkstore.repo.impl.{InMemoryLinkVORepo, RedisLinkVORepo}

object LinkScoreService {

  val linkVORepository: LinkVORepository = init

  def init = {
    Constants.DATA_STORE match {
      case Some("redis") => new RedisLinkVORepo()
      case _ => new InMemoryLinkVORepo()
    }
  }


  /**
    * Adds a link with it's score to the data store.
    *
    * @param url
    * @param score
    */
  def addLink(url: String, score: Long) = {
    if (score < 0) throw new IllegalArgumentException("Invalid score. It should be a whole number, greater or equal to zero")
    this.linkVORepository.addLink(new LinkVO(normalizeUrl(url), score))
  }

  /**
    * Removes a link from the store.
    *
    * @param url
    */
  def removeLink(url: String) = {
    this.linkVORepository.removeLink(new LinkVO(normalizeUrl(url)))
  }

  /**
    * Clears the data store.
    * This function can be dangerous.
    * But we are including it because LinkScoreService is a singleton / global variable
    * and we need a way to reset it in the same jvm.
    */
  def flushStore(): Unit = {
    this.linkVORepository.deleteAll()
  }

  /**
    * Domain Stats Report groups the links by domain aggregating the sum of stats and the count of links.
    * This report is like: select domain, count(links), sum(stats) group by domain.
    *
    * @return
    */
  def getDomainStats(): Map[String, (Int, Long)] =
    linkVORepository.getLinks.toList
      .map(link => link.getDomain() -> (1, link.score))
      .foldLeft(Map[String, (Int, Long)]().withDefaultValue((0, 0L)))((left, right) => {
        left + (right._1 -> (left(right._1)._1 + right._2._1, left(right._1)._2 + right._2._2))
      })

  def normalizeUrl(url: String) = CommonUtils.normalizeURL(url)
}
