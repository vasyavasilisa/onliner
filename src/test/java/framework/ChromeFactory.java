package framework;

import framework.services.PropertyService;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by USER on 09.05.2017.
 */
public class ChromeFactory extends BrowserFactory {

    private static final String CHROME_DRIVER_NAME = "webdriver.chrome.driver";

    private static final String DRIVER_LOCATION_KEY = "driver_chrome_location";

    private static final ChromeFactory instance = new ChromeFactory();

    public static synchronized ChromeFactory getInstance() {
        return instance;
    }

    private ChromeFactory() {
        PropertyService propertyService = new PropertyService();

        System.setProperty(CHROME_DRIVER_NAME, propertyService.readProperties().getProperty(DRIVER_LOCATION_KEY));

    }

    @Override
    public ChromeDriver getDriver() {
        return new ChromeDriver();
    }
}
