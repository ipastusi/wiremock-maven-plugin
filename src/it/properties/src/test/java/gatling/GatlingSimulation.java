package gatling;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class GatlingSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8082");
    private final ScenarioBuilder scenario = scenario("get")
            .exec(http("get")
                    .get("/healthcheck")
                    .check(
                            status().is(200),
                            substring("wiremock-maven-plugin")
                    )
            );

    {
        setUp(scenario.injectClosed(constantConcurrentUsers(1).during(10)))
                .maxDuration(30)
                .protocols(httpProtocol)
                .assertions(
                        global().successfulRequests().percent().is(100.0)
                );
    }
}
