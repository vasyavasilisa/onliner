package onliner.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by USER on 08.05.2017.
 */
public class StartPage {

    private String currentUrl="https://www.onliner.by/";
    By enterButtonLocator=By.cssSelector(".auth-bar__item");
    By exit=By.xpath("//ul[@class='user-bar cfix']/li/a[@class='exit']");
    By classPopThemes = By.cssSelector(".project-navigation__item");
   /* By urlLocator=By.tagName("a");*/
    String hrefAttribute="href";
    By spanThemeClass=By.cssSelector(".project-navigation__sign");
    private String textOfRandTheme;
    private int randDigit;

    private final WebDriver driver;

    public StartPage(WebDriver driver) {
        this.driver = driver;


    }

    public boolean isGetRightUrl(){
        return (driver.getCurrentUrl().equals(currentUrl));

    }

    public void clickOnEnter(){

       /* Actions action1 = new Actions(driver);
        WebElement elem1 = driver.findElement(By.className("_u"));
        action1.moveToElement(elem1).build().perform();*/

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(enterButtonLocator));
        System.out.println(driver.findElement(enterButtonLocator).getText());
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(enterButtonLocator);
       elem.click();


    }

    public boolean isExitExist(){
        boolean enable=driver.findElement(exit).isEnabled();
        if(!enable){
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        return driver.findElement(exit).isEnabled();
    }

    public List<WebElement> getPopThemes(){
        List<WebElement> webElementList=  driver.findElements
                (classPopThemes);
        return webElementList;
    }


    public int generateRandDigit(){
        int size=getPopThemes().size();
        int max=size;
        int randThemeInd=(int)( Math.random() * 12);
        setRandDigit(randThemeInd);
        return randThemeInd;
    }

    public void setRandDigit(int randDigit) {
        this.randDigit = randDigit;
    }
    public String getTextOfRandTheme(){
        return textOfRandTheme;
    }

    public void setTextOfRandTheme(String textOfRandTheme) {
        this.textOfRandTheme = textOfRandTheme;
    }

  /*  public String findUrlOfRandTheme(){
       return getPopThemes().get( randDigit).findElement(urlLocator).getAttribute(hrefAttribute);
    }*/

    public CategoriesPage clickOnRandomTheme(){
        generateRandDigit();
        List<WebElement> webElementList=getPopThemes();
       // WebElement elem = getPopThemes().get(randDigit);

       // String spanClass=getPopThemes().get(randDigit).findElement(urlLocator).getAttribute(hrefAttribute);
        textOfRandTheme=getPopThemes().get(randDigit).findElement(spanThemeClass).getText();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webElementList.get( randDigit)));
        webElementList.get( randDigit).click();
        return new CategoriesPage(driver);
    }


    public void findOpinions() throws IOException {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://www.onliner.by/"));
        System. out.println(driver.getCurrentUrl());
       Pattern p = Pattern.compile("^[b]\\-[o]\\w+[s]\\-[m]\\w+[n]\\-[2]\\w\\w[t]\\w+[t]");
        String nameClass= null;
        String allElem =  driver.getPageSource();//.contains("b-opinions-main-2__text");
        String [] elemPart=allElem.split("<");
        for(String elem:elemPart){
            String [] findClass=elem.split("\\\"");
            for (String cssClass:findClass){
               if( cssClass.startsWith("b-opinions")){
                   System.out.println(cssClass);
               }
                Matcher m = p.matcher(cssClass);
                if(m.matches()){
                    nameClass=cssClass;
                    break;
                }
            }
        }
             List<String> data = new ArrayList<>();
            List<WebElement> webElementListOpinions =  driver.findElements
                (By.cssSelector("."+nameClass+""));
       for(WebElement webElement:webElementListOpinions){

            data.add(webElement.getText());
        }
        Path path = Paths.get("", "opinions.csv");
        Files.write(path, data);
    }


    public StartPage logout(){

       /* WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.elementToBeClickable(By.className("_u")));
        Actions action1 = new Actions(driver);
        WebElement elem1 = driver.findElement(By.className("_u"));
        action1.moveToElement(elem1).build().perform();*/
        WebDriverWait wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.elementToBeClickable(exit));
        String attr= driver.findElement(exit).getAttribute("href");
        System.out.println(attr);
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(exit);
        action.doubleClick(elem).build().perform();

        return this;
    }

    public boolean isEnterExist(){
        boolean displayed=driver.findElement(enterButtonLocator).isEnabled();
        if(!displayed){
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(enterButtonLocator));
        }

        return driver.findElement(enterButtonLocator).isEnabled();
    }

}
