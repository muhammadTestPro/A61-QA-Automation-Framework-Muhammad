import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.openqa.selenium.interactions.Actions;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    protected static WebDriver driver = null;
    protected WebDriverWait wait;
    protected ChromeOptions options;
    private String url = null;
    protected Actions action;
    protected FluentWait fluentWait;

    @DataProvider(name="LoginNegativeTestData")
    public Object[][] getDataFromDataProvider(){
        return new Object[][]{
                {"invalidEmail@email.com","Invalid"},   // Invalid Email Data
                {"",""},                               // Empty email and pwd
        };
    }

    public static WebDriver chooseBroswer(String browserName) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://192.168.1.104:4444/"; // Used for selenium grid

        try{
            switch (browserName){
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    return driver = new FirefoxDriver();
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    return driver = new EdgeDriver(edgeOptions);
                // Using Selenium Grid
                case "grid-edge":
                    caps.setCapability("browserName","MicrosoftEdge");
                    return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
                case "grid-firefox":
                    caps.setCapability("browserName","firefox");
                    return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
                case "grid-chrome":
                    caps.setCapability("browserName","chrome");
                    return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
                case "cloud":
                    return lambdaTest();
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    return driver = new ChromeDriver(chromeOptions);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @return WebDriver - the current instance of WebDriver associated with the current thread
     */
    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    @BeforeSuite
    static void setupClass() {
        //WebDriverManager.chromedriver().setup();
    }

    public static WebDriver lambdaTest() throws MalformedURLException{

        String hubUrl = "https://hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("127");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "marcello.strategy");
        ltOptions.put("accessKey", "sgbOor5Ul9i1OQaYgpdbZKAmZLjqJlwE4cHA9TrnJuOKVQN7AW");
        ltOptions.put("project", "Untitled");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubUrl),browserOptions);
    }

    @BeforeMethod
    @Parameters({"baseUrl"})
    public void initMethod(String baseUrl) throws MalformedURLException {
//        options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*", "--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        this.driver = new ChromeDriver(options);
        threadDriver.set(chooseBroswer(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        driver = chooseBroswer(System.getProperty("browser")); // System.getProperty allows to get the property from Gradle
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//        this.fluentWait = new FluentWait(Duration.ofSeconds(10))
//                .withTimeout(Duration.ofSeconds(10))
//                .pollingEvery(Duration.ofSeconds(2));
        url = baseUrl;
//        action = new Actions(driver);
        openURL();
    }

    @AfterMethod
    public void endMethod(){
//        driver.quit();
        threadDriver.get().close();
        threadDriver.remove();
    }

    public void openURL(){
        //driver.get(this.url);
        getDriver().get(url);
    }
}