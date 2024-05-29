import core.constant.BusinessProcess;
import core.constant.PerformanceTestType;
import io.gatling.javaapi.core.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scenario.BP1SimulationImpl;
import scenario.BP2SimulationImpl;
import scenario.BP3SimulationImpl;
import scenario.BPSimulationService;
import scenario.breakpoint.BP1BreakpointSimulationImpl;
import scenario.breakpoint.BP2BreakpointSimulationImpl;
import scenario.breakpoint.BP3BreakpointSimulationImpl;

import static core.constant.BusinessProcess.BP1;
import static core.constant.PerformanceTestType.LOAD;
import static core.constant.TestConfiguration.*;
import static core.util.HttpProtocol.getGenericHttpProtocol;

public class PerformanceTestRunner extends Simulation {
    private final Logger logger = LoggerFactory.getLogger(PerformanceTestRunner.class);

    public PerformanceTestRunner() {
        final String businessProcessString = System.getProperty("bp", BP1.name()); // Default is BP1
        final String testTypeString = System.getProperty("testType", LOAD.name()); // Default is Load

        BusinessProcess businessProcess = BusinessProcess.valueOf(businessProcessString);
        PerformanceTestType performanceTestType = PerformanceTestType.valueOf(testTypeString);

        logger.debug(
                "Starting the {} test for the business process: {}",
                testTypeString, businessProcess.getBusinessProcessFullName()
        );

        BPSimulationService testScenario = selectTest(businessProcess, performanceTestType);
        setUp(
                testScenario
                        .getPopulationBuilder()
                        .protocols(getGenericHttpProtocol())
        );
    }

    private BPSimulationService selectTest(BusinessProcess businessProcess, PerformanceTestType testType) {
        switch (businessProcess) {
            case BP1:
                return createTestScenario(BP1_VU, testType, BP1SimulationImpl.class, BP1BreakpointSimulationImpl.class);
            case BP2:
                return createTestScenario(BP2_VU, testType, BP2SimulationImpl.class, BP2BreakpointSimulationImpl.class);
            case BP3:
                return createTestScenario(BP3_VU, testType, BP3SimulationImpl.class, BP3BreakpointSimulationImpl.class);
            default:
                String errorMessage = "Business process '" + businessProcess.name() + "' has not been implemented.";
                logger.error(errorMessage);
                throw new RuntimeException(errorMessage);
        }
    }

    private BPSimulationService createTestScenario(int vus, PerformanceTestType performanceTestType,
                                                   Class<? extends BPSimulationService> testClass,
                                                   Class<? extends BPSimulationService> breakpointClass) {
        try {
            switch (performanceTestType) {
                case LOAD:
                    return testClass.getConstructor(int.class, int.class, int.class, int.class)
                            .newInstance(vus, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                                    DEFAULT_THINK_TIME_IN_SECONDS, LOAD_TEST_DURATION_IN_SECONDS);
                case STRESS:
                    return testClass.getConstructor(int.class, int.class, int.class, int.class)
                            .newInstance(vus, STRESS_TEST_RAMP_UP_TIME_IN_SECONDS,
                                    STRESS_TEST_THINK_TIME_IN_SECONDS, STRESS_TEST_DURATION_IN_SECONDS);
                case ENDURANCE:
                    return testClass.getConstructor(int.class, int.class, int.class, int.class)
                            .newInstance(vus, DEFAULT_RAMP_UP_TIME_IN_SECONDS,
                                    DEFAULT_THINK_TIME_IN_SECONDS, ENDURANCE_TEST_DURATION_IN_SECONDS);
                case BREAKPOINT:
                    return breakpointClass.getConstructor(int.class, int.class, int.class)
                            .newInstance(MAX_VU, MAX_VU * VU_RAMP_UP_RATE, DEFAULT_THINK_TIME_IN_SECONDS);
                default:
                    throw new RuntimeException("Performance test type '" + performanceTestType.name()
                            + "' has not been implemented.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to create test scenario";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
