package crisp.core.controller;

import io.gatling.javaapi.core.ActionBuilder;

import static crisp.core.constant.ApiConstant.*;
import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class AuthController {

    public static ActionBuilder getAuthenticationToken(String url) {
        return http(GET_JWT_API_NAME).get(url)
                .header("Content-Type", "application/json")
                .header(AUTH_HEADER_NAME, "Bearer " + AUTH_HEADER_VALUE)
                .queryParam("id", "#{accountId}")
                .check(bodyString().saveAs("bodyRes"))
                .check(status().is(HTTP_OK))
                .check(status().saveAs(RESPONSE_STATUS_CODE))
                .check(jsonPath("$.token").notNull())
                .check(jsonPath("$.token").saveAs("token"))
                .silent();
    }
}
