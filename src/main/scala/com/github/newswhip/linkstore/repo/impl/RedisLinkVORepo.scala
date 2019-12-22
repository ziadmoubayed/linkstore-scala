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
  val REDIS_MAP_KEY = Constants.REDIS_MAP_KEY
  val REDIS_HOST = Constants.REDIS_HOST;
  val REDIS_PORT: Int = Constants.REDIS_PORT;


  override def addLink(linkVO: LinkVO) = {
    Try(new Jedis(REDIS_HOST, REDIS_PORT).hset(REDIS_MAP_KEY, linkVO.url, linkVO.score.toString)) match {
      case Success(value) => logger.trace("Success")
      case Failure(exception) => logger.error("Error Adding Link", exception)
    }
  }

  override def removeLink(linkVO: LinkVO) = {
    Try(new Jedis(REDIS_HOST, REDIS_PORT).hdel(REDIS_MAP_KEY, linkVO.url)) match {
      case Success(value) => logger.trace("Success")
      case Failure(exception) => logger.error("Error Removing Link", exception)
    }
  }

  override def getLinks: Set[LinkVO] = {
    Try(new Jedis(REDIS_HOST, REDIS_PORT).hgetAll(REDIS_MAP_KEY)) match {
      case Success(value) => value.asScala.map({ case (k: String, v: String) => new LinkVO(k, v.toLong) }).toSet
      case Failure(exception) => {
        logger.error("Error Retrieving Links", exception)
        Set()
      }
    }
  }

  override def deleteAll() = {
    Try(new Jedis(REDIS_HOST, REDIS_PORT).del(REDIS_MAP_KEY)) match {
      case Success(value) => logger.trace("Success")
      case Failure(exception) => logger.error("Error flushing redis database", exception)
    }
  }
}
