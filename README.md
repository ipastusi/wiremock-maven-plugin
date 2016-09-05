# wiremock-maven-plugin

[![Join the chat at https://gitter.im/automatictester/wiremock-maven-plugin](https://badges.gitter.im/automatictester/lightning.svg)](https://gitter.im/automatictester/wiremock-maven-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://snap-ci.com/automatictester/wiremock-maven-plugin/branch/master/build_image)](https://snap-ci.com/automatictester/wiremock-maven-plugin/branch/master)

Ultra-lightweight, super-simple WireMock Maven Plugin. 

## plugin

Plugin itself. It will be available in Maven Central in next few days. To build it locally: `(cd plugin; mvn clean install)`

## plugin-it

All-in-one integration test and usage example. To run it locally: `(cd plugin-it; mvn clean verify)`

How it works:
- **wiremock-maven-plugin** grabs mappings defined in `src/main/resources/mappings/sample.json` at **pre-integration-test** phase
- **jmeter-maven-plugin** executes JMeter test (**example.jmx**) against those mocks at **integration-test** phase
- When Maven process execution is finished, WireMock is stopped as well

Custom params can be provided using **params** tag in plugin **configuration** section:

```
<configuration>
   <params>--root-dir=target/classes --port=8089</params>
</configuration>
```

See [pom.xml](https://github.com/automatictester/wiremock-maven-plugin/blob/master/plugin-it/pom.xml) for complete example. WireMock [manual](http://wiremock.org/docs/running-standalone/) provides detailed information on available command line options. 