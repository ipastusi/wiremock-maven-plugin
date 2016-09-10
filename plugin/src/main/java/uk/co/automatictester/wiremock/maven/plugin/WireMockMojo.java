package uk.co.automatictester.wiremock.maven.plugin;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.Arrays;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class WireMockMojo extends ConfigurationMojo {

    public void execute() throws MojoExecutionException {
        String wireMockParams = Arrays.toString(getAllParams()).replaceAll("[\\[\\]]", "").replaceAll(", ", " ");
        getLog().info("Starting WireMock with following params: " + wireMockParams);
        WireMockServerRunner.main(getAllParams());
    }
}
