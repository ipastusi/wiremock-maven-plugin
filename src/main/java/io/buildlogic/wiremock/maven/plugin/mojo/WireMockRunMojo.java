package io.buildlogic.wiremock.maven.plugin.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import io.buildlogic.wiremock.maven.plugin.server.WireMockServer;
import io.buildlogic.wiremock.maven.plugin.util.ClasspathUtil;

import java.util.Arrays;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class WireMockRunMojo extends ConfigurationMojo {

    @Override
    public void execute() throws MojoExecutionException {
        if (shouldSkip()) {
            getLog().info("Requested to skip WireMock Maven Plugin execution");
            return;
        }

        addRuntimeDependenciesToClasspath();

        String[] rawWireMockParams = getAllParams();
        String wireMockParams = getFormattedStringFrom(rawWireMockParams);

        String startMessage = String.format("Starting WireMock with following params: %s", wireMockParams);
        getLog().info(startMessage);
        WireMockServer wireMock = WireMockServer.getInstance();
        wireMock.run(rawWireMockParams);

        if (shouldKeepRunning()) {
            keepRunningUntilInterrupted();
        }
    }

    private void addRuntimeDependenciesToClasspath() throws MojoExecutionException {
        ClasspathUtil classpathUtil = new ClasspathUtil();
        classpathUtil.setClasspathElements(classpathElements);
        classpathUtil.setDescriptor(descriptor);
        classpathUtil.addRuntimeDependenciesToClasspath();
    }

    private String getFormattedStringFrom(String[] array) {
        return Arrays.toString(array).replaceAll("[\\[\\]]", "").replaceAll(", ", " ");
    }

    private void keepRunningUntilInterrupted() {
        getLog().info("WireMock will keep running until interrupted manually...");
        while (true) {
            sleepForOneSecond();
        }
    }

    private void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            getLog().error(e.getMessage());
        }
    }
}
