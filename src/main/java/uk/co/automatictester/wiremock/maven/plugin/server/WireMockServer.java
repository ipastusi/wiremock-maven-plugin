package uk.co.automatictester.wiremock.maven.plugin.server;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

public class WireMockServer {

    private static final WireMockServer instance = new WireMockServer();
    private static final WireMockServerRunner wireMock = new WireMockServerRunner();

    private WireMockServer() {
    }

    public static WireMockServer getInstance() {
        return instance;
    }

    public synchronized void run(String... params) {
        wireMock.run(params);
    }

    public synchronized void stop() {
        wireMock.stop();
    }
}
