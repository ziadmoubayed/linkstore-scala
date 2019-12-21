package com.github.newswhip.linkstore.common

import java.net.URI

object CommonUtils {

  /**
    * Returns the domain from the url
    *
    * @param urlString string url
    * @return domain name without http:// and www.
    */
  def getDomain(urlString: String): String = {
    val uri = URI.create(checkHeaders(urlString))
    val domain = uri.getHost
    if (domain.startsWith("www.")) domain.substring(4)
    else domain
  }

  /**
    * Appends http:// to header if missing
    *
    * @param urlString
    * @return
    */
  def checkHeaders(urlString: String): String = {
    if (!urlString.startsWith("http://") || urlString.startsWith("https://"))
      return "http://" + urlString
    return urlString
  }
}
