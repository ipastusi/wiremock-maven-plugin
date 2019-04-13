# wiremock-maven-plugin

[![Build Status](https://travis-ci.com/automatictester/wiremock-maven-plugin.svg?branch=master)](https://travis-ci.com/automatictester/wiremock-maven-plugin)
[![Central status](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/wiremock-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/wiremock-maven-plugin)

Ultra-lightweight, super-simple WireMock Maven Plugin.
 
Please note the above badge shows current version of WireMock Maven Plugin, **not** version of WireMock itself. 
See [releases tab](https://github.com/automatictester/wiremock-maven-plugin/releases) for information which WireMock version is used by WireMock Maven Plugin.

## Required Java version

WireMock Maven Plugin is tested against a broad range of [Java versions](https://github.com/automatictester/wiremock-maven-plugin/blob/master/.travis.yml), from 7 to 11 inclusive.

## Quick start guide

- Add your mock definitions to the following folders:

```
src/main/resources/mappings/
src/main/resources/__files/
```

- Add plugin to your **pom.xml**:

```
<build>
    <plugins>
       <plugin>
          <groupId>uk.co.automatictester</groupId>
          <artifactId>wiremock-maven-plugin</artifactId>          
          <version> <!-- check maven central badge above for most recent released version number --> </version>
          
          <executions>
             <execution>
                <goals>
                   <goal>run</goal>
                   <goal>stop</goal> <!-- important in multi-module project where more than one module uses this plugin -->
                </goals>
                   <configuration>
                      <dir>target/classes</dir>
                      <params>--port=8081 --verbose</params>
                   </configuration>
             </execution>
          </executions>
          
       </plugin>   
    </plugins>
</build>
```

See [WireMock manual](http://wiremock.org/docs/running-standalone/) for detailed information on available command line options.

- Run your tests:

`mvn clean verify`

Maven will copy your resources from `src/main/resources/` to `target/classes/`. WireMock Maven Plugin will start WireMock on **localhost:8081** at 
**pre-integration-test** phase and use your mock definitions. Your tests executed at **integration-test** phase will have mocks ready to use. 
When Maven process execution finishes, WireMock will be stopped as well.

You may want to stop WireMock Maven Plugin explicitly in **post-integration-test** phase instead of waiting for it to shut down at the end of Maven process execution.
You can do that by adding **stop** goal to the configuration. See above code snippet as an example. This will help you avoid port conflicts in multi-module 
Maven projects with more than one module using this plugin.

See [pom.xml](https://github.com/automatictester/wiremock-maven-plugin/blob/master/src/it/core/pom.xml) for a simple example and [pom.xml](https://github.com/automatictester/wiremock-maven-plugin/blob/master/src/it/ext/pom.xml) for an example with WireMock extension.

If you want to build most recent version locally:

`mvn clean install`

If you want to start and stop WireMock manually, but prefer Maven to download it for you, this is how you need to configure your project:
  
```
<build>
    <plugins>
        <plugin>
            <groupId>uk.co.automatictester</groupId>
            <artifactId>wiremock-maven-plugin</artifactId>
            <version><!-- check maven central badge above for most recent released version number --></version>
            
            <configuration>
                <keepRunning>true</keepRunning>
                <dir>target/classes</dir>
                <params>--port=8081 --verbose</params>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Now when you run it with `mvn clean compile wiremock:run`, you should see the following line in the console output:

```
[INFO] WireMock will keep running until interrupted manually...
```
