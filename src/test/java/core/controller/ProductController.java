package core.controller;

import io.gatling.javaapi.core.ActionBuilder;

import static core.constant.ApiPayloadConstant.*;
import static core.constant.EnvironmentConstant.BASE_URL;
import static core.util.RequestBodyGenerator.getAddProductRequestPayload;
import static core.util.RequestBodyGenerator.getUpdateProductRequestPayload;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ProductController {
    public static ActionBuilder getAllProducts() {
        return http(GET_ALL_PRODUCTS_API_NAME).get(BASE_URL + PRODUCTS_ENDPOINT)
                .header(AUTH_HEADER_NAME, AUTH_TOKEN_PLACEHOLDER)
                .check(status().is(HTTP_OK))
                .check(status().saveAs(RESPONSE_STATUS_CODE));
    }

    public static ActionBuilder addProduct() {
        return http(ADD_PRODUCT_API_NAME).post(BASE_URL + ADD_PRODUCT_ENDPOINT)
                .header(AUTH_HEADER_NAME, AUTH_TOKEN_PLACEHOLDER)
                .body(StringBody(getAddProductRequestPayload()))
                .check(status().is(HTTP_CREATED))
                .check(status().saveAs(RESPONSE_STATUS_CODE));
    }

    public static ActionBuilder updateProduct() {
        return http(UPDATE_PRODUCT_API_NAME).put(BASE_URL + PRODUCTS_ENDPOINT + "/1")
                .header(AUTH_HEADER_NAME, AUTH_TOKEN_PLACEHOLDER)
                .body(StringBody(getUpdateProductRequestPayload()))
                .check(status().is(HTTP_OK))
                .check(status().saveAs(RESPONSE_STATUS_CODE));
    }
}
