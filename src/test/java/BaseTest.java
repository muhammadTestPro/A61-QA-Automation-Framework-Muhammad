import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;


import java.time.Duration;

public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options;
    String url = null;// https://qa.koel.app/"; // "https://app.testpro.io/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"baseUrl"})
    public void initMethod(String baseUrl){
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(this.driver,Duration.ofSeconds(10));
        this.url = baseUrl;
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
        WebElement emailField = this.driver.findElement(By.cssSelector("input[type='email']"));
        emailField.sendKeys(email);
    }

    public void inputPassword(String pwd){
        WebElement passField = this.driver.findElement(By.cssSelector("input[type='password']"));
        passField.sendKeys(pwd);
    }

    public void btnLogin(){
        WebElement buttonLogin = this.driver.findElement(By.cssSelector("button[type='submit']"));
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

    // deletes the given playlist
    public void menuDelPlaylist(String playlist) {
        //WebElement delPlaylist = this.driver.findElement(By.xpath("//section[@id=\"playlists\"]//a[contains(text(),\"" + playlist + "\")]"));
        WebElement delPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id=\"playlists\"]//a[contains(text(),\"" + playlist + "\")]")));
        delPlaylist.click();

        //WebElement btnPlaylistDel = this.driver.findElement(By.xpath("//button[@class=\"del btn-delete-playlist\"]"));
        WebElement btnPlaylistDel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"del btn-delete-playlist\"]")));
        btnPlaylistDel.click();

//        WebElement btnOK = this.driver.findElement(By.cssSelector("button.ok"));
//        btnOK.click();  // Only shows when there is something in the playlist

        //WebElement divSuccessDisplay = this.driver.findElement(By.xpath("//div[@class='alertify-logs top right']//div[contains(text(),\""+ playlist + "\")]"));
        WebElement divSuccessDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alertify-logs top right']//div[contains(text(),\""+ playlist + "\")]")));
        divSuccessDisplay.isDisplayed();

    }

    // creates playlist
    public void createPlaylist(String playlistName)  {

        //WebElement btnPlusIcon = this.driver.findElement(By.cssSelector("i[data-testid=\"sidebar-create-playlist-btn\"]"));
        //btnPlusIcon.click();
        WebElement btnPlusIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("i[data-testid=\"sidebar-create-playlist-btn\"]")));
        btnPlusIcon.click();

        //WebElement liAddNewPlaylist = this.driver.findElement(By.cssSelector("li[data-testid=\"playlist-context-menu-create-simple\""));
        //liAddNewPlaylist.click();
        WebElement liAddNewPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[data-testid=\"playlist-context-menu-create-simple\"")));
        liAddNewPlaylist.click();

        //WebElement txtPlaylist = this.driver.findElement(By.xpath("//input[@name=\"name\"]"));
        WebElement txtPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"name\"]")));
        txtPlaylist.sendKeys(playlistName);
        txtPlaylist.sendKeys(Keys.ENTER);

    }

    // deletes the given playlist
    public void menuDelPlaylistWMenu(String playlist){
        Actions action = new Actions(this.driver);
        //WebElement delPlaylist = this.driver.findElement(By.xpath("//section[@id=\"playlists\"]//a[contains(text(),\"" + playlist + "\")]"));
        WebElement delPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id=\"playlists\"]//a[contains(text(),\"" + playlist + "\")]")));
        action.contextClick(delPlaylist).perform(); // Right click

        //WebElement liDeleteTxt = this.driver.findElement(By.xpath("//nav//li[contains(text(),\"Delete\")]"));
        WebElement liDeleteTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav//li[contains(text(),\"Delete\")]")));
        liDeleteTxt.click();

//        WebElement btnOK = this.driver.findElement(By.cssSelector("button.ok"));
//        btnOK.click();  // Only shows when there is something in the playlist

        //WebElement divSuccessDisplay = this.driver.findElement(By.xpath("//div[@class='alertify-logs top right']//div[contains(text(),\"" + playlist + "\")]"));
        WebElement divSuccessDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alertify-logs top right']//div[contains(text(),\"" + playlist + "\")]")));
        divSuccessDisplay.isDisplayed();

    }

}