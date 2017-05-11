package onliner.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Created by USER on 08.05.2017.
 */
public class LoginPage{

        By login = By.xpath("//input[@placeholder='Ник или e-mail']");
        By password = By.xpath("//input[@placeholder='Пароль']");
        By loginButtonLocator=By.cssSelector(".auth-box__auth-submit");


private WebDriver driver;

public LoginPage(WebDriver driver) {
        this.driver = driver;

        }

public void typeUsername(String username) {

        driver.findElement(login).sendKeys(username);
        }

public void typePassword(String pass) {
        driver.findElement(password).sendKeys(pass);

        }

public void submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        }



public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(login));
        typeUsername(username);
        typePassword(password);
        submitLogin();
        }



}
