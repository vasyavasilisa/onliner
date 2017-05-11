package onliner.forms;



import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;



import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by USER on 08.05.2017.
 */
public class CategoriesPage {

    private WebDriver driver;
    By caption= By.className("schema-header__title");

    public CategoriesPage(WebDriver driver) {
        this.driver = driver;


    }


    public String getCategoryName(){
        Wait wait = new FluentWait(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo =(WebElement) wait.until(new Function<WebDriver,WebElement>() {

            public WebElement apply(WebDriver driver) {

                return driver.findElement(caption);

            }

        });

            return foo.getText();
    }

public void returnOnStartPage() {
    driver.navigate().back();
    driver.navigate().refresh();


}


}
