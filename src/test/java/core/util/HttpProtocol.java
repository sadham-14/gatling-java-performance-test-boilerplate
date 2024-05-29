package core.util;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class HttpProtocol {
    public static HttpProtocolBuilder getGenericHttpProtocol() {
        return http
                .header("Cache-Control", "no-cache")
                .acceptHeader("application/json")
                .disableCaching()
                .disableAutoReferer()
                .disableFollowRedirect()
                .contentTypeHeader("application/json")
                .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
    }
}
