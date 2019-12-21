package com.github.newswhip.linkstore.repo

import com.github.newswhip.linkstore.LinkVO

trait LinkVORepository {
  def addLink(linkVO: LinkVO)
  def removeLink(linkVO: LinkVO)
  def getLinks: Set[LinkVO]
  def deleteAll()
}
