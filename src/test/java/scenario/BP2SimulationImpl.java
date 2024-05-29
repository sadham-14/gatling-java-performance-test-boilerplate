package scenario;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

import static core.constant.ApiPayloadConstant.PRODUCT_NAMES_CSV_FILE_PATH;
import static core.constant.BusinessProcess.BP2;
import static core.controller.ProductController.updateProduct;
import static core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP2SimulationImpl extends Simulation implements BPSimulationService {
    /**
     * BP 2: Update Product
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;
    private final int testDurationInSeconds;

    public BP2SimulationImpl(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds, int testDurationInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
        this.testDurationInSeconds = testDurationInSeconds;
    }

    @Override
    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP2.getBusinessProcessFullName())
                .exec(authenticateUser())
                .pause(ofSeconds(3))
                .during(ofSeconds(testDurationInSeconds))
                .on(
                        feed(
                                csv(PRODUCT_NAMES_CSV_FILE_PATH)
                                        .circular()
                        )
                                .exec(updateProduct())
                                .pause(ofSeconds(thinkTimeInSeconds))
                ).injectOpen(rampUsers(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
