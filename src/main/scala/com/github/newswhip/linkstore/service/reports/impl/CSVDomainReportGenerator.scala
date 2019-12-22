package com.github.newswhip.linkstore.service.reports.impl

import com.github.newswhip.linkstore.common.Constants
import com.github.newswhip.linkstore.service.LinkScoreService
import com.github.newswhip.linkstore.service.reports.ReportGenerator


/**
  * Formats report into lines separated by delimiter.
  */
class CSVDomainReportGenerator extends ReportGenerator[Map[String, (Int, Long)]] {

  override protected def format(raw: Map[String, (Int, Long)]): List[String] = Constants.CSV_HEADERS :: raw.toList.map(k => k._1 + Constants.CSV_SEPARATOR + k._2._1 + Constants.CSV_SEPARATOR + k._2._2)

  override protected def getData: Map[String, (Int, Long)] = LinkScoreService.getDomainStats
}
