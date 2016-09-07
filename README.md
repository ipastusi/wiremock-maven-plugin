# wiremock-maven-plugin

[![Join the chat at https://gitter.im/automatictester/wiremock-maven-plugin](https://badges.gitter.im/automatictester/lightning.svg)](https://gitter.im/automatictester/wiremock-maven-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://snap-ci.com/automatictester/wiremock-maven-plugin/branch/master/build_image)](https://snap-ci.com/automatictester/wiremock-maven-plugin/branch/master)
[![Central status](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/wiremock-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.co.automatictester/wiremock-maven-plugin)

Ultra-lightweight, super-simple WireMock Maven Plugin. 

## Quick start guide

- Add plugin to your **pom.xml**:

```
<plugins>

   [...]

   <plugin>
      <groupId>uk.co.automatictester</groupId>
      <artifactId>wiremock-maven-plugin</artifactId>
      <version>1.0.0</version>
      <executions>
         <execution>
            <goals>
               <goal>run</goal>
            </goals>
            <configuration>
               <params>--root-dir=target/classes --port=8089</params>
            </configuration>
         </execution>
      </executions>
   </plugin>
   
   [...]
   
</plugins>
```

See WireMock [manual](http://wiremock.org/docs/running-standalone/) for detailed information on available command line options. You can add or ramove other parameters, but keep the `--root-dir=target/classes` unchanged, unless you know what you are doing.

- Add JSON files containing your mappings to the following folder:

`src/main/resources/mappings/`

- Run your tests:

`mvn clean verify`

Wiremock Maven Plugin will start WireMock on **localhost:8089** using mappings defined in `src/main/resources/mappings/sample.json` at **pre-integration-test** phase. When Maven process execution finishes, WireMock will be stopped as well.

See [plugin-it/pom.xml](https://github.com/automatictester/wiremock-maven-plugin/blob/7900107b050527ef7f7672a6074ad388cfc56f84/plugin-it/pom.xml) for a complete example.

## Repo structure

### plugin

This module contains plugin itself. If you want to build most recent version locally:

`(cd plugin; mvn clean install)`

### plugin-it

This module contains all-in-one integration test and usage example. To run it locally:

`(cd plugin-it; mvn clean verify)`

## Other info

Questions are welcome on Gitter. Pull requests are also welcome, and if processed, release will follow shortly.
