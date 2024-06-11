import core.constant.BusinessProcess;
import core.constant.PerformanceTestType;
import io.gatling.javaapi.core.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scenario.*;

import static core.constant.BusinessProcess.BP1;
import static core.constant.PerformanceTestType.LOAD;
import static core.constant.TestConfiguration.*;
import static core.util.HttpProtocol.getGenericHttpProtocol;
import static java.time.Duration.ofSeconds;

public class PerformanceTestRunner extends Simulation {
    public PerformanceTestRunner() {
        final String businessProcessString = System.getProperty("bp", BP1.name()); // Setting the default business process to BP1
        final String testTypeString = System.getProperty("testType", LOAD.name()); // Setting the default test type to Load test

        BusinessProcess businessProcess = BusinessProcess.valueOf(businessProcessString);
        PerformanceTestType performanceTestType = PerformanceTestType.valueOf(testTypeString);

        Logger logger = LoggerFactory.getLogger(PerformanceTestRunner.class);
        logger.debug(
                "Starting the {} test for the business process: {}",
                testTypeString, businessProcess.getBusinessProcessFullName()
        );

        BusinessProcessSimulation testScenario = selectTest(businessProcess, performanceTestType);
        setUp(
                testScenario
                        .getPopulationBuilder()
                        .protocols(getGenericHttpProtocol())
        );
    }

    private BusinessProcessSimulation selectTest(BusinessProcess businessProcess,
                                                 PerformanceTestType performanceTestType) {
        BusinessProcessService service;
        int virtualUserCount;

        switch (businessProcess) {
            case BP1:
                service = new AddProductBusinessProcessServiceImpl();
                virtualUserCount = BP1_VU;
                break;
            case BP2:
                service = new UpdateProductBusinessProcessServiceImpl();
                virtualUserCount = BP2_VU;
                break;
            case BP3:
                service = new GetAllProductsBusinessProcessServiceImpl();
                virtualUserCount = BP3_VU;
                break;
            default:
                throw new RuntimeException("Business process '" + businessProcess.name()
                        + "' has not been implemented.");
        }

        return createTestScenario(virtualUserCount, service, performanceTestType);
    }

    private BusinessProcessSimulation createTestScenario(int virtualUserCount, BusinessProcessService service,
                                                         PerformanceTestType performanceTestType) {
        try {
            switch (performanceTestType) {
                case LOAD:
                    return new BusinessProcessSimulation(
                            virtualUserCount, DEFAULT_RAMP_UP_TIME, DEFAULT_THINK_TIME,
                            LOAD_TEST_DURATION, false, service
                    );
                case STRESS:
                    return new BusinessProcessSimulation(
                            virtualUserCount, STRESS_TEST_RAMP_UP_TIME, STRESS_TEST_THINK_TIME,
                            STRESS_TEST_DURATION, false, service
                    );
                case ENDURANCE:
                    return new BusinessProcessSimulation(
                            virtualUserCount, DEFAULT_RAMP_UP_TIME, DEFAULT_THINK_TIME,
                            ENDURANCE_TEST_DURATION, false, service
                    );
                case BREAKPOINT:
                    return new BusinessProcessSimulation(
                            MAX_VIRTUAL_USERS, ofSeconds(MAX_VIRTUAL_USERS / VIRTUAL_USER_RAMP_UP_RATE),
                            DEFAULT_THINK_TIME, ofSeconds(0), true, service
                    );
                default:
                    throw new RuntimeException("Performance test type '" + performanceTestType.name()
                            + "' has not been implemented.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create the performance test scenario");
        }
    }
}