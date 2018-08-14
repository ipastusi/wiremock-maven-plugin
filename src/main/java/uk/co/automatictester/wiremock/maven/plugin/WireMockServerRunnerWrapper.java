package uk.co.automatictester.wiremock.maven.plugin;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

public class WireMockServerRunnerWrapper {

    private static WireMockServerRunner wireMockServerRunner = new WireMockServerRunner();

    public static void run(String... params) {
        wireMockServerRunner.run(params);
    }

    public static void stop() {
        wireMockServerRunner.stop();
    }
}
