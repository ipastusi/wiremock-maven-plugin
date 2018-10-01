package uk.co.automatictester.wiremock.maven.plugin

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import uk.co.automatictester.wiremock.maven.plugin.util.ParameterUtil

class ParameterUtilSpec extends PropSpec with TableDrivenPropertyChecks {

    val dirParams =
        Table(
            ("input", "output"),
            ("target/classes", Array("--root-dir=target/classes")),
            ("dir with spaces", Array("--root-dir=dir with spaces"))
        )

    property("converts dir param correctly") {
        forAll(dirParams) { (input, output) =>
            val allParams: Array[String] = ParameterUtil.getDirParam(input)
            assert(allParams sameElements output)
        }
    }

    val allParams =
        Table(
            ("dirParam", "otherParams", "output"),
            ("target/classes", "--port=8081", Array("--root-dir=target/classes", "--port=8081")),
            ("target/classes", "--port=8081 --extensions=uk.co.automatictester.wiremock.maven.plugin.SampleResponseTransformer -v", Array("--root-dir=target/classes", "--port=8081", "--extensions=uk.co.automatictester.wiremock.maven.plugin.SampleResponseTransformer", "-v")),
            ("dir with spaces", "--port=8081", Array("--root-dir=dir with spaces", "--port=8081"))
        )

    property("converts all params correctly") {
        forAll(allParams) { (dirParam, otherParams, output) =>
            val allParams: Array[String] = ParameterUtil.getAllParams(dirParam, otherParams)
            assert(allParams sameElements output)
        }
    }
}
