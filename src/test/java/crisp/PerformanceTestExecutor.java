package crisp;

import crisp.core.constant.BusinessProcess;
import crisp.core.constant.PerformanceTestType;
import crisp.scenario.BP1Test;
import crisp.scenario.BP2Test;
import crisp.scenario.BP3Test;
import crisp.scenario.breakpoint.BP1BreakpointTest;
import crisp.scenario.breakpoint.BP2BreakpointTest;
import crisp.scenario.breakpoint.BP3BreakpointTest;
import io.gatling.javaapi.core.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static crisp.core.constant.BusinessProcess.*;
import static crisp.core.constant.PerformanceTestType.*;
import static crisp.core.constant.TestConfiguration.*;
import static crisp.core.util.HttpProtocol.getGenericHttpProtocol;

public class PerformanceTestExecutor extends Simulation {
    private final Logger logger = LoggerFactory.getLogger(PerformanceTestExecutor.class);

    {
        final String businessProcess = System.getProperty("bp", BP1.name()); // Default is BP1
        final String testType = System.getProperty("testType", LOAD.name()); // Default is Load


        switch (BusinessProcess.valueOf(businessProcess)) {
            case BP1:
                logger.debug("Start executing the business process: {}", BP1.getBusinessProcessFullName());
                runBp1Test(testType);
                break;

            case BP2:
                logger.debug("Start executing the business process: {}", BP2.getBusinessProcessFullName());
                runBp2Test(testType);
                break;

            case BP3:
                logger.debug("Start executing the business process: {}", BP3.getBusinessProcessFullName());
                runBp3Test(testType);
                break;

            default:
                throw new RuntimeException("Business process '" + businessProcess + "' has not been implemented.");
        }
    }

    private void runBp1Test(String testType) {
        switch (PerformanceTestType.valueOf(testType)) {
            case LOAD:
                logger.debug("{} test is running", LOAD.name());
                BP1Test bp1LoadTest = new BP1Test(BP1_VU, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                        DEFAULT_THINK_TIME_IN_SECONDS, LOAD_TEST_DURATION_IN_SECONDS);

                setUp(bp1LoadTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case STRESS:
                logger.debug("{} test is running", STRESS.name());
                BP1Test bp1StressTest = new BP1Test(BP1_VU, STRESS_TEST_RAMP_UP_TIME_IN_SECONDS,
                        STRESS_TEST_THINK_TIME_IN_SECONDS, STRESS_TEST_DURATION_IN_SECONDS);
                setUp(bp1StressTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case ENDURANCE:
                logger.debug("{} test is running", ENDURANCE.name());
                BP1Test bp1EnduranceTest = new BP1Test(BP1_VU, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                        DEFAULT_THINK_TIME_IN_SECONDS, ENDURANCE_TEST_DURATION_IN_SECONDS);
                setUp(bp1EnduranceTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case BREAKPOINT:
                logger.debug("{} test is running", BREAKPOINT.name());
                BP1BreakpointTest bp1BreakpointTest = new BP1BreakpointTest(MAX_VU,
                        MAX_VU * VU_RAMP_UP_RATE, DEFAULT_THINK_TIME_IN_SECONDS);
                setUp(bp1BreakpointTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            default:
                throw new RuntimeException("Performance test type '" + testType + "' has not been implemented.");
        }
    }

    private void runBp2Test(String testType) {
        switch (PerformanceTestType.valueOf(testType)) {
            case LOAD:
                logger.debug("{} test is running", LOAD.name());
                BP2Test bp2LoadTest = new BP2Test(BP2_VU, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                        DEFAULT_THINK_TIME_IN_SECONDS, LOAD_TEST_DURATION_IN_SECONDS);

                setUp(bp2LoadTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case STRESS:
                logger.debug("{} test is running", STRESS.name());
                BP2Test bp2StressTest = new BP2Test(BP2_VU, STRESS_TEST_RAMP_UP_TIME_IN_SECONDS,
                        STRESS_TEST_THINK_TIME_IN_SECONDS, STRESS_TEST_DURATION_IN_SECONDS);
                setUp(bp2StressTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case ENDURANCE:
                logger.debug("{} test is running", ENDURANCE.name());
                BP2Test bp2EnduranceTest = new BP2Test(BP2_VU, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                        DEFAULT_THINK_TIME_IN_SECONDS, ENDURANCE_TEST_DURATION_IN_SECONDS);
                setUp(bp2EnduranceTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case BREAKPOINT:
                logger.debug("{} test is running", BREAKPOINT.name());
                BP2BreakpointTest bp2BreakpointTest = new BP2BreakpointTest(MAX_VU,
                        MAX_VU * VU_RAMP_UP_RATE, DEFAULT_THINK_TIME_IN_SECONDS);
                setUp(bp2BreakpointTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            default:
                throw new RuntimeException("Performance test type '" + testType + "' has not been implemented.");
        }
    }

    private void runBp3Test(String testType) {
        switch (PerformanceTestType.valueOf(testType)) {
            case LOAD:
                logger.debug("{} test is running", LOAD.name());
                BP3Test bp3LoadTest = new BP3Test(BP3_VU, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                        DEFAULT_THINK_TIME_IN_SECONDS, LOAD_TEST_DURATION_IN_SECONDS);
                setUp(bp3LoadTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case STRESS:
                logger.debug("{} test is running", STRESS.name());
                BP3Test bp3StressTest = new BP3Test(BP3_VU, STRESS_TEST_RAMP_UP_TIME_IN_SECONDS,
                        STRESS_TEST_THINK_TIME_IN_SECONDS, STRESS_TEST_DURATION_IN_SECONDS);
                setUp(bp3StressTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case ENDURANCE:
                logger.debug("{} test is running", ENDURANCE.name());
                BP3Test bp3EnduranceTest = new BP3Test(BP3_VU, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                        DEFAULT_THINK_TIME_IN_SECONDS, ENDURANCE_TEST_DURATION_IN_SECONDS);
                setUp(bp3EnduranceTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            case BREAKPOINT:
                logger.debug("{} test is running", BREAKPOINT.name());
                BP3BreakpointTest bp3BreakpointTest = new BP3BreakpointTest(MAX_VU,
                        MAX_VU * VU_RAMP_UP_RATE, DEFAULT_THINK_TIME_IN_SECONDS);
                setUp(bp3BreakpointTest.getPopulationBuilder().protocols(getGenericHttpProtocol()));
                break;

            default:
                throw new RuntimeException("Performance test type '" + testType + "' has not been implemented.");
        }
    }
}
