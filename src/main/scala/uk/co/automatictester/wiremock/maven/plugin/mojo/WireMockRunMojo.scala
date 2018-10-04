package uk.co.automatictester.wiremock.maven.plugin.mojo

import java.util

import org.apache.maven.plugins.annotations.{LifecyclePhase, Mojo, ResolutionScope}
import uk.co.automatictester.wiremock.maven.plugin.server.WireMockServer
import uk.co.automatictester.wiremock.maven.plugin.util.ClasspathUtil

@Mojo(name = "run", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST, requiresDependencyResolution = ResolutionScope.RUNTIME)
class WireMockRunMojo extends ConfigurationMojo {

    def execute(): Unit = {
        addRuntimeDependenciesToClasspath()

        val rawWireMockParams: Array[String] = getAllParams()
        val wireMockParams: String = getFormattedStringFrom(rawWireMockParams)
        val startMessage: String = String.format("Starting WireMock with following params: %s", wireMockParams)
        getLog.info(startMessage)
        WireMockServer.run(rawWireMockParams)
        if (shouldKeepRunning()) {
            keepRunningUntilInterrupted()
        }
    }

    private def addRuntimeDependenciesToClasspath(): Unit = {
        val classpathUtil: ClasspathUtil = new ClasspathUtil()
        classpathUtil.classpathElements = classpathElements
        classpathUtil.descriptor = descriptor
        classpathUtil.addRuntimeDependenciesToClasspath()
    }

    private def getFormattedStringFrom(array: Array[String]): String = {
        util.Arrays.toString(array.map(_.asInstanceOf[AnyRef])).replaceAll("[\\[\\]]", "").replaceAll(", ", " ")
    }

    private def keepRunningUntilInterrupted(): Unit = {
        getLog.info("WireMock will keep running until interrupted manually...")
        while (true) {
            sleepForOneSecond()
        }
    }

    private def sleepForOneSecond(): Unit = {
        try {
            Thread.sleep(1000)
        } catch {
            case e: InterruptedException => getLog.error(e.getMessage)
        }
    }
}
