package crisp.core.constant;

import static crisp.core.constant.BusinessProcess.*;

public class TestConfiguration {
    // Virtual users
    public static final int BP1_VU = getVirtualUserCount(BP1);
    public static final int BP2_VU = getVirtualUserCount(BP2);
    public static final int BP3_VU = getVirtualUserCount(BP3);

    // Test durations
    public static final int LOAD_TEST_DURATION_IN_SECONDS = 3600;
    public static final int STRESS_TEST_DURATION_IN_SECONDS = 3600;
    public static final int ENDURANCE_TEST_DURATION_IN_SECONDS = 86400;

    // Think times
    public static final int DEFAULT_THINK_TIME_IN_SECONDS = 60;
    public static final int STRESS_TEST_THINK_TIME_IN_SECONDS = DEFAULT_THINK_TIME_IN_SECONDS / 2;

    // Ramp up times
    public static final int DEFAULT_RAMP_UP_TIME_IN_SECONDS = 600;
    public static final int STRESS_TEST_RAMP_UP_TIME_IN_SECONDS = DEFAULT_RAMP_UP_TIME_IN_SECONDS / 2;

    // Breakpoint Test
    public static final int MAX_VU = 20000;
    public static final int VU_RAMP_UP_RATE = 20;

    private static int getVirtualUserCount(BusinessProcess businessProcess) {
        int virtualUsersCount;

        switch (businessProcess) {
            case BP1:
                virtualUsersCount = 15;
                break;
            case BP2:
                virtualUsersCount = 9;
                break;
            case BP3:
                virtualUsersCount = 6;
                break;
            default:
                throw new RuntimeException(businessProcess + " is not implemented.");
        }

        return virtualUsersCount;
    }
}
