package uk.co.automatictester.wiremock.maven.plugin

import org.scalatest.{FlatSpec, Matchers}
import uk.co.automatictester.wiremock.maven.plugin.server.WireMockServer

class WireMockServerSpec extends FlatSpec {

    "WireMockServer" should "start and stop multiple times in a sequence" in {
        (0 to 1).foreach(i => {
            WireMockServer.run(Array[String]())
            WireMockServer.stop()
        })
    }
}
