package crisp.scenario.breakpoint;

import crisp.core.controller.AuthController;
import crisp.core.util.ExecutionUtil;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

import static crisp.core.constant.ApiConstant.*;
import static crisp.core.constant.BusinessProcess.BP3;
import static crisp.core.controller.GetAllServiceRequestsController.getAllServiceRequests;
import static crisp.core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP3BreakpointTest extends Simulation {
    /**
     * BP 3: View all Service Requests
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;

    public BP3BreakpointTest(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
    }

    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP3.getBusinessProcessFullName())
                .exec(
                        feed(
                                csv("data/requester_accounts.csv")
                                        .circular()
                        )
                                .exec(AuthController.getAuthenticationToken(BASE_URL + AUTHORIZATION_ENDPOINT))
                                .pause(ofSeconds(3))
                                .exec(getAllServiceRequests(BASE_URL + SERVICE_REQUESTS_ENDPOINT))
                                .pause(ofSeconds(thinkTimeInSeconds))
                                .exec(ExecutionUtil::exitApplicationIf5xx)
                ).injectOpen(rampUsersPerSec(0).to(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
