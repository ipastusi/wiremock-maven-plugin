package uk.co.automatictester.wiremock.maven.plugin;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

public class WireMockServerRunnerWrapper {

    private static final WireMockServerRunnerWrapper instance = new WireMockServerRunnerWrapper();
    private static final WireMockServerRunner wireMock = new WireMockServerRunner();

    private WireMockServerRunnerWrapper() {
    }

    public static WireMockServerRunnerWrapper getInstance() {
        return instance;
    }

    public synchronized void run(String... params) {
        wireMock.run(params);
    }

    public synchronized void stop() {
        wireMock.stop();
    }
}
