package framework;

import framework.exceptions.UnSupportedStoradgeTypeException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by USER on 09.05.2017.
 */
public abstract class BrowserFactory {

    public abstract WebDriver getDriver();

    public static BrowserFactory getFactory(String type) {
        switch (type) {
            case "chrome": {
                return ChromeFactory.getInstance();
            }
            case "firefox": {
                return FirefoxFactory.getInstance();
            }
        }
        throw new UnSupportedStoradgeTypeException();
    }

}
