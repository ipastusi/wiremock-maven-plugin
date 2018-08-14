package uk.co.automatictester.wiremock.maven.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class ClasspathAdmin {

    private PluginDescriptor descriptor;
    private List<String> classpathElements;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    void setDescriptor(PluginDescriptor pluginDescriptor) {
        this.descriptor = pluginDescriptor;
    }

    void setClasspathElements(List<String> classpathElements) {
        this.classpathElements = classpathElements;
    }

    void addRuntimeDependenciesToClasspath() throws MojoExecutionException {
        ClassRealm realm = descriptor.getClassRealm();

        for (String classpathElement : classpathElements) {
            String message = String.format("Adding %s to wiremock-maven-plugin classpath", classpathElement);
            log.debug(message);
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
}
