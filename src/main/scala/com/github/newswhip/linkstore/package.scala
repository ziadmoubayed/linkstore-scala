package com.github.newswhip

import com.github.newswhip.linkstore.common.CommonUtils

package object linkstore {

  case class LinkVO(url: String, val score: Long = 0L) {
    def getDomain(): String = CommonUtils.getDomain(url)

    def canEqual(a: Any) = a.isInstanceOf[LinkVO]

    override def equals(that: Any): Boolean =
      that match {
        case that: LinkVO => that.canEqual(this) && this.hashCode == that.hashCode
        case _ => false
      }

    override def hashCode: Int = {
      val prime = 31
      var result = 1
      result = prime * result + (if (url == null) 0 else url.hashCode)
      return result
    }

  }

}
