package core.controller;

import io.gatling.javaapi.core.ActionBuilder;

import static core.constant.ApiPayloadConstant.*;
import static core.constant.EnvironmentConstant.BASE_URL;
import static core.util.RequestBodyGenerator.getAuthenticationRequestPayload;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class AuthController {
    public static ActionBuilder getAuthenticationToken() {
        return http(GET_AUTH_TOKEN_API_NAME)
                .post(BASE_URL.concat(AUTHORIZATION_ENDPOINT))
                .body(StringBody(getAuthenticationRequestPayload()))
                .check(status().is(200).saveAs(RESPONSE_STATUS_CODE))
                .check(jsonPath("$.token").notNull())
                .check(jsonPath("$.token").saveAs(AUTH_TOKEN_VARIABLE))
                .silent();
    }
}
