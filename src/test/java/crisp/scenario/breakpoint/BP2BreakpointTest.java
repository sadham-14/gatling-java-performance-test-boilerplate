package crisp.scenario.breakpoint;

import crisp.core.util.ExecutionUtil;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

import static crisp.core.constant.ApiConstant.*;
import static crisp.core.constant.BusinessProcess.BP2;
import static crisp.core.controller.ApproveRequestController.approveRequest;
import static crisp.core.controller.AuthController.getAuthenticationToken;
import static crisp.core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP2BreakpointTest extends Simulation {
    /**
     * BP 2: Application Environment Creation Request Approval
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;

    public BP2BreakpointTest(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
    }

    public PopulationBuilder getPopulationBuilder() {
        return scenario(BP2.getBusinessProcessFullName())
                .exec(
                        feed(
                                csv("data/approver_accounts.csv")
                                        .circular()
                        )
                                .exec(getAuthenticationToken(BASE_URL + AUTHORIZATION_ENDPOINT))
                                .pause(ofSeconds(3))
                                .feed(
                                        csv("data/pending_approval_request_ids.csv")
                                                .circular()
                                )
                                .exec(approveRequest(BASE_URL + SERVICE_REQUESTS_ENDPOINT))
                                .pause(ofSeconds(thinkTimeInSeconds))
                                .exec(ExecutionUtil::exitApplicationIf5xx)
                ).injectOpen(rampUsersPerSec(0).to(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
