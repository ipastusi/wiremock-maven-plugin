package uk.co.automatictester.wiremock.maven.plugin;

import org.testng.annotations.Test;

public class WireMockServerRunnerWrapperTest {

    @Test
    public void testRun() {
        WireMockServerRunnerWrapper.run();
        WireMockServerRunnerWrapper.stop();
    }
}
