package scenario.breakpoint;

import core.util.ExecutionUtil;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import scenario.BPSimulationService;

import static core.constant.ApiPayloadConstant.PRODUCT_NAMES_CSV_FILE_PATH;
import static core.constant.BusinessProcess.BP2;
import static core.controller.ProductController.updateProduct;
import static core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP2BreakpointSimulationImpl extends Simulation implements BPSimulationService {
    /**
     * BP 2: Update Product
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;

    public BP2BreakpointSimulationImpl(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
    }

    @Override
    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP2.getBusinessProcessFullName())
                .exec(
                        authenticateUser()
                                .pause(ofSeconds(3))
                                .feed(
                                        csv(PRODUCT_NAMES_CSV_FILE_PATH)
                                                .circular()
                                )
                                .exec(updateProduct())
                                .pause(ofSeconds(thinkTimeInSeconds))
                                .exec(ExecutionUtil::exitApplicationIf5xx)
                ).injectOpen(rampUsersPerSec(0).to(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
