package com.a1qa.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by USER on 08.05.2017.
 */
public class LoginPage{


        By login = By.xpath("//input[@placeholder='Ник или e-mail']");
        By password = By.xpath("//input[@placeholder='Пароль']");
        // By аccount =By.cssSelector(".auth-box__input");
        By loginButtonLocator=By.cssSelector(".auth-box__auth-submit");
        By authError=By.cssSelector(".auth-box__line--error");
// By userName=By.cssSelector(".user-name");

private final WebDriver driver;

public LoginPage(WebDriver driver) {
        this.driver = driver;

       /* if (!"Login".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the login page");
        }*/
        }

public LoginPage typeUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(login));
        driver.findElement(login).sendKeys(username);
        return this;
        }

public LoginPage typePassword(String pass) {
        driver.findElement(password).sendKeys(pass);
        return this;
        }

public void submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        // return new StartPage(driver);
        }

public LoginPage loginExpectingFailure(String username, String password) {
        typeUsername(username);
        typePassword(password);
        submitLogin();
        return new LoginPage(driver);
        }

public StartPage loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);
        submitLogin();
        return new StartPage(driver);
        }


public boolean isErrorExist(){
        boolean enable=driver.findElement(authError).isEnabled();
        if(!enable){
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        }

        return driver.findElement(authError).isEnabled();
        }
}
