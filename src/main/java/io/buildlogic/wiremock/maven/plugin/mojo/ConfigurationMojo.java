package io.buildlogic.wiremock.maven.plugin.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.Parameter;
import io.buildlogic.wiremock.maven.plugin.util.ParameterUtil;

import java.io.File;
import java.util.List;

abstract class ConfigurationMojo extends AbstractMojo {

    @Parameter(defaultValue = "${plugin}", required = true, readonly = true)
    PluginDescriptor descriptor;

    @Parameter(property = "project.testClasspathElements", required = true, readonly = true)
    List<String> classpathElements;

    /**
     * Set the root directory (<i>--root-dir</i>), under which <i>mappings</i> and <i>__files</i> reside.
     * This defaults to: <i>target/classes</i>
     */
    @Parameter(property = "wiremock.dir", defaultValue = "target/classes")
    private File dir;

    /**
     * Set all other parameters in command-line format, e.g.:
     * <i>--port=8081 --verbose</i>
     * Do <b>NOT</b> specify <i>--root-dir</i> here.
     */
    @Parameter(property = "wiremock.params")
    private String params;

    /**
     * Set to <i>true</i> if (and only if) you want to keep the plugin running indefinitely.
     */
    @Parameter(property = "wiremock.keepRunning", defaultValue = "false")
    private String keepRunning;

    /**
     * Set to <i>true</i> to skip plugin execution.
     */
    @Parameter(property = "wiremock.skip", defaultValue = "false")
    private String skip;

    boolean shouldKeepRunning() {
        return keepRunning.equals("true");
    }

    boolean shouldSkip() {
        return skip.equals("true");
    }

    String[] getAllParams() {
        String directory = dir.toString();
        if (params == null) {
            return ParameterUtil.getDirParam(directory);
        } else {
            return ParameterUtil.getAllParams(directory, params);
        }
    }
}
