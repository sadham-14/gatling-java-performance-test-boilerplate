package crisp.core.controller;

import io.gatling.javaapi.core.ActionBuilder;

import static crisp.core.constant.ApiConstant.*;
import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class GetAllServiceRequestsController {

    public static ActionBuilder getAllServiceRequests(String url) {
        return http(GET_ALL_SERVICE_REQUESTS_API_NAME).get(url)
                .header(AUTH_HEADER_NAME, "Bearer #{token}")
                .queryParam("currentPage", "1")
                .queryParam("itemsPerPage", "20")
                .check(status().is(HTTP_OK))
                .check(status().saveAs(RESPONSE_STATUS_CODE))
                .check(bodyString().saveAs("queryRes"));
    }
}
