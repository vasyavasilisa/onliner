package onliner.tests;



import framework.BrowserFactory;
import framework.services.PropertyService;
import onliner.forms.CategoriesPage;
import onliner.forms.LoginPage;
import onliner.forms.StartPage;
import org.openqa.selenium.WebDriver;
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


   // private String mainPage = "http://onliner.by/";
    private String BROWSER_TYPE_KEY = "brouser_type";

   /* @BeforeClass
    public static void getBrouser() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();
        fis = new FileInputStream("src/test/resources/brouser.properties");
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



    }*/


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

        // driver.findElement(By.cssSelector(".auth-bar__item")).click();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        startPage.clickOnEnter();
        LoginPage loginPage = new LoginPage(driver);
        //  driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        Assert.assertEquals(loginPage.loginAs(login,password).isExitExist(),true);///////Успешная авторизация
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
