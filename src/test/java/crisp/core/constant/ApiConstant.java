package crisp.core.constant;

import static crisp.core.util.PropertyFileReader.getPropertyValue;

public class ApiConstant {
    // Environment Constants
    public static final String SIT = "SIT";
    public static final String UAT = "UAT";
    public static final String TEST_ENV = getPropertyValue("test_env").toUpperCase();
    public static final String BASE_URL = getBaseUrl();

    // API Names
    public static final String GET_JWT_API_NAME = "[GET] Get JWT";
    public static final String SUBMIT_SERVICE_REQUEST_API_NAME = "[POST] Submit Service Request";
    public static final String APPROVE_SERVICE_REQUEST_API_NAME = "[PUT] Approve Service Request";
    public static final String GET_ALL_SERVICE_REQUESTS_API_NAME = "[GET] Get All Service Requests";

    // API Endpoints
    public static final String SERVICE_REQUESTS_ENDPOINT = "/core/service-requests";
    public static final String AUTHORIZATION_ENDPOINT = "/user/mock-token";

    // Authentication Header
    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final String AUTH_HEADER_VALUE = getApiKey();

    // Response Status Codes
    public static final String RESPONSE_STATUS_CODE = "responseStatusCode";
    public static final int HTTP_OK = 200;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;

    private static String getBaseUrl() {
        String baseUrl;

        switch (TEST_ENV) {
            case SIT:
                baseUrl = getPropertyValue("sit_base_url");
                break;
            case UAT:
                baseUrl = getPropertyValue("uat_base_url");
                break;
            default:
                throw new RuntimeException("Unable run performance test on '" + TEST_ENV + "'environment.");
        }

        return baseUrl;
    }

    private static String getApiKey() {
        String apiKey;

        switch (TEST_ENV) {
            case SIT:
                apiKey = getPropertyValue("sit_api_key");
                break;
            case UAT:
                apiKey = getPropertyValue("uat_api_key");
                break;
            default:
                throw new RuntimeException("Unable run performance test on '" + TEST_ENV + "'environment.");
        }

        return apiKey;
    }
}
