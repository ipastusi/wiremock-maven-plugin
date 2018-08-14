package uk.co.automatictester.wiremock.maven.plugin;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

public class WireMockServerRunnerWrapper {

    private WireMockServerRunnerWrapper() {}

    private static WireMockServerRunner instance = new WireMockServerRunner();

    public static void run(String... params) {
        instance.run(params);
    }

    public static void stop() {
        instance.stop();
    }
}
