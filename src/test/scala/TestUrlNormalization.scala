import com.github.newswhip.linkstore.common.CommonUtils
import org.scalatest.FlatSpec

class TestUrlNormalization extends FlatSpec {

  /**
    * URL Equality is very important to make sure we get the best aggregations
    */
  val URLS = Map("http://google.com" -> "google.com", "google.com" -> "google.com",
    "www.google.com/1234" -> "google.com/1234", "https://google.com/12341241241" -> "google.com/12341241241",
    "http://maps.google.com" -> "maps.google.com",
    "http://somedomain:8080/123123?query=hello" -> "somedomain:8080/123123?query=hello",
    "https://www.google.com/search?q=2.35+*+8&oq=2.35++*+8&aqs=chrome..69i57j6.4190j0j7&sourceid=chrome&ie=UTF-8" -> "google.com/search?q=2.35+*+8&oq=2.35++*+8&aqs=chrome..69i57j6.4190j0j7&sourceid=chrome&ie=UTF-8")

  "urls" should "be normalized" in {
    URLS.foreach({ case (k, v) => assert(CommonUtils.normalizeURL(k) == v) })
  }
}
