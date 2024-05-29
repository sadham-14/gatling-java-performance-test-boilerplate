package core.constant;

public class TestConfiguration {
    // Virtual Users
    public static final int BP1_VU = 60;
    public static final int BP2_VU = 50;
    public static final int BP3_VU = 40;

    // Think Times
    public static final int DEFAULT_THINK_TIME_IN_SECONDS = 30;
    public static final int STRESS_TEST_THINK_TIME_IN_SECONDS = DEFAULT_THINK_TIME_IN_SECONDS / 2;

    // Ramp Up Times
    public static final int DEFAULT_RAMP_UP_TIME_IN_SECONDS = 600;
    public static final int STRESS_TEST_RAMP_UP_TIME_IN_SECONDS = DEFAULT_RAMP_UP_TIME_IN_SECONDS / 2;

    // Test Durations
    public static final int LOAD_TEST_DURATION_IN_SECONDS = 3600 + DEFAULT_RAMP_UP_TIME_IN_SECONDS;
    public static final int STRESS_TEST_DURATION_IN_SECONDS = 3600 + STRESS_TEST_RAMP_UP_TIME_IN_SECONDS;
    public static final int ENDURANCE_TEST_DURATION_IN_SECONDS = 86400 + DEFAULT_RAMP_UP_TIME_IN_SECONDS;

    // Breakpoint Test
    public static final int MAX_VU = 20000;
    public static final int VU_RAMP_UP_RATE = 20;
}
