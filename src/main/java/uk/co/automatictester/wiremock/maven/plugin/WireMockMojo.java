package uk.co.automatictester.wiremock.maven.plugin;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class WireMockMojo extends ConfigurationMojo {

    public void execute() throws MojoExecutionException {
        addRuntimeDependenciesToClasspath();

        String[] rawWireMockParams = getAllParams();
        String wireMockParams = getFormattedStringFrom(rawWireMockParams);

        String startMessage = String.format("Starting WireMock with following params: %s", wireMockParams);
        getLog().info(startMessage);
        WireMockServerRunner.main(rawWireMockParams);

        if (shouldKeepRunning()) {
            keepRunningUntilInterrupted();
        }
    }

    private void addRuntimeDependenciesToClasspath() throws MojoExecutionException {
        ClassRealm realm = getDescriptor().getClassRealm();

        for (String classpathElement : getClasspathElements()) {
            String message = String.format("Adding %s to wiremock-maven-plugin classpath", classpathElement);
            getLog().debug(message);
            URL classpathElementUrl = getClasspathElementFrom(classpathElement);
            realm.addURL(classpathElementUrl);
        }
    }

    private URL getClasspathElementFrom(String classpathElement) throws MojoExecutionException {
        File classpathElementFile = new File(classpathElement);
        URI classpathElementUri = classpathElementFile.toURI();
        return getClasspathElementFrom(classpathElementUri);
    }

    private URL getClasspathElementFrom(URI uri) throws MojoExecutionException {
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new MojoExecutionException("Malformed classpath URL", e);
        }
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
