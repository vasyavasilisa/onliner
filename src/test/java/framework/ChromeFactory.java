package framework;

import framework.services.CommonFunctions;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by USER on 09.05.2017.
 */
public class ChromeFactory extends BrowserFactory {

    private static final String CHROME_DRIVER_NAME= "webdriver.chrome.driver";

    private static final String DRIVER_LOCATION_KEY_WIN = "driver_chrome_location_win";

    private static final String DRIVER_LOCATION_KEY_LIN = "driver_chrome_location_lin";

    private static final ChromeFactory instance = new ChromeFactory();

    public static synchronized ChromeFactory getInstance() {
        return instance;
    }

    private ChromeFactory() {
        CommonFunctions commonFunctions = new CommonFunctions();
        String osType = System.getProperty("os.name").toLowerCase();
        if(osType.indexOf( "win" ) >= 0){
            System.setProperty(CHROME_DRIVER_NAME, commonFunctions.readProperties().getProperty(DRIVER_LOCATION_KEY_WIN));
        }
        else if(osType.indexOf( "nix") >=0 || osType.indexOf( "nux") >=0) {
            System.setProperty(CHROME_DRIVER_NAME, commonFunctions.readProperties().getProperty(DRIVER_LOCATION_KEY_LIN));
        }

    }

    @Override
    public ChromeDriver getDriver() {
        return new ChromeDriver();
    }
}
