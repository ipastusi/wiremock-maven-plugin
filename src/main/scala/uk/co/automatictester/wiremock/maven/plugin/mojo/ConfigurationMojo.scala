package uk.co.automatictester.wiremock.maven.plugin.mojo

import java.io.File
import java.util

import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugin.descriptor.PluginDescriptor
import org.apache.maven.plugins.annotations.Parameter
import uk.co.automatictester.wiremock.maven.plugin.util.ParameterUtil

abstract class ConfigurationMojo extends AbstractMojo {

    @Parameter(defaultValue = "${plugin}", required = true, readonly = true)
    var descriptor: PluginDescriptor = _

    @Parameter(property = "project.testClasspathElements", required = true, readonly = true)
    var classpathElements: util.List[String] = _

    /**
      * Set the root directory (''--root-dir''), under which ''mappings'' and ''\_\_files'' reside.
      * This defaults to: ''target/classes''
      */
    @Parameter(property = "dir", defaultValue = "target/classes")
    var dir: File = _

    /**
      * Set all other parameters in command-line format, e.g.:
      * ''--port=8081 --verbose''
      * Do '''NOT''' specify ''--root-dir'' here.
      */
    @Parameter(property = "params")
    var params: String = _

    /**
      * Set to ''true'' if (and only if) you want to keep the plugin running indefinitely.
      */
    @Parameter(property = "keepRunning", defaultValue = "false")
    var keepRunning: String = _

    def shouldKeepRunning(): Boolean = {
        keepRunning == "true"
    }

    def getAllParams(): Array[String] = {
        val directory: String = dir.toString
        if (params == null) {
            ParameterUtil.getDirParam(directory)
        } else {
            ParameterUtil.getAllParams(directory, params)
        }
    }
}
