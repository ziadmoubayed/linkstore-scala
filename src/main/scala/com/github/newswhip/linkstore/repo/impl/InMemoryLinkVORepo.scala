package com.github.newswhip.linkstore.repo.impl

import com.github.newswhip.linkstore.LinkVO
import com.github.newswhip.linkstore.repo.LinkVORepository

class InMemoryLinkVORepo extends LinkVORepository {
  private var store = Set[LinkVO]()

  override def addLink(linkVO: LinkVO) = {
    store = store + (linkVO)
  }

  override def removeLink(linkVO: LinkVO) = {
    store = store - (linkVO)
  }

  override def getLinks: Set[LinkVO] = {
    println(store)
    store
  }

  override def deleteAll() = {
    store = Set()
  }
}
