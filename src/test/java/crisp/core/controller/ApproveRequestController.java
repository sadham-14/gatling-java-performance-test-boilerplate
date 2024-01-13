package crisp.core.controller;

import io.gatling.javaapi.core.ActionBuilder;

import static crisp.core.constant.ApiConstant.*;
import static crisp.core.util.RequestBodyGenerator.getApproveRequestBody;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ApproveRequestController {
    public static ActionBuilder approveRequest(String url) {
        return http(APPROVE_SERVICE_REQUEST_API_NAME).put(url)
                .header(AUTH_HEADER_NAME, "Bearer #{token}")
                .queryParam("action", "Approve")
                .body(StringBody(getApproveRequestBody()))
                .check(status().is(HTTP_OK))
                .check(status().saveAs(RESPONSE_STATUS_CODE))
                .check(bodyString().saveAs("approveRes"));
    }
}
