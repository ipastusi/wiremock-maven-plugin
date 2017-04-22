package uk.co.deliverymind.wiremock.maven.plugin;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresDependencyResolution = ResolutionScope.TEST)
public class WireMockMojo extends ConfigurationMojo {

    @Parameter(defaultValue = "${plugin}", required = true, readonly = true)
    private PluginDescriptor descriptor;

    @Parameter(property = "project.testClasspathElements", required = true, readonly = true)
    private List<String> classpathElements;

    public void execute() throws MojoExecutionException {
        loadTestClasspath();

        String wireMockParams = Arrays.toString(getAllParams()).replaceAll("[\\[\\]]", "").replaceAll(", ", " ");
        getLog().info("Starting WireMock with following params: " + wireMockParams);
        WireMockServerRunner.main(getAllParams());
    }

    private void loadTestClasspath() throws MojoExecutionException {
        ClassRealm realm = descriptor.getClassRealm();

        for (String element : classpathElements)
        {
            getLog().debug(String.format("Adding %s to Wiremock plugin classpath", element));
            File elementFile = new File(element);
            try {
                realm.addURL(elementFile.toURI().toURL());
            } catch (MalformedURLException e) {
                throw new MojoExecutionException("Malformed classpath URL", e);
            }
        }
    }
}
