import com.github.newswhip.linkstore.common.CommonUtils
import org.scalatest.FlatSpec

class TestCommonUtils extends FlatSpec {

  val SAME_DOMAINS = Array[String]("http://google.com/23456", "https://google.com/123", "https://google.com/asdascz", "https://google.com/12adszxc")
  val CORRUPTED_DOMAIN = "http:google.com"
  val DOMAIN_WITHOUT_HTTP = Array[String]("google.com", "top.google.com")

  "getDomain" should "extract only the domain from the link" in {
    SAME_DOMAINS.foreach(SAME_DOMAIN => assert(CommonUtils.getDomain(SAME_DOMAIN) == "google.com"))
  }

  "getDomain" should "fail if link is corrupted" in {
    assertThrows[IllegalArgumentException](CommonUtils.getDomain(CORRUPTED_DOMAIN))
  }

  "checkHeaders" should "add http:// if missing" in {
    DOMAIN_WITHOUT_HTTP.foreach(DOMAIN => assert(CommonUtils.addHttp(DOMAIN) == "http://" + DOMAIN))
  }
}
