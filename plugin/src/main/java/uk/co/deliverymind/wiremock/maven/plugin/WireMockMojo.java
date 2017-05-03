package uk.co.deliverymind.wiremock.maven.plugin;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class WireMockMojo extends ConfigurationMojo {

    public void execute() throws MojoExecutionException {
        addRuntimeDependenciesToClasspath();

        String wireMockParams = Arrays.toString(getAllParams()).replaceAll("[\\[\\]]", "").replaceAll(", ", " ");
        getLog().info("Starting WireMock with following params: " + wireMockParams);
        WireMockServerRunner.main(getAllParams());
    }

    private void addRuntimeDependenciesToClasspath() throws MojoExecutionException {
        ClassRealm realm = getDescriptor().getClassRealm();

        for (String element: getClasspathElements()) {
            getLog().info(String.format("Adding %s to wiremock-maven-plugin classpath", element));
            File elementFile = new File(element);
            try {
                realm.addURL(elementFile.toURI().toURL());
            } catch (MalformedURLException e) {
                throw new MojoExecutionException("Malformed classpath URL", e);
            }
        }
    }
}
