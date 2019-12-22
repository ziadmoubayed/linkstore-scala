package com.github.newswhip.linkstore.common

/**
  * Constants.
  */
object Constants {
  val DATA_STORE = scala.util.Properties.envOrNone("LIVE_STORE_ENV")
  val REDIS_HOST = scala.util.Properties.envOrElse("REDIS_HOST", "localhost")
  val REDIS_PORT = scala.util.Properties.envOrElse("REDIS_PORT", "6379").toInt
  val REDIS_MAP_KEY = scala.util.Properties.envOrElse("REDIS_MAP", "myset")
  val CSV_SEPARATOR = ";"
  val CSV_HEADERS = "domain;urls;social_score"
}
