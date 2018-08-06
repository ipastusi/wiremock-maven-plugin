package uk.co.automatictester.wiremock.maven.plugin;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "stop", defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST)
public class WireMockTerminatorMojo extends ConfigurationMojo {

    public void execute() {
        getLog().info("Stopping WireMock...");
        WireMockServerRunnerWrapper.stop();
    }
}
