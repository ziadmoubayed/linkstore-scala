import com.github.newswhip.linkstore.LinkVO
import com.github.newswhip.linkstore.repo.LinkVORepository
import com.github.newswhip.linkstore.repo.impl.InMemoryLinkVORepo
import org.scalatest.{BeforeAndAfter, FlatSpec}

class TestInMemoryRepo extends FlatSpec with BeforeAndAfter {

  val linkVORepository = new InMemoryLinkVORepo()

  var DEMO1: LinkVO = null
  var DEMO2: LinkVO = null

  before {
    DEMO1 = new LinkVO("http://google.com", 10L)
    DEMO2 = new LinkVO("http://google1.com", 10L)
  }

  after {
    linkVORepository.deleteAll();
  }

  "Adding links to the repo" should "add the link" in {
    assert(0 == linkVORepository.getLinks.size)
    linkVORepository.addLink(DEMO1)
    assert(linkVORepository.getLinks.contains(DEMO1))
    assert(1 == linkVORepository.getLinks.size)
    linkVORepository.addLink(DEMO2)
    assert(linkVORepository.getLinks.contains(DEMO2))
    assert(2 == linkVORepository.getLinks.size)
  }

  "Removing links from the repo" should "remove the link" in {
    linkVORepository.addLink(DEMO1)
    linkVORepository.addLink(DEMO2)
    assert(2 == linkVORepository.getLinks.size)
    linkVORepository.removeLink(DEMO2)
    assert(!linkVORepository.getLinks.contains(DEMO2))
    assert(1 == linkVORepository.getLinks.size)
    linkVORepository.removeLink(DEMO1)
    assert(!linkVORepository.getLinks.contains(DEMO1))
    assert(0 == linkVORepository.getLinks.size)
  }

  "Duplicates " should "be ignored" in {
    linkVORepository.addLink(DEMO1)
    linkVORepository.addLink(DEMO1)
    assert(linkVORepository.getLinks.contains(DEMO1))
    assert(linkVORepository.getLinks.size == 1)
  }
}
