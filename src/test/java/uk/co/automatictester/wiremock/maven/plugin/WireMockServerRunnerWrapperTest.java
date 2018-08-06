package uk.co.automatictester.wiremock.maven.plugin;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WireMockServerRunnerWrapperTest {

    @Test
    public void testRun() {
        WireMockServerRunnerWrapper.run(new String[]{});
        WireMockServerRunnerWrapper.stop();
    }
}