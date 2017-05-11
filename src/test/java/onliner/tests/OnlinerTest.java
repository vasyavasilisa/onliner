package onliner.tests;



import framework.BrowserFactory;
import framework.services.CommonFunctions;
import onliner.forms.CategoriesPage;
import onliner.forms.LoginPage;
import onliner.forms.StartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.Properties;


/**
 * Created by USER on 08.05.2017.
 */
public class OnlinerTest {

    private static final String MAINPAGE_URL_KEY ="main_page_url";
    private String BROWSER_TYPE_KEY = "brouser_type";
    private  WebDriver driver;



    @Parameters({ "login", "password" })
   @Test
    public void Onliner_shouldWork(String login, String password) throws IOException {

        CommonFunctions commonFunctions = new CommonFunctions();
        Properties properties = commonFunctions.readProperties();
        String br = properties.getProperty(BROWSER_TYPE_KEY);
        driver= BrowserFactory.getFactory(br).getDriver();
        driver.manage().window().maximize();
        String mainPage = properties.getProperty(MAINPAGE_URL_KEY);
        driver.navigate().to(mainPage);

        StartPage startPage= new StartPage(driver);
        Assert.assertEquals(startPage.getUrl(),mainPage);
        startPage.clickOnEnter();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login,password);
        Assert.assertEquals(startPage.isExitExist(),true);
        startPage.clickOnRandomTheme();

        CategoriesPage categoriesPage = new CategoriesPage(driver);
        Assert.assertEquals(categoriesPage.getCategoryName(),startPage.getTextOfRandTheme());
        categoriesPage.returnOnStartPage();
        startPage.findAndSaveOpinions();
        startPage.logout();
        Assert.assertTrue(startPage.isEnterExist());


    }

}
