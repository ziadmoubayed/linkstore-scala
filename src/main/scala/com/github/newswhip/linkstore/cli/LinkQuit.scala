package com.github.newswhip.linkstore.cli

import picocli.CommandLine

@CommandLine.Command(name = "quit", header = Array("Exits the REPL."))
class LinkQuit extends Runnable {
  override def run() = {
    println("Good Bye!")
    System.exit(0)
  }
}
