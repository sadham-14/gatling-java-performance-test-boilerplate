package scenario;

import io.gatling.javaapi.core.ChainBuilder;

import java.time.Duration;

public interface BusinessProcessService {
    String getBusinessProcessName();

    ChainBuilder getScenario(Duration thinkTime);
}
