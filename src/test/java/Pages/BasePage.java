package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver givenDriver){
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement findElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    // Elements

    By soundbar = By.cssSelector("div[data-testid='sound-bar-play']");
    By play = By.cssSelector("span[data-testid='play-btn']");
    By skip = By.cssSelector("i[data-testid='play-next-btn']");
    By search = By.cssSelector("input[type='search']");
    By viewAll = By.cssSelector("[data-test='view-all-songs-btn']");
    By addTo = By.cssSelector("[data-test='add-to-btn']");
    By createPlaylist = By.cssSelector("[data-testid=\"sidebar-create-playlist-btn\"]");
    By newPlaylist = By.cssSelector("[data-testid='playlist-context-menu-create-simple']");
    By newPlaylistName = By.cssSelector("#playlists > form > input[type=text]");
    By deletePlaylist = By.cssSelector("[class=\"del btn-delete-playlist\"]");
    By successMessage = By.cssSelector("div.success.show");
    By homePage = By.cssSelector("[class=\"home active\"]");
    By confirmationOk = By.cssSelector("[class=\"ok\"]");
    By logout = By.cssSelector("[class=\"fa fa-sign-out\"]");
    By registration = By.cssSelector("[href='registration']");
    By email = By.cssSelector("[type='email']");
    By password = By.cssSelector("[type='password']");
    By submit = By.cssSelector("[type='submit']");
    By message = By.cssSelector("[class='messages']");


    // Helper Methods

    public WebElement getSoundbar(){
        return findElement(soundbar);
    }
    public WebElement getPlayBtn(){
        return findElement(play);
    }
    public WebElement getSkipBtn(){
        return findElement(skip);
    }
    public WebElement getSearchBar(){
        return findElement(search);
    }
    public WebElement getViewAll(){
        return findElement(viewAll);
    }
    public WebElement getAddTo(){
        return findElement(addTo);
    }
    public WebElement getCreatePlaylist(){
        return findElement(createPlaylist);
    }
    public WebElement getNewPlaylist(){
        return findElement(newPlaylist);
    }
    public WebElement getNewPlaylistName(){
        return findElement(newPlaylistName);
    }
    public WebElement getDeletePlaylist(){
        return findElement(deletePlaylist);
    }
    public WebElement getSuccessMessage(){
        return findElement(successMessage);
    }
    public WebElement getHomePage(){
        return findElement(homePage);
    }
    public WebElement getConfirmationOk(){
        return findElement(confirmationOk);
    }
    public WebElement getLogout(){
        return findElement(logout);
    }
    public WebElement getRegistration(){
        return findElement(registration);
    }
    public WebElement getEmail(){
        return findElement(email);
    }
    public WebElement getPassword(){
        return findElement(password);
    }
    public WebElement getSubmit() {
        return findElement(submit);
    }
    public WebElement getMessage() {
        return findElement(message);
    }



    // Methods

    public void gotoHomePage() {
        getHomePage().click();
    }

    public void verifyPlay() {
        Assert.assertTrue(getSoundbar().isDisplayed());
    }

    public void playFirstSong()  {
        driver.findElement(play).click(); // Doesn't work with "get" because they're not displayed unless hovered over
    }

    public void playNextSong() {
        driver.findElement(skip).click(); // Doesn't work with "get" because they're not displayed unless hovered over
    }

    public void inputSearch(String song) {
        getSearchBar().clear();
        getSearchBar().sendKeys(song);
    }

    public void viewAllButton() {
        getViewAll().click();
    }

    public void firstSongSelect() {
        WebElement firstSong = driver.findElement(By.cssSelector("#songResultsWrapper > div > div > div.item-container > table > tr:nth-child(1)"));
        firstSong.click();
    }

    public void addToPlaylist() {
        getAddTo().click();
    }

    public void selectPlaylist() {
        WebElement selectPlaylist = driver.findElement(By.cssSelector("#songResultsWrapper > header > div.song-list-controls > div > section.existing-playlists > ul > li.playlist"));
        selectPlaylist.click();
    }


    public void createPlaylist() throws InterruptedException {
        Thread.sleep(2000); // Hardcoded delay to allow webpage to fully load
        getCreatePlaylist().click();
        getNewPlaylist().click();
        getNewPlaylistName().clear();
        getNewPlaylistName().sendKeys("PlaylistTest");
        getNewPlaylistName().sendKeys(Keys.ENTER);
    }

    public void deleteFirstPlaylist() {
        WebElement firstPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"playlist playlist\"]")));
        firstPlaylist.click();
        getDeletePlaylist().click();
    }

    public void renameFirstPlaylist() {
        Actions a = new Actions(driver);
        WebElement firstPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"playlist playlist\"]")));
        a.doubleClick(firstPlaylist).perform();
        WebElement firstPlaylistName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid=\"inline-playlist-name-input\"]")));
        firstPlaylistName.sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));
        firstPlaylistName.sendKeys("RenamePlaylistTest");
        firstPlaylistName.sendKeys(Keys.ENTER);
    }

    public void logout() {
        getLogout().click();
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

}
