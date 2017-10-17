# wiremock-maven-plugin

[![Central status](https://maven-badges.herokuapp.com/maven-central/uk.co.deliverymind/wiremock-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.co.deliverymind/wiremock-maven-plugin)

Ultra-lightweight, super-simple WireMock Maven Plugin.
 
Please note the above badge shows current version of WireMock Maven Plugin, **not** version of WireMock itself. See [releases tab](https://github.com/deliverymind/wiremock-maven-plugin/releases) for information which WireMock version is used by WireMock Maven Plugin.

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
          <groupId>uk.co.deliverymind</groupId>
          <artifactId>wiremock-maven-plugin</artifactId>          
          <version><!-- check maven central badge above for most recent released version number --></version>
          
          <executions>
             <execution>
                <goals>
                   <goal>run</goal>
                </goals>
                   <configuration>
                      <dir>target/classes</dir>
                      <params>--port=8081</params>
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

Maven will copy your resources from `src/main/resources/` to `target/classes/`. Wiremock Maven Plugin will start WireMock on **localhost:8081** at **pre-integration-test** phase and use your mock definitions. Your tests executed at **integration-test** phase will have mocks ready to use. When Maven process execution finishes, WireMock will be stopped as well.

See [pom.xml](https://github.com/deliverymind/wiremock-maven-plugin/blob/master/src/it/core/pom.xml) for a simple example and [pom.xml](https://github.com/deliverymind/wiremock-maven-plugin/blob/master/src/it/ext/pom.xml) for an example with WireMock extension. 

If you want to build most recent version locally:

`mvn clean install`

If you want to start and stop WireMock manually, but prefer Maven to download it for you, this is how you need to configure your project:
  
```
<build>
    <plugins>
        <plugin>
            <groupId>uk.co.deliverymind</groupId>
            <artifactId>wiremock-maven-plugin</artifactId>
            <version><!-- check maven central badge above for most recent released version number --></version>
            
            <configuration>
                <keepRunning>true</keepRunning>
                <dir>target/classes</dir>
                <params>--port=8081</params>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Now when you run it with `mvn clean compile wiremock:run`, you should see the following line in the console output:

```
[INFO] WireMock will keep running until interrupted manually...
```