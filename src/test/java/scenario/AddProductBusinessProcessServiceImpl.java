package scenario;

import io.gatling.javaapi.core.ChainBuilder;

import java.time.Duration;

import static core.constant.ApiPayloadConstant.PRODUCT_NAMES_CSV_FILE_PATH;
import static core.constant.BusinessProcess.BP1;
import static core.controller.ProductController.addProduct;
import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.feed;

public class AddProductBusinessProcessServiceImpl implements BusinessProcessService {
    @Override
    public String getBusinessProcessName() {
        return BP1.getBusinessProcessFullName();
    }

    @Override
    public ChainBuilder getScenario(Duration thinkTime) {
        return feed(csv(PRODUCT_NAMES_CSV_FILE_PATH).circular())
                .exec(addProduct())
                .pause(thinkTime);
    }
}
