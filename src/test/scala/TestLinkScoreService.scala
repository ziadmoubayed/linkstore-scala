import com.github.newswhip.linkstore.common.CommonUtils
import com.github.newswhip.linkstore.service.LinkScoreService
import org.scalatest.{BeforeAndAfter, FlatSpec}

class TestLinkScoreService extends FlatSpec with BeforeAndAfter {
  private var SAME_DOMAINS = Array[String]()
  private var DIFFERENT_DOMAINS = Array[String]()
  private var RANDOM_SCORE = 0L

  before {
    LinkScoreService.flushStore()
    SAME_DOMAINS = Array[String]("http://google.com/23456", "https://google.com/123", "https://google.com/asdascz", "https://google.com/12adszxc")
    DIFFERENT_DOMAINS = Array[String]("https://google1.com/123", "https://google2.com/123", "https://google3.com/123", "https://google4.com/123")
    RANDOM_SCORE = Math.floor(Math.random * 1000).toLong
  }

  after {
    LinkScoreService.flushStore()
  }

  "adding a link" should "update the report" in {
    SAME_DOMAINS.foreach(SAME_DOMAIN => LinkScoreService.addLink(SAME_DOMAIN, RANDOM_SCORE))
    val report = LinkScoreService.getDomainStats
    assert(SAME_DOMAINS.length == report.get(CommonUtils.getDomain(SAME_DOMAINS(0))).get._1)
  }

  "scores in reports" should "accumulate for the same domain" in {
    SAME_DOMAINS.foreach(SAME_DOMAIN => LinkScoreService.addLink(SAME_DOMAIN, RANDOM_SCORE))
    val report = LinkScoreService.getDomainStats
    assert(RANDOM_SCORE * SAME_DOMAINS.length == report.get(CommonUtils.getDomain(SAME_DOMAINS(0))).get._2)
  }

  "report" should "contain a different entry for every unique domain" in {
    DIFFERENT_DOMAINS.foreach(DIFFERENT_DOMAIN => LinkScoreService.addLink(DIFFERENT_DOMAIN, RANDOM_SCORE))
    val report = LinkScoreService.getDomainStats
    assert(report.size == DIFFERENT_DOMAINS.length)
  }

  "adding a corrupted url" should "throw illegal argument exception" in {
    assertThrows[IllegalArgumentException](LinkScoreService.addLink(null, -1L))
  }
}
