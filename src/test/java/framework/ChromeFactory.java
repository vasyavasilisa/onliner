package framework;

import framework.services.CommonFunctions;
import org.openqa.selenium.chrome.ChromeDriver;

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
        CommonFunctions commonFunctions = new CommonFunctions();
        System.setProperty(CHROME_DRIVER_NAME,  commonFunctions.readProperties().getProperty(DRIVER_LOCATION_KEY));

    }

    @Override
    public ChromeDriver getDriver() {
        return new ChromeDriver();
    }
}
