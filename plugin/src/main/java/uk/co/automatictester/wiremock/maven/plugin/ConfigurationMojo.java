package uk.co.automatictester.wiremock.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

abstract public class ConfigurationMojo extends AbstractMojo {

    /**
     * Parameters, in command-line format, e.g.:
     * --root-dir=target/classes --port=8089
     */
    @Parameter(property = "params")
    protected String params;

    protected String[] getParams() {
        return params.split(" ");
    }
}
