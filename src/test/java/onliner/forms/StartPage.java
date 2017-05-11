package onliner.forms;

import framework.services.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by USER on 08.05.2017.
 */
public class StartPage {

    By enterButtonLocator=By.cssSelector(".auth-bar__item");
    By exitLocator=By.xpath("//ul[@class='user-bar cfix']/li/a[@class='exit']");
    By liPopThemesLocator = By.cssSelector(".project-navigation__item");
    By ulPopThemesLocator = By.cssSelector(".project-navigation__list");
    By divOpinionsLocator=By.cssSelector(".b-main-page-grid-4");
    By spanThemeClassLocator=By.cssSelector(".project-navigation__sign");
    private String textOfRandTheme;


    private WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    public String getUrl(){
        return driver.getCurrentUrl();

    }

    public void clickOnEnter(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonLocator));
        System.out.println(driver.findElement(enterButtonLocator).getText());
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(enterButtonLocator);
       elem.click();


    }

    public boolean isExitExist(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(exitLocator));
        return driver.findElement(exitLocator).isEnabled();
    }

    public List<WebElement> getPopThemes(){
        List<WebElement> webElementList=  driver.findElements(liPopThemesLocator);
        return webElementList;
    }


    public int generateRandDigitForTheme(){
        int size=getPopThemes().size();
        int max=size;
        int randThemeInd=0;
        while(true) {
             randThemeInd = (int) (Math.random() * max);
            if(driver.findElements(liPopThemesLocator).get(randThemeInd).isDisplayed()){
                break;
            }
        }
        return randThemeInd;
    }

    public String getTextOfRandTheme(){
        return textOfRandTheme;
    }

    public void clickOnRandomTheme(){
        int randInd=generateRandDigitForTheme();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ulPopThemesLocator));
        List<WebElement> webElementList=getPopThemes();
        WebElement liOfTheme = webElementList.get(randInd);
        textOfRandTheme= liOfTheme.findElement(spanThemeClassLocator).getText();
        liOfTheme.click();
    }


    public void findAndSaveOpinions() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(divOpinionsLocator));
        CommonFunctions commonFunctions = new CommonFunctions();
        String allElem =  driver.getPageSource();
        Set<String> classes=commonFunctions.findClassesForOpinions(allElem);
        List<String> data = new ArrayList<>();
        for(String cl:classes) {
            List<WebElement> l = driver.findElements(By.cssSelector("." + cl + ""));
            for(WebElement el:l){
                data.add(el.getText().split("\n")[0]);
            }
        }
        commonFunctions.saveOpinionsInCsv(data);

    }


    public void logout(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.elementToBeClickable(exitLocator));
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(exitLocator);
        action.doubleClick(elem).build().perform();

    }

    public boolean isEnterExist(){
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(enterButtonLocator));
            return driver.findElement(enterButtonLocator).isEnabled();
    }

}
