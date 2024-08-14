import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.openqa.selenium.interactions.Actions;


import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ChromeOptions options;
    private String url = null;// https://qa.koel.app/"; // "https://app.testpro.io/";
    protected Actions action;
    protected FluentWait fluentWait;

    @DataProvider(name="LoginNegativeTestData")
    public Object[][] getDataFromDataProvider(){
        return new Object[][]{
                {"invalidEmail@email.com","Invalid"},   // Invalid Email Data
                {"",""},                               // Empty email and pwd
        };
    }

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"baseUrl"})
    public void initMethod(String baseUrl){
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*","--disable-notifications","--remote-allow-origins=*", "--incognito","--start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(this.driver,Duration.ofSeconds(10));
        this.fluentWait = new FluentWait(Duration.ofSeconds(10))
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2));
        this.url = baseUrl;
        action = new Actions(this.driver);
        openURL();
    }

    @AfterMethod
    public void endMethod(){
        this.driver.quit();
    }

    public void openURL(){
        this.driver.get(this.url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void inputEmail(String email){
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.sendKeys(email);
    }

    public void inputPassword(String pwd){
        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passField.sendKeys(pwd);
    }

    public void btnLogin(){
        WebElement buttonLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        buttonLogin.click();
    }

    public void inputSearch(String seachTxt){
        WebElement seachField = this.driver.findElement(By.cssSelector("input[type='search']"));
        seachField.sendKeys(seachTxt);
    }

    public void btnSongsViewAll(){
        WebElement buttonViewAll = this.driver.findElement(By.xpath("//section[@class='songs']//button[contains(text(),'View All')]"));
        buttonViewAll.click();
    }

    public void btnAddTo(){
        WebElement buttonAddTo = this.driver.findElement(By.cssSelector("button[class='btn-add-to']"));
        buttonAddTo.click();
    }

    public void btnPlaySong(){
        WebElement buttonPlaySong = this.driver.findElement(By.cssSelector("span[data-testid='play-btn']"));
        buttonPlaySong.click();
    }

    public void btnPlayNextSong(){
        WebElement buttonPlayNext = this.driver.findElement(By.cssSelector("i[data-testid='play-next-btn']"));
        buttonPlayNext.click();
    }

    public void imgSoundBarVisible(){
        WebElement buttonPlayNext = this.driver.findElement(By.cssSelector("div[data-testid='sound-bar-play']"));
        buttonPlayNext.isDisplayed();
    }

    public void rightClickPlaylistMenu(String playlist){
        WebElement delPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id=\"playlists\"]//a[contains(text(),\"" + playlist + "\")]")));
        action.contextClick(delPlaylist).perform(); // Right click
    }

    public void selectSubMenuPlaylistOption(String option){
        WebElement liDeleteTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//li[contains(text(),\""+ option +"\")]")));
        liDeleteTxt.click();
    }

    public void updatePlaylistTextBoxContent(String playlistName) {
        WebElement txtEditPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-testid=\"inline-playlist-name-input\"]")));
        //txtEditPlaylist.clear(); It doesnt work - when I use clear right after the txtbox will become hidden
        txtEditPlaylist.sendKeys(Keys.chord(Keys.CONTROL,"a"),playlistName);
        txtEditPlaylist.sendKeys(Keys.ENTER);
    }

    // creates playlist
    public void createPlaylist(String playlistName)  {

        WebElement btnPlusIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i[data-testid=\"sidebar-create-playlist-btn\"]")));
        btnPlusIcon.click();

        WebElement liAddNewPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-testid=\"playlist-context-menu-create-simple\"")));
        liAddNewPlaylist.click();

        WebElement txtPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"name\"]")));
        txtPlaylist.sendKeys(playlistName);
        txtPlaylist.sendKeys(Keys.ENTER);
    }
}