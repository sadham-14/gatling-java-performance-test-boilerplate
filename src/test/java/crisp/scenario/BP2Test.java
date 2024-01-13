package crisp.scenario;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;

import static crisp.core.constant.ApiConstant.*;
import static crisp.core.constant.BusinessProcess.BP2;
import static crisp.core.controller.ApproveRequestController.approveRequest;
import static crisp.core.controller.AuthController.getAuthenticationToken;
import static crisp.core.util.HttpProtocol.getGenericHttpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;
import static java.time.Duration.ofSeconds;

public class BP2Test extends Simulation {
    /**
     * BP 2: Application Environment Creation Request Approval
     */
    private final int virtualUserCount;
    private final int rampUpTimeInSeconds;
    private final int thinkTimeInSeconds;
    private final int testDurationInSeconds;

    public BP2Test(int virtualUserCount, int rampUpTimeInSeconds, int thinkTimeInSeconds, int testDurationInSeconds) {
        this.virtualUserCount = virtualUserCount;
        this.rampUpTimeInSeconds = rampUpTimeInSeconds;
        this.thinkTimeInSeconds = thinkTimeInSeconds;
        this.testDurationInSeconds = testDurationInSeconds;
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
                                .during(ofSeconds(testDurationInSeconds))
                                .on(
                                        feed(
                                                csv("data/pending_approval_request_ids.csv")
                                                        .circular()
                                        )
                                                .exec(approveRequest(BASE_URL + SERVICE_REQUESTS_ENDPOINT))
                                                .pause(ofSeconds(thinkTimeInSeconds))
                                )
                ).injectOpen(rampUsers(virtualUserCount).during(rampUpTimeInSeconds))
                .protocols(getGenericHttpProtocol());
    }
}
