package io.buildlogic.wiremock.maven.plugin;

import org.testng.annotations.Test;
import io.buildlogic.wiremock.maven.plugin.server.WireMockServer;

public class WireMockServerTest {

    @Test
    public void testRun() {
        WireMockServer wireMock = WireMockServer.getInstance();
        for (int i = 0; i < 2; i++) {
            wireMock.run();
            wireMock.stop();
        }
    }
}
