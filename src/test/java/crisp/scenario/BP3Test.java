package crisp.scenario;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

import static crisp.core.constant.ApiConstant.*;
import static crisp.core.constant.BusinessProcess.BP3;
import static crisp.core.controller.AuthController.getAuthenticationToken;
import static crisp.core.controller.GetAllServiceRequestsController.getAllServiceRequests;
import static crisp.core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP3Test extends Simulation {
    /**
     * BP 3: View all Service Requests
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;
    private final int testDurationInSeconds;

    public BP3Test(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds, int testDurationInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
        this.testDurationInSeconds = testDurationInSeconds;
    }

    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP3.getBusinessProcessFullName())
                .exec(
                        feed(csv("data/requester_accounts.csv")
                                .circular()
                        )
                                .exec(getAuthenticationToken(BASE_URL + AUTHORIZATION_ENDPOINT))
                                .pause(ofSeconds(3))
                                .during(ofSeconds(testDurationInSeconds))
                                .on(exec(getAllServiceRequests(BASE_URL + SERVICE_REQUESTS_ENDPOINT))
                                        .pause(ofSeconds(thinkTimeInSeconds))
                                )
                )
                .injectOpen(rampUsers(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
