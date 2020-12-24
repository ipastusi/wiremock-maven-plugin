# WireMock Maven Plugin

[![Build Status](https://travis-ci.com/automatictester/wiremock-maven-plugin.svg?branch=master)](https://travis-ci.com/automatictester/wiremock-maven-plugin)
[![Central status](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/wiremock-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/wiremock-maven-plugin)

Run WireMock as part of Maven lifecycle.

## What's new in version 5.0.0 and above

Each version of WireMock Maven Plugin prior to version 5.0.0 was shipped bundled together with a particular version of
WireMock itself. This resulted in WireMock Maven Plugin having to be released after every release of WireMock itself.
This introduced potential delay between when new WireMock version was released, and when users of WireMock Maven Plugin
could benefit from it. For this reason, WireMock Maven Plugin version 5.0.0 and above come decoupled from WireMock
itself. It is up to the users to explicitly decide which WireMock version they intend to use.

You can do this by specifying WireMock dependency on WireMock Maven Plugin level:

```xml
<build>
    <plugins>
       <plugin>
          <groupId>uk.co.automatictester</groupId>
          <artifactId>wiremock-maven-plugin</artifactId>          
          <version><!-- check above badge for most recent version of wiremock-maven-plugin --></version>
          
          [...]
          
          <dependencies>
             <dependency>
                <groupId>com.github.tomakehurst</groupId>
                <artifactId>wiremock</artifactId>
                <version><!-- wiremock version you want to use --></version>
             </dependency>
          </dependencies>
       </plugin>   
    </plugins>
</build>
```

## Quick start guide

- Add your mock definitions to the following folders:

```
src/main/resources/mappings/
src/main/resources/__files/
```

- Add this plugin to your **pom.xml**:

```xml
<build>
    <plugins>
       <plugin>
          <groupId>uk.co.automatictester</groupId>
          <artifactId>wiremock-maven-plugin</artifactId>          
          <version><!-- check above badge for most recent version of wiremock-maven-plugin --></version>
          
          <executions>
             <execution>
                <goals>
                   <goal>run</goal>
                   <goal>stop</goal> <!-- important in multi-module project where more than one module uses this plugin -->
                </goals>
                <configuration>
                   <dir>target/classes</dir>
                   <params>--port=8081 --verbose</params>
                   <skip>false</skip> <!-- set to true to skip plugin execution -->
                </configuration>
             </execution>
          </executions>
          <dependencies>
             <dependency>
                <groupId>com.github.tomakehurst</groupId>
                <artifactId>wiremock</artifactId>
                <version><!-- wiremock version you want to use --></version>
             </dependency>
          </dependencies>
       </plugin>   
    </plugins>
</build>
```

See [WireMock manual](http://wiremock.org/docs/running-standalone/) for detailed information on available command line
options.

- Run your tests:

`mvn clean verify`

Maven will copy your resources from `src/main/resources/` to `target/classes/`. WireMock Maven Plugin will start
WireMock on `localhost:8081` at `pre-integration-test` phase and use your mock definitions. Your tests executed
at `integration-test` phase will have mocks ready to use. When Maven process execution finishes, WireMock will be
stopped as well.

You may want to stop WireMock Maven Plugin explicitly in `post-integration-test` phase instead of waiting for it to shut
down at the end of Maven process execution. You can do that by adding `stop` goal to the configuration. See above code
snippet as an example. This will help you avoid port conflicts in multi-module Maven projects with more than one module
using this plugin.

See [pom.xml](https://github.com/automatictester/wiremock-maven-plugin/blob/master/src/it/core/pom.xml) for a simple
example and [pom.xml](https://github.com/automatictester/wiremock-maven-plugin/blob/master/src/it/ext/pom.xml) for an
example with WireMock extension.

If you want to start and stop WireMock manually, but prefer Maven to download it for you, this is how you can configure
your project:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>uk.co.automatictester</groupId>
            <artifactId>wiremock-maven-plugin</artifactId>
            <version><!-- wiremock-maven-plugin version - check maven central badge above for most recent released version number --></version>
            
            <configuration>
                <keepRunning>true</keepRunning>
                <dir>target/classes</dir>
                <params>--port=8081 --verbose</params>
            </configuration>
            
            <dependencies>
               <dependency>
                  <groupId>com.github.tomakehurst</groupId>
                  <artifactId>wiremock</artifactId>
                  <version><!-- wiremock version you want to use --></version>
               </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
```

Now when you run it with `mvn clean compile wiremock:run`, you should see the following line in the console output:

```
[INFO] WireMock will keep running until interrupted manually...
```

## Required Java version

WireMock Maven Plugin is tested against a broad range of Java versions.
See [Travis CI config](https://github.com/automatictester/wiremock-maven-plugin/blob/master/.travis.yml) for details.
