package uk.co.automatictester.wiremock.maven.plugin;

import org.testng.annotations.Test;

public class WireMockServerRunnerWrapperTest {

    @Test
    public void testRun() {
        WireMockServerRunnerWrapper wireMock = WireMockServerRunnerWrapper.getInstance();
        wireMock.run();
        wireMock.stop();
        wireMock.run();
        wireMock.stop();
    }
}
