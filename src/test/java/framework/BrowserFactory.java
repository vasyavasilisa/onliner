package framework;

import org.openqa.selenium.WebDriver;

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
        throw new RuntimeException();
    }

}
