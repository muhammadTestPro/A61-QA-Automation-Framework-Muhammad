package pageFactory;

import io.netty.util.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePageFactory extends BasePageFactory {

    public HomePageFactory(WebDriver givenDriver) {
        super(givenDriver);
    }

    // IMPORTANT NOTE : @FindBy is not possible to make it dynamic Ex. @FindBy( css = "$var")
    @FindBy( css = "img.avatar")
    WebElement avatarIcon;

    @FindBy( xpath = "//section[@id=\"playlists\"]//a[contains(text(),\"My Playlist\")]")
    WebElement myPlalistOption;

    @FindBy( xpath = "//section[@id=\"playlists\"]//a[contains(text(),\"My New Playlist\")]")
    WebElement myNewPlalistOption;

    @FindBy( xpath = "//input[@data-testid=\"inline-playlist-name-input\"]")
    WebElement playlistEditField;

    @FindBy( xpath = "//div[contains(text(),'My New Playlist')]")
    WebElement newPlaylistConfirmMsg;

    @FindBy( xpath = "//nav[@class='menu playlist-item-menu']//ul//li[contains(text(),'Delete')]")
    WebElement deletePopUpMenuOption;

    @FindBy( xpath = "//div[contains(text(),'My Playlist')]")
    WebElement myPlaylistConfirmMsg;

    @FindBy(xpath="//i[@data-testid=\"sidebar-create-playlist-btn\"]")
    WebElement btnPlusIcon;

    @FindBy(css="li[data-testid=\"playlist-context-menu-create-simple\"")
    WebElement liAddNewPlaylist;

    @FindBy(xpath="//input[@name=\"name\"]")
    WebElement txtPlaylist;


    public WebElement getUserAvatar(){
        return avatarIcon;
    }

    public HomePageFactory rightClickPlaylist(){
        actions.contextClick(myPlalistOption).perform();
        return this;
    }

    public HomePageFactory rightClickNewPlaylist(){
        actions.contextClick(myNewPlalistOption).perform();
        return this;
    }

    public HomePageFactory doubleClickPlayList() {
        actions.doubleClick(myPlalistOption).perform();
        return this;
    }

    public HomePageFactory clickDeleteMenuOption() {
        deletePopUpMenuOption.click();
        return this;
    }

    public HomePageFactory renamePlaylistField(String playlistName) {
        playlistEditField.sendKeys(Keys.chord(Keys.CONTROL,"a"),playlistName);
        playlistEditField.sendKeys(Keys.ENTER);
        return this;
    }

    public WebElement isNewPlayListDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(newPlaylistConfirmMsg));
    }

    public WebElement isMyPlayListDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(myPlaylistConfirmMsg));
    }

    public HomePageFactory clickAddPlayListIcon(){
        btnPlusIcon.click();
        return this;
    }

    public HomePageFactory clickMenuNewPlayList(){
        liAddNewPlaylist.click();
        return this;
    }

    public HomePageFactory addNamePlaylistField(String playlistName) {
        txtPlaylist.sendKeys(Keys.chord(Keys.CONTROL,"a"),playlistName);
        txtPlaylist.sendKeys(Keys.ENTER);
        return this;
    }

}
