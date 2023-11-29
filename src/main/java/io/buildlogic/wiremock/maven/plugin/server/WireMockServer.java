package io.buildlogic.wiremock.maven.plugin.server;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

public class WireMockServer {

    private static final WireMockServer INSTANCE = new WireMockServer();
    private static final WireMockServerRunner WIREMOCK = new WireMockServerRunner();

    private WireMockServer() {
    }

    public static WireMockServer getInstance() {
        return INSTANCE;
    }

    public synchronized void run(String... params) {
        WIREMOCK.run(params);
    }

    public synchronized void stop() {
        WIREMOCK.stop();
    }
}
