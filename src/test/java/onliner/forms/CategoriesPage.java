package onliner.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by USER on 08.05.2017.
 */
public class CategoriesPage {

    private final WebDriver driver;
    By textLinkLocator=By.linkText("Новости");
    By caption= By.className("schema-header__title");

    public CategoriesPage(WebDriver driver) {
        this.driver = driver;


    }


    public String getUrl(){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getCurrentUrl();
    }

public StartPage returnOnStartPage() {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    WebElement element = driver.findElement(textLinkLocator);
    element.click();
    return new StartPage(driver);
}


}
