package core.constant;

import static core.util.PropertyFileReader.getPropertyValue;

public class EnvironmentConstant {
    public static final String SIT = "SIT";
    public static final String UAT = "UAT";
    public static final String TEST_ENV = System.getProperty("env", SIT); // Setting the default environment to SIT
    public static final String BASE_URL = getBaseUrl();

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
}
