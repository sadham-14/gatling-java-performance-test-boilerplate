package core.constant;

import java.time.Duration;

import static java.time.Duration.*;

public class TestConfiguration {
    // Virtual Users
    public static final int BP1_VU = 60;
    public static final int BP2_VU = 50;
    public static final int BP3_VU = 40;

    // Think Times
    public static final Duration DEFAULT_THINK_TIME = ofSeconds(30);
    public static final Duration STRESS_TEST_THINK_TIME = DEFAULT_THINK_TIME.dividedBy(2);

    // Ramp Up Times
    public static final Duration DEFAULT_RAMP_UP_TIME = ofMinutes(10);
    public static final Duration STRESS_TEST_RAMP_UP_TIME = DEFAULT_RAMP_UP_TIME.dividedBy(2);

    // Test Durations
    public static final Duration LOAD_TEST_DURATION = ofHours(1).plus(DEFAULT_RAMP_UP_TIME);
    public static final Duration STRESS_TEST_DURATION = ofHours(1).plus(STRESS_TEST_RAMP_UP_TIME);
    public static final Duration ENDURANCE_TEST_DURATION = ofHours(24).plus(DEFAULT_RAMP_UP_TIME);

    // Breakpoint Test
    public static final int MAX_VIRTUAL_USERS = 2000;
    public static final int VIRTUAL_USER_RAMP_UP_RATE = 1;
}
