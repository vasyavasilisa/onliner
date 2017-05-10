package onliner.tests;



import framework.BrowserFactory;
import framework.services.PropertyService;
import onliner.forms.CategoriesPage;
import onliner.forms.LoginPage;
import onliner.forms.StartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.io.IOException;


/**
 * Created by USER on 08.05.2017.
 */
public class OnlinerTest {

    private static final String MAINPAGE_URL_KEY ="main_page_url";
    private  WebDriver driver=null;
    private String BROWSER_TYPE_KEY = "brouser_type";



    @Parameters({ "login", "password" })
   @Test
    public void Onliner_shouldWork(String login, String password) throws IOException {

        PropertyService propertyService = new PropertyService();
       String br = propertyService.readProperties().getProperty(BROWSER_TYPE_KEY);
        driver= BrowserFactory.getFactory(br).getDriver();
        driver.manage().window().maximize();
        String mainPage = propertyService.readProperties().getProperty(MAINPAGE_URL_KEY);
        driver.navigate().to(mainPage);
        StartPage startPage= new StartPage(driver);
        Assert.assertEquals(startPage.isGetRightUrl(),true);///////////////Главная страница открылась
        startPage.clickOnEnter();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.loginAs(login,password).isExitExist(),true);///////Успешная авторизация
        CategoriesPage categoriesPage =  startPage.clickOnRandomTheme();
        Assert.assertEquals(categoriesPage.getUrl(),startPage.getUrlOfRandTheme());
        startPage=categoriesPage.returnOnStartPage();
        startPage.findOpinions();
        Assert.assertTrue(startPage.logout().isEnterExist());


    }

}
