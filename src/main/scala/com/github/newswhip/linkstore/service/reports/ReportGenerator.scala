package com.github.newswhip.linkstore.service.reports

trait ReportGenerator[T] {
  def getReport: List[String] = this.format(getData)

  /**
    * Abstract method to format any report generated by the getData method into a Collection of String.
    *
    * @param raw
    * @return
    */
  protected def format(raw: T): List[String] = ???

  /**
    * Can return any type of report.
    * Reports will be converted by the format method into a collection of strings.
    *
    * @return
    */
  protected def getData: T = ???
}
