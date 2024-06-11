package scenario;

import io.gatling.javaapi.core.ChainBuilder;

import java.time.Duration;

import static core.constant.BusinessProcess.BP3;
import static core.controller.ProductController.getAllProducts;
import static io.gatling.javaapi.core.CoreDsl.exec;

public class GetAllProductsBusinessProcessServiceImpl implements BusinessProcessService {
    @Override
    public String getBusinessProcessName() {
        return BP3.getBusinessProcessFullName();
    }

    @Override
    public ChainBuilder getScenario(Duration thinkTime) {
        return exec(getAllProducts())
                .pause(thinkTime);
    }
}
