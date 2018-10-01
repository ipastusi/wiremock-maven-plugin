package uk.co.automatictester.wiremock.maven.plugin.server

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner

object WireMockServer {

    private val WireMock = new WireMockServerRunner()

    def run(params: Array[String]): Unit = {
        WireMock.run(params: _*)
    }

    def stop(): Unit = {
        WireMock.stop()
    }
}
