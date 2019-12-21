package com.github.newswhip.linkstore.repo.impl

import com.github.newswhip.linkstore.LinkVO
import com.github.newswhip.linkstore.common.Constants
import com.github.newswhip.linkstore.repo.LinkVORepository
import com.typesafe.scalalogging.Logger
import redis.clients.jedis.Jedis

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}


class RedisLinkVORepo extends LinkVORepository {

  val logger = Logger(classOf[RedisLinkVORepo])
  val REDIS_SET = Constants.REDIS_MAP


  override def addLink(linkVO: LinkVO) = {
    Try(new Jedis().hset(REDIS_SET, linkVO.url, linkVO.score.toString)) match {
      case Success(value) => logger.trace("Success")
      case Failure(exception) => logger.error("Error Adding Link", exception)
    }
  }

  override def removeLink(linkVO: LinkVO) = {
    Try(new Jedis().hdel(REDIS_SET, linkVO.url)) match {
      case Success(value) => logger.trace("Success")
      case Failure(exception) => logger.error("Error Removing Link", exception)
    }
  }

  override def getLinks: Set[LinkVO] = {
    Try(new Jedis().hgetAll(REDIS_SET)) match {
      case Success(value) => value.asScala.map({ case (k: String, v: String) => new LinkVO(k, v.toLong) }).toSet
      case Failure(exception) => {
        logger.error("Error Retrieving Links", exception)
        Set()
      }
    }
  }

  override def deleteAll() = {
    Try(new Jedis().del(REDIS_SET)) match {
      case Success(value) => logger.trace("Success")
      case Failure(exception) => logger.error("Error flushing redis database", exception)
    }
  }
}
