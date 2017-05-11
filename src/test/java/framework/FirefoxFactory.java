package framework;

import framework.services.CommonFunctions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by USER on 09.05.2017.
 */
public class FirefoxFactory extends BrowserFactory {

    private static final String GECKO_DRIVER_NAME = "webdriver.gecko.driver";

    private static final String DRIVER_LOCATION_KEY_LIN = "driver_firefox_location_lin";

    private static final String DRIVER_LOCATION_KEY_WIN = "driver_firefox_location_win";

    private static final FirefoxFactory instance = new FirefoxFactory();

    public static synchronized FirefoxFactory getInstance() {
        return instance;
    }

    private FirefoxFactory() {
        CommonFunctions commonFunctions = new CommonFunctions();
        String osType = System.getProperty("os.name").toLowerCase();
        if(osType.indexOf( "win" ) >= 0){
            System.setProperty(GECKO_DRIVER_NAME, commonFunctions.readProperties().getProperty(DRIVER_LOCATION_KEY_WIN));
        }
         else if(osType.indexOf( "nix") >=0 || osType.indexOf( "nux") >=0) {
            System.setProperty(GECKO_DRIVER_NAME, commonFunctions.readProperties().getProperty(DRIVER_LOCATION_KEY_LIN));
        }

    }
@Override
    public FirefoxDriver getDriver() {
   /* File pathToBinary = new File("C:\\Users\\USER\\Desktop\\FirefoxPortable\\App\\Firefox\\firefox.exe");
    FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
    FirefoxProfile firefoxProfile = new FirefoxProfile();
    firefoxProfile.setPreference("webdriver.load.strategy","unstable");
    FirefoxOptions options= new FirefoxOptions().setBinary(ffBinary).setProfile(firefoxProfile);*/
       return new FirefoxDriver(/*options*/);
    }




}
