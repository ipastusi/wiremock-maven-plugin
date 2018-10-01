package uk.co.automatictester.wiremock.maven.plugin.util

import scala.collection.JavaConverters._
import scala.collection.mutable.MutableList

object ParameterUtil {

    private val RootDirParamPrefix: String = "--root-dir="

    def getAllParams(dir: String, nonDirParams: String): Array[String] = {
        val dirParams: List[String] = getDirParam(dir).toList
        val otherParams: List[String] = getNonDirParams(nonDirParams)
        val allParams: MutableList[String] = MutableList()
        allParams ++= dirParams
        allParams ++= otherParams
        allParams.asJava.toArray(Array.ofDim(1))
    }

    def getDirParam(dir: String): Array[String] = {
        val dirParam: List[String] = List(RootDirParamPrefix + dir)
        dirParam.asJava.toArray(Array.ofDim(1))
    }

    private def getNonDirParams(nonDirParams: String): List[String] = {
        nonDirParams.split(" ").toList
    }
}
