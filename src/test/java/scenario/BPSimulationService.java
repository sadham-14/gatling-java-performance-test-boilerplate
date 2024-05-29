package scenario;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.PopulationBuilder;

import static core.controller.AuthController.getAuthenticationToken;
import static io.gatling.javaapi.core.CoreDsl.exec;

public interface BPSimulationService {
    PopulationBuilder getPopulationBuilder();

    default ChainBuilder authenticateUser() {
        return exec(getAuthenticationToken())
                .exitHereIfFailed();
    }
}
