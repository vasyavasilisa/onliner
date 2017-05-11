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





    @Parameters({ "login", "password" })
   @Test
    public void Onliner_shouldWork(String login, String password) throws IOException {

        CommonFunctions commonFunctions = new CommonFunctions();
        Properties properties = commonFunctions.readProperties();
        String br = properties.getProperty("brouser_type");
        WebDriver driver= BrowserFactory.getFactory(br).getDriver();
        String mainPage = properties.getProperty("main_page_url");

        StartPage startPage= new StartPage(driver);
        startPage.navigateToMainPage(mainPage);
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

        startPage.closeBrowser();


    }

}
