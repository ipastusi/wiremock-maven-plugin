package uk.co.automatictester.wiremock.maven.plugin.mojo

import org.apache.maven.plugins.annotations.{LifecyclePhase, Mojo}
import uk.co.automatictester.wiremock.maven.plugin.server.WireMockServer

@Mojo(name = "stop", defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST)
class WireMockStopMojo extends ConfigurationMojo {

    @Override
    def execute: Unit = {
        getLog.info("Stopping WireMock...")
        WireMockServer.stop()
    }
}
