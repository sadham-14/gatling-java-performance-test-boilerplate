package scenario;

import io.gatling.javaapi.core.ChainBuilder;

import java.time.Duration;

import static core.constant.ApiPayloadConstant.PRODUCT_NAMES_CSV_FILE_PATH;
import static core.constant.BusinessProcess.BP2;
import static core.controller.ProductController.updateProduct;
import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.feed;

public class UpdateProductBusinessProcessServiceImpl implements BusinessProcessService {
    @Override
    public String getBusinessProcessName() {
        return BP2.getBusinessProcessFullName();
    }

    @Override
    public ChainBuilder getScenario(Duration thinkTime) {
        return feed(csv(PRODUCT_NAMES_CSV_FILE_PATH).circular())
                .exec(updateProduct())
                .pause(thinkTime);
    }
}
