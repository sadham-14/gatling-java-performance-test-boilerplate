package crisp.scenario.breakpoint;

import crisp.core.util.ExecutionUtil;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

import static crisp.core.constant.ApiConstant.*;
import static crisp.core.constant.BusinessProcess.BP1;
import static crisp.core.controller.AuthController.getAuthenticationToken;
import static crisp.core.controller.SubmitRequestController.submitServiceRequest;
import static crisp.core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP1BreakpointTest extends Simulation {
    /**
     * BP1: Application Environment Creation Request Submission
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;

    public BP1BreakpointTest(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
    }

    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP1.getBusinessProcessFullName())
                .exec(
                        feed(
                                csv("data/requester_accounts.csv")
                                        .circular()
                        )
                                .exec(getAuthenticationToken(BASE_URL.concat(AUTHORIZATION_ENDPOINT)))
                                .pause(ofSeconds(3))
                                .exec(submitServiceRequest(BASE_URL.concat(SERVICE_REQUESTS_ENDPOINT)))
                                .pause(ofSeconds(thinkTimeInSeconds))
                                .exec(ExecutionUtil::exitApplicationIf5xx)
                ).injectOpen(rampUsersPerSec(0).to(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
