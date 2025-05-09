import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    static WebDriver driver;
    private String emailLegacy;
    private String passwordLegacy;
    private String song;
    String url = "https://qa.koel.app/";
    WebDriverWait wait;

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public void waitInitialize(WebDriver driver) {
        BaseTest.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize here
    }

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    public void verifyPlay() {
        WebElement soundbar = driver.findElement(By.cssSelector("div[data-testid='sound-bar-play']"));
        Assert.assertTrue(soundbar.isDisplayed());
    }

    public void playFirstSong()  {
        WebElement buttonPlay = driver.findElement(By.cssSelector("span[data-testid='play-btn']"));
        buttonPlay.click();

    }

    public void playNextSong() {
        WebElement skip = driver.findElement(By.cssSelector("i[data-testid='play-next-btn']"));
        skip.click();
    }

    public void setEmailPassword(String email, String password) {
        this.emailLegacy = email;
        this.passwordLegacy = password;
    }

    public void inputEmail() {
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(this.emailLegacy);
    }

    public void inputPassword() {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(this.passwordLegacy);
    }

    public void loginButton() {
        //      Clicks login button
        WebElement login = driver.findElement(By.cssSelector("button[type='submit']"));
        login.click();
    }

    public void login() {
        //      Input login credentials
        setEmailPassword("kristofer.juhasz@testpro.io", "Logintest1!");
        inputEmail();
        inputPassword();
        loginButton();
    }

    public void inputSearch() {
        WebElement searchBar = driver.findElement(By.cssSelector("input[type='search']"));
        searchBar.clear();
        searchBar.sendKeys(this.song);
    }

    public void searchSong(String song) {
        this.song = song;
    }

    public void viewAllButton() {
        WebElement viewAll = driver.findElement(By.cssSelector("[data-test='view-all-songs-btn']"));
        viewAll.click();
    }

    public void firstSongSelect() {
        WebElement firstSong = driver.findElement(By.cssSelector("#songResultsWrapper > div > div > div.item-container > table > tr:nth-child(1)"));
        firstSong.click();
    }

    public void addToPlaylist() {
        WebElement addTo = driver.findElement(By.cssSelector("[data-test='add-to-btn']"));
        addTo.click();
    }

    public void selectPlaylist() {
        WebElement selectPlaylist = driver.findElement(By.cssSelector("[#songResultsWrapper > header > div.song-list-controls > div > section.existing-playlists > ul > li.playlist]"));
        selectPlaylist.click();
    }


    public void createPlaylist() throws InterruptedException {
        WebElement createPlaylistButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"sidebar-create-playlist-btn\"]")));
        createPlaylistButton.click();
        WebElement newPlaylistButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='playlist-context-menu-create-simple']")));
        newPlaylistButton.click();
        WebElement newPlaylistName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#playlists > form > input[type=text]")));
        newPlaylistName.clear();
        newPlaylistName.sendKeys("Homework");
        newPlaylistName.sendKeys(Keys.ENTER);
    }

    public void deleteFirstPlaylist() {
        WebElement firstPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"playlist playlist\"]")));
        firstPlaylist.click();
        WebElement deletePlaylistButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"del btn-delete-playlist\"]")));
        deletePlaylistButton.click();
    }

    public void renameFirstPlaylist() {
        Actions a = new Actions(driver);
        WebElement firstPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"playlist playlist\"]")));
        a.doubleClick(firstPlaylist).perform();
        WebElement firstPlaylistName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"inline-playlist-name-input\"]")));
        firstPlaylistName.sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));
        firstPlaylistName.sendKeys("Homework21");
        firstPlaylistName.sendKeys(Keys.ENTER);
    }

    public WebDriver lambdaTest() throws MalformedURLException {
        String hubUrl = "https://hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("129");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "juhkri");
        ltOptions.put("accessKey", "plHLrwS0YOniwTgtHkq9rohX4ZBdDTq6clfYyQbAX966m079Cc");
        ltOptions.put("project", "Untitled");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubUrl), browserOptions);
    }

    public WebDriver pickBrowser(String browserName) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://26.61.194.212:4444";

        switch (browserName){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "microsoft edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver();

            // Grid Browsers
            case "grid-edge":
                caps.setCapability("browserName", "microsoft edge");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(),caps);
            case "cloud":
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }


    @BeforeMethod
    @Parameters({"BaseURL"})
    public void setBrowser(String baseURL) throws MalformedURLException {
        threadDriver.set(driver = pickBrowser(System.getProperty("browser")));
        getDriver().get(url);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
//        wait = new WebDriverWait((WebDriver) threadDriver, Duration.ofSeconds(4));
    }

//    public void initBrowser() throws MalformedURLException {

//      Added ChromeOptions argument below to fix websocket error
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(options);
//        driver = pickBrowser(System.getProperty("browser"));
//        driver.get(url);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();

//
//        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
//    }

    //@AfterMethod
    public void tearDown() {
        threadDriver.get().close();
        threadDriver.remove();
    }


    @BeforeSuite
    static void setupClass() {
        // WebDriverManager.chromedriver().setup();
    }
}