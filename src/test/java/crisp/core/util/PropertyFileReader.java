package crisp.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
    private static final Logger logger = LoggerFactory.getLogger(PropertyFileReader.class);

    public static String getPropertyValue(String propertyName) {
        String propertyValue = null;

        try (InputStream inputStream = PropertyFileReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

        return propertyValue;
    }
}
