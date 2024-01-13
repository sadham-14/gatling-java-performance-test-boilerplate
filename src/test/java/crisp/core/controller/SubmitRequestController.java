package crisp.core.controller;

import io.gatling.javaapi.core.ActionBuilder;

import static crisp.core.constant.ApiConstant.*;
import static crisp.core.util.RequestBodyGenerator.getSubmitRequestBodyPayload;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class SubmitRequestController {
    public static ActionBuilder submitServiceRequest(String url) {
        return http(SUBMIT_SERVICE_REQUEST_API_NAME).post(url)
                .header(AUTH_HEADER_NAME, "Bearer #{token}")
                .body(StringBody(getSubmitRequestBodyPayload()))
                .check(status().is(HTTP_OK))
                .check(status().saveAs(RESPONSE_STATUS_CODE))
                .check(bodyString().saveAs("createRes"));
    }
}
