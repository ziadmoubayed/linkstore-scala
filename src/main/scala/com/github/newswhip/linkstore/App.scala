package com.github.newswhip.linkstore

import java.util.Scanner

import com.github.newswhip.linkstore.cli.LinkStore
import picocli.CommandLine

object App {
  var keepRunning = true

  Runtime.getRuntime.addShutdownHook(new Thread(() => keepRunning = false))

  def main(args: Array[String]): Unit = {
    val input = new Scanner(System.in).useDelimiter("\n") // So that prompt shows once each time
    new CommandLine(new LinkStore).execute("--help".split(" "): _*)
    val shPrompt = "cli> "
    print(shPrompt)
    while (keepRunning) {
      input.hasNext match {
        case true => {
          val cmd = input.next
          new CommandLine(new LinkStore).execute(cmd.split(" "): _*)
          print(shPrompt)
        }
      }
    }
  }
}