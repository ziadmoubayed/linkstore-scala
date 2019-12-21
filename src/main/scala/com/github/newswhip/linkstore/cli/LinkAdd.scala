package com.github.newswhip.linkstore.cli

import com.github.newswhip.linkstore.service.LinkScoreService
import picocli.CommandLine

// defines some commands to show in the list (option/parameters fields omitted for this demo)
@CommandLine.Command(name = "add", header = Array("Add a url to the store."))
class LinkAdd extends Runnable {
  @CommandLine.Parameters(index = "0", paramLabel = "url", description = Array("URL to add"))
  var url = ""
  @CommandLine.Parameters(index = "1", paramLabel = "score", description = Array("Social interactions"))
  var score = 0L

  override def run() = {
    LinkScoreService.addLink(url, score)
  }
}
