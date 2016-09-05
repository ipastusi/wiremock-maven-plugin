package wiremock;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class WireMockMojo extends ConfigurationMojo {

    public void execute() throws MojoExecutionException {
        WireMockServerRunner.main(getParams());
    }
}
