package com.github.newswhip.linkstore.cli

import java.io.{File, IOException}
import java.nio.file.{Files, StandardOpenOption}

import com.github.newswhip.linkstore.service.reports.impl.CSVDomainReportGenerator
import com.typesafe.scalalogging.Logger
import picocli.CommandLine

@CommandLine.Command(name = "export", header = Array("Export a report."))
class LinkExport() extends Runnable {
  lazy val logger = Logger(classOf[LinkExport])

  @CommandLine.Option(names = Array("-f", "--file"),
    description = Array("/tmp/export.csv"))
  var dest: String = ""

  val reportGenerator = new CSVDomainReportGenerator()

  override def run() = { //Generates the report
    val report = this.reportGenerator.getReport

    /**
      * If destination file not specified.
      * Print to output stream.
      */
    if (!dest.isEmpty) {
      try
        write(report, new File(dest))
      catch {
        case e: IOException =>
          logger.error("Error writing report to: " + dest, e)
      }
    }
    else print(report)
  }

  /**
    * Prints the formatted line collection, line by line.
    *
    * @param formattedReport report formatted and ready for print
    */
  private def print(formattedReport: List[String]): Unit = {
    formattedReport.foreach(println)
  }

  /**
    * Writes the lines to the destination file.
    * Fails If File already exists or directory does not exist.
    *
    * @param formattedReport report formatted and ready for write
    * @param dest            destination file
    * @throws IOException if writing fails
    */
  @throws[IOException]
  private def write(formattedReport: List[String], dest: File): Unit = {
    val content = formattedReport.mkString("\n").getBytes
    Files.write(dest.toPath, content, StandardOpenOption.CREATE_NEW)
  }
}
