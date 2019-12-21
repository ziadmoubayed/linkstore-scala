import com.github.newswhip.linkstore.LinkVO
import org.scalatest._
import org.scalatest.matchers.must.Matchers

class TestLinkVO extends FlatSpec with Matchers {

  // these first two instances should be equal
  val link1 = new LinkVO("http://google.com/123", 82)
  val link2 = new LinkVO("http://google.com/123", 82)
  val link3 = new LinkVO("http://google.com/234", 82)
  val link4 = new LinkVO("google1.com/234")

  "link1 == link1" should "return true" in assert(link1 == link1)

  // all tests pass
  "http://google.com/123 == http://google.com/123" should "be equal" in assert(link1 == link2)
  "http://google.com/123 != http://google.com/234" should "not be equal" in assert(link1 != link3)
  "http://google1.com != http://google.com" should "not be equal" in assert(link3 != link4)
  "(http://google.com/123).getDomain() != (http://google.com)/234.getDomain()" should "be equal" in {
    assert(link1.getDomain() == link3.getDomain())
  }
  "(http://google.com/234).getDomain() != (google1.com/234).getDomain()" should "not be equal" in {
    assert(link3.getDomain() != link4.getDomain())
  }
}