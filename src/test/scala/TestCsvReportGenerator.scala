import com.github.newswhip.linkstore.common.Constants
import com.github.newswhip.linkstore.service.LinkScoreService
import com.github.newswhip.linkstore.service.reports.ReportGenerator
import com.github.newswhip.linkstore.service.reports.impl.CSVDomainReportGenerator
import org.scalatest.{BeforeAndAfter, FlatSpec}

class TestCsvReportGenerator extends FlatSpec with BeforeAndAfter {
  var reportGenerator = new CSVDomainReportGenerator()

  after {
    LinkScoreService.flushStore()
  }

  "csv reports" should "contain headers" in {
    val report = reportGenerator.getReport
    assert(report != null)
    assert(report.size > 0)
    assert(Constants.CSV_HEADERS.equals(report(0)))
  }
}
