import com.a1qa.onliner.pages.CategoriesPage;
import com.a1qa.onliner.pages.LoginPage;
import com.a1qa.onliner.pages.StartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by USER on 08.05.2017.
 */
public class OnlinerTest {
    private static WebDriver driver=null;
    private String mainPage = "http://onliner.by/";

    @BeforeClass
    public static void getBrouser() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/main/resources/brouser.properties");
        property.load(fis);
        String br = property.getProperty("brouser_type");
        switch (br) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver",
                        property.getProperty("driver_chrome_location"));
                driver = new ChromeDriver();
                break;
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver",
                                property.getProperty("driver_firefox_location"));
                        File pathToBinary = new File("C:\\Users\\USER\\Desktop\\FirefoxPortable\\App\\Firefox\\firefox.exe");
                        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
                        FirefoxProfile firefoxProfile = new FirefoxProfile();
                        firefoxProfile.setPreference("webdriver.load.strategy","unstable");
                        FirefoxOptions options= new FirefoxOptions().setBinary(ffBinary).setProfile(firefoxProfile);
                     driver = new FirefoxDriver(options);
                        break;
                            case "IE":
                                System.setProperty("webdriver.chrome.driver",
                                        property.getProperty("driver_chrome_location"));
                                break;
                        }



    }




    @Test
    public void Onliner_shouldWork() throws IOException {

        driver.manage().window().maximize();
        driver.navigate().to(mainPage);
        StartPage startPage= new StartPage(driver);
        Assert.assertEquals(startPage.isGetRightUrl(),true);///////////////Главная страница открылась

        // driver.findElement(By.cssSelector(".auth-bar__item")).click();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        startPage.clickOnEnter();
        LoginPage loginPage = new LoginPage(driver);
        //  driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        Assert.assertEquals(loginPage.loginAs("vasilisademuanova2700@mail.ru","123456").isExitExist(),true);///////Успешная авторизация
        //Assert.assertTrue(loginPage.loginExpectingFailure("vasilisademuanova2700@mail.ru","1234566").isErrorExist());///////Успешная авторизация
       // startPage.clickOnRandomTheme();
        CategoriesPage categoriesPage =  startPage.clickOnRandomTheme();
       // System.out.println(categoriesPage.getUrl());
       Assert.assertEquals(categoriesPage.getUrl(),startPage.getUrlOfRandTheme());
        startPage=categoriesPage.returnOnStartPage();
       startPage.findOpinions();
        //startPage.logout();
      Assert.assertTrue(startPage.logout().isEnterExist());


    }

}
