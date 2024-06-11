package scenario;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static core.controller.AuthController.getAuthenticationToken;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BusinessProcessSimulation extends Simulation {
    private final int virtualUserCount;
    private final Duration rampUpTime;
    private final Duration thinkTime;
    private final Duration testDuration;
    private final boolean isBreakpointTest;
    private final BusinessProcessService strategy;

    public BusinessProcessSimulation(int virtualUserCount, Duration rampUpTime, Duration thinkTime,
                                     Duration testDuration, boolean isBreakpointTest,
                                     BusinessProcessService service) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTime = rampUpTime;
        this.thinkTime = thinkTime;
        this.testDuration = testDuration;
        this.isBreakpointTest = isBreakpointTest;
        this.strategy = service;
    }

    public PopulationBuilder getPopulationBuilder() {
        ScenarioBuilder scenarioBuilder = scenario(strategy.getBusinessProcessName())
                .exec(getAuthenticationToken())
                .exitHereIfFailed()
                .pause(ofSeconds(3))
                .during(testDuration)
                .on(strategy.getScenario(thinkTime));

        if (isBreakpointTest) {
            return scenarioBuilder.injectClosed(
                    rampConcurrentUsers(0).to(virtualUserCount).during(rampUpTime)
            );
        } else {
            return scenarioBuilder.injectOpen(
                    rampUsers(virtualUserCount).during(rampUpTime)
            );
        }
    }
}
