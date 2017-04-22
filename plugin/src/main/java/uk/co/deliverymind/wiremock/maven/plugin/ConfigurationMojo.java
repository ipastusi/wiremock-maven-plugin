package uk.co.deliverymind.wiremock.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

abstract public class ConfigurationMojo extends AbstractMojo {

    /**
     * Set the root directory (<i>--root-dir</i>), under which <i>mappings</i> and <i>__files</i> reside.
     * This defaults to: <i>target/classes</i>
     */
    @Parameter(property = "dir", defaultValue = "target/classes")
    private File dir;

    /**
     * Set extension classes to be loaded (<i>--extensions</i>), for example custom matchers or response transformers.
     */
    @Parameter(property = "extensions")
    private List<String> extensions;

    /**
     * Set all other parameters in command-line format, e.g.:
     * <i>--port=8081 --verbose</i>
     * Do <b>NOT</b> specify <i>--root-dir</i> here.
     */
    @Parameter(property = "params")
    private String params;

    protected String[] getAllParams() {
        String extensionParam = "";
        if (extensions != null && !extensions.isEmpty()) {
            extensionParam = String.format(" %s%s", "--extensions=", join(",", extensions));
        }
        String allParams = String.format("%s%s%s %s", "--root-dir=", dir, extensionParam, params);
        return allParams.split(" ");
    }

    private String join(String separator, List<String> items) {
        StringBuilder b = new StringBuilder();
        for (String item: items) {
            b.append(separator).append(item);
        }
        return items.isEmpty() ? "" : b.toString().substring(separator.length());
    }
}
