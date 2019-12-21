package com.github.newswhip.linkstore.cli

import com.github.newswhip.linkstore.service.LinkScoreService
import picocli.CommandLine

@CommandLine.Command(name = "remove", header = Array("Remove a url from the store."))
class LinkRemove extends Runnable {
  @CommandLine.Parameters(index = "0", paramLabel = "url",
    description = Array("URL to remove"))
  var url = ""

  override def run() = {
    LinkScoreService.removeLink(url)
  }
}
