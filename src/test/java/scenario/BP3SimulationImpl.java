package scenario;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

import static core.constant.BusinessProcess.BP3;
import static core.controller.ProductController.getAllProducts;
import static core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP3SimulationImpl extends Simulation implements BPSimulationService {
    /**
     * BP 3: Get All Products
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;
    private final int testDurationInSeconds;

    public BP3SimulationImpl(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds, int testDurationInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
        this.testDurationInSeconds = testDurationInSeconds;
    }

    @Override
    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP3.getBusinessProcessFullName())
                .exec(authenticateUser())
                .pause(ofSeconds(3))
                .during(ofSeconds(testDurationInSeconds))
                .on(
                        exec(getAllProducts())
                                .pause(ofSeconds(thinkTimeInSeconds))
                )
                .injectOpen(rampUsers(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
