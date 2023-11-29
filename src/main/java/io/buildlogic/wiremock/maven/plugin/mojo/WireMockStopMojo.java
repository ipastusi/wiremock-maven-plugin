package io.buildlogic.wiremock.maven.plugin.mojo;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import io.buildlogic.wiremock.maven.plugin.server.WireMockServer;

@Mojo(name = "stop", defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST)
public class WireMockStopMojo extends ConfigurationMojo {

    @Override
    public void execute() {
        getLog().info("Stopping WireMock...");
        WireMockServer wireMock = WireMockServer.getInstance();
        wireMock.stop();
    }
}
