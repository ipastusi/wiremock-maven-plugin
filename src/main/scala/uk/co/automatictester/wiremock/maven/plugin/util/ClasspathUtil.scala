package uk.co.automatictester.wiremock.maven.plugin.util

import java.io.File
import java.net.{MalformedURLException, URI, URL}
import java.util

import org.apache.maven.plugin.MojoExecutionException
import org.apache.maven.plugin.descriptor.PluginDescriptor
import org.codehaus.plexus.classworlds.realm.ClassRealm
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConverters._

class ClasspathUtil {

    var descriptor: PluginDescriptor = _
    var classpathElements: util.List[String] = _
    private val log: Logger = LoggerFactory.getLogger(this.getClass)

    def addRuntimeDependenciesToClasspath(): Unit = {
        val realm: ClassRealm = descriptor.getClassRealm

        toIterator(classpathElements).foreach(classpathElement => {
            log.debug("Adding {} to wiremock-maven-plugin classpath", classpathElement)
            val classpathElementUrl: URL = getClasspathElementFrom(classpathElement)
            realm.addURL(classpathElementUrl)
        })
    }

    private def toIterator[T](javaList: util.List[T]): Iterator[T] = {
        javaList.iterator().asScala
    }

    private def getClasspathElementFrom(classpathElement: String): URL = {
        val classpathElementFile: File = new File(classpathElement)
        val classpathElementUri: URI = classpathElementFile.toURI
        getClasspathElementFrom(classpathElementUri)
    }

    private def getClasspathElementFrom(uri: URI): URL = try {
        uri.toURL
    } catch {
        case e: MalformedURLException => throw new MojoExecutionException("Malformed classpath URL", e)
    }
}
