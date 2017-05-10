package framework;

import framework.services.PropertyService;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by USER on 09.05.2017.
 */
public class FirefoxFactory extends BrowserFactory {

    private static final String GECKO_DRIVER_NAME = "webdriver.gecko.driver";

    private static final String DRIVER_LOCATION_KEY = "driver_firefox_location";

    private static final FirefoxFactory instance = new FirefoxFactory();

    public static synchronized FirefoxFactory getInstance() {
        return instance;
    }

    private FirefoxFactory() {
        PropertyService propertyService = new PropertyService();
        System.setProperty(GECKO_DRIVER_NAME, propertyService.readProperties().getProperty(DRIVER_LOCATION_KEY));

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
