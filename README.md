# wiremock-maven-plugin

[![Central status](https://maven-badges.herokuapp.com/maven-central/uk.co.deliverymind/wiremock-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.co.deliverymind/wiremock-maven-plugin)

Ultra-lightweight, super-simple WireMock Maven Plugin. 

## Quick start guide

- Add your mock definitions to the following folders:

```
src/main/resources/mappings/
src/main/resources/__files/
```

- Add plugin to your **pom.xml**:

```
<plugins>

   [...]

   <plugin>
      <groupId>uk.co.deliverymind</groupId>
      <artifactId>wiremock-maven-plugin</artifactId>
      <version>2.1.0</version>
      <executions>
         <execution>
            <goals>
               <goal>run</goal>
            </goals>
               <configuration>
                  <dir>target/classes</dir>
                  <params>--port=8080</params>
               </configuration>
         </execution>
      </executions>
   </plugin>
   
   [...]
   
</plugins>
```

See WireMock [manual](http://wiremock.org/docs/running-standalone/) for detailed information on available command line options.

- Run your tests:

`mvn clean verify`

Maven will copy your resources from `src/main/resources/` to `target/classes/`. Wiremock Maven Plugin will start WireMock on **localhost:8080** at **pre-integration-test** phase and use your mock definitions. Your tests executed at **integration-test** phase will have mocks ready to use. When Maven process execution finishes, WireMock will be stopped as well.

See [plugin-it/pom.xml](https://github.com/deliverymind/wiremock-maven-plugin/blob/master/plugin-it/pom.xml) for a complete example.

## Repo structure

### plugin

This module contains plugin itself. If you want to build most recent version locally:

`mvn -pl plugin clean install`

### plugin-it

This module contains all-in-one integration test and usage example. To run it locally:

`mvn -pl plugin-it clean verify`
