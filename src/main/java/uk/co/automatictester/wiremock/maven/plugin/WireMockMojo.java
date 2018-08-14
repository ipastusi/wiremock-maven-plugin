package uk.co.automatictester.wiremock.maven.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.util.Arrays;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class WireMockMojo extends ConfigurationMojo {

    public void execute() throws MojoExecutionException {
        addRuntimeDependenciesToClasspath();

        String[] rawWireMockParams = getAllParams();
        String wireMockParams = getFormattedStringFrom(rawWireMockParams);

        String startMessage = String.format("Starting WireMock with following params: %s", wireMockParams);
        getLog().info(startMessage);
        WireMockServerRunnerWrapper.run(rawWireMockParams);

        if (shouldKeepRunning()) {
            keepRunningUntilInterrupted();
        }
    }

    private void addRuntimeDependenciesToClasspath() throws MojoExecutionException {
        ClasspathAdmin classpathAdmin = new ClasspathAdmin();
        classpathAdmin.setClasspathElements(classpathElements);
        classpathAdmin.setDescriptor(descriptor);
        classpathAdmin.addRuntimeDependenciesToClasspath();
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
