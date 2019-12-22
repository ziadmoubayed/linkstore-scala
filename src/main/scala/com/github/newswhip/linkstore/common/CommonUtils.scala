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
    val uri = URI.create(addHttp(urlString))
    val domain = uri.getHost
    if (domain == null) throw new IllegalArgumentException("Invalid url: " + urlString);
    if (domain.startsWith("www.")) domain.substring(4)
    else domain
  }

  /**
    * Appends http:// to header if missing
    *
    * @param urlString
    * @return
    */
  def addHttp(urlString: String): String = {
    if (!(urlString.startsWith("http://") || urlString.startsWith("https://")))
      return "http://" + urlString
    return urlString
  }


  /**
    * Returns a normalized url that can be used properly with equality
    *
    * @param urlString
    * @return
    */
  def normalizeURL(urlString: String): String = {
    val uri = URI.create(addHttp(urlString))
    var url = getDomain(urlString)
    if (uri.getPort != -1) url += (":" + uri.getPort)
    if (uri.getPath != null) url += uri.getPath
    if (uri.getRawQuery != null) url += ("?" + uri.getRawQuery)
    return url
  }
}
