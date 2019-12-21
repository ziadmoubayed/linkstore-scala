package com.github.newswhip.linkstore.cli

import picocli.CommandLine
import picocli.CommandLine.Command

@Command(name = "links", mixinStandardHelpOptions = true,
  version = Array("Link Store Version 1.0"), description = Array("Add Description"),
  subcommands = Array(classOf[LinkAdd],
    classOf[LinkRemove], classOf[LinkExport],
    classOf[CommandLine.HelpCommand],
    classOf[LinkQuit]))
class LinkStore extends Runnable {

  @Command(name = "links", mixinStandardHelpOptions = true,
    version = Array("Link Store Version 1.0"),
    description = Array("Add Description"))
  override def run() = {
    // your business logic goes here...
  }
}