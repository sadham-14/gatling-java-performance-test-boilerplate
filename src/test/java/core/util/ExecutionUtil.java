package core.util;

import io.gatling.javaapi.core.Session;

import static core.constant.ApiPayloadConstant.HTTP_INTERNAL_SERVER_ERROR;
import static core.constant.ApiPayloadConstant.RESPONSE_STATUS_CODE;

public class ExecutionUtil {
    private static int currentTotalRequestCount = 0;
    private static int status5xxCount = 0;

    public static Session exitApplicationIf5xx(Session session) {
        currentTotalRequestCount += 1;
        double currentTotalRequestCount3Percentile = currentTotalRequestCount * 0.03;

//        Catch 500, 501, 502, 503, 504, ...
        if (session.getInt(RESPONSE_STATUS_CODE) >= HTTP_INTERNAL_SERVER_ERROR) {
            status5xxCount += 1;

//            If the count of 5xx responses (status5xxCount) exceeds 3% of the total number of requests
//            (currentTotalRequestCount), it exits the program
            if (status5xxCount >= currentTotalRequestCount3Percentile) {
                System.exit(0);
            }
        }
        return session;
    }
}
