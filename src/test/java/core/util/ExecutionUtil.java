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

        if (session.getInt(RESPONSE_STATUS_CODE) >= HTTP_INTERNAL_SERVER_ERROR) {  // Catch 500, 501, 502, 503, 504, ...
            status5xxCount += 1;

            if (status5xxCount >= currentTotalRequestCount3Percentile) {
                System.exit(0); // Force quit the program
            }
        }
        return session;
    }
}
