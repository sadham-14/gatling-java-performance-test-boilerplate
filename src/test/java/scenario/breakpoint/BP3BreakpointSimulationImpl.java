package scenario.breakpoint;

import core.util.ExecutionUtil;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import scenario.BPSimulationService;

import static core.constant.BusinessProcess.BP3;
import static core.controller.ProductController.getAllProducts;
import static core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static java.time.Duration.ofSeconds;

public class BP3BreakpointSimulationImpl extends Simulation implements BPSimulationService {
    /**
     * BP 3: Get All Products
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;

    public BP3BreakpointSimulationImpl(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
    }

    @Override
    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP3.getBusinessProcessFullName())
                .exec(
                        authenticateUser()
                                .pause(ofSeconds(3))
                                .exec(getAllProducts())
                                .pause(ofSeconds(thinkTimeInSeconds))
                                .exec(ExecutionUtil::exitApplicationIf5xx)
                ).injectOpen(rampUsersPerSec(0).to(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
