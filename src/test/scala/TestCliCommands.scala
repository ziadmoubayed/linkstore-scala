import com.github.newswhip.linkstore.cli.LinkStore
import com.github.newswhip.linkstore.service.LinkScoreService
import org.scalatest.{BeforeAndAfter, FlatSpec}
import picocli.CommandLine

class TestCliCommands extends FlatSpec with BeforeAndAfter {

  private var SAME_DOMAINS = Array[String]()
  private var DIFFERENT_DOMAINS = Array[String]()
  private var RANDOM_SCORE = 0L

  before {
    SAME_DOMAINS = Array[String]("http://google.com/23456", "https://google.com/123", "https://google.com/asdascz", "https://google.com/12adszxc")
    DIFFERENT_DOMAINS = Array[String]("https://google1.com/123", "https://google2.com/123", "https://google3.com/123", "https://google4.com/123")
    RANDOM_SCORE = Math.floor(Math.random * 1000).toLong
  }

  after {
    LinkScoreService.flushStore()
  }

  "Add Command" should "add a link when executed" in {
    SAME_DOMAINS.map(SAME_DOMAIN => "add " + SAME_DOMAIN + " " + RANDOM_SCORE)
      .foreach(cmd => new CommandLine(new LinkStore).execute(cmd.split(" "): _*))
    assert(SAME_DOMAINS.length == LinkScoreService.getDomainStats()(SAME_DOMAINS(0))._1)
  }

  "Remove Command" should "remove a link when executed" in {
    DIFFERENT_DOMAINS.map(DIFFERENT_DOMAIN => "add " + DIFFERENT_DOMAIN + " " + RANDOM_SCORE)
      .foreach(cmd => new CommandLine(new LinkStore).execute(cmd.split(" "): _*))
    assert(DIFFERENT_DOMAINS.length == LinkScoreService.getDomainStats().size)
    DIFFERENT_DOMAINS.map(DIFFERENT_DOMAIN => "remove " + DIFFERENT_DOMAIN)
      .foreach(cmd => new CommandLine(new LinkStore).execute(cmd.split(" "): _*))
    assert(0 == LinkScoreService.getDomainStats.size)
  }

  "Export Command" should "number of records equal to distinct domains" in {
    DIFFERENT_DOMAINS.map(DIFFERENT_DOMAIN => "add " + DIFFERENT_DOMAIN + " " + RANDOM_SCORE)
      .foreach(cmd => new CommandLine(new LinkStore).execute(cmd.split(" "): _*))
    assert(DIFFERENT_DOMAINS.length == LinkScoreService.getDomainStats().size)
  }
}
