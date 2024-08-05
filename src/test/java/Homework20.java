import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Homework20 extends BaseTest{

    @Test
    public void deletePlaylist() throws InterruptedException {
        openURL();
        String playlistName = "My Playlist";

        inputEmail("marcello.ferraz.vieira@testpro.io");
        inputPassword("TestPro@123");
        btnLogin();

        createPlaylist(playlistName);
        menuDelPlaylist(playlistName);

        // Check if My Playlist exists
        //Actions action = new Actions(driver);
//        List<WebElement> checkPlaylistName = driver.findElements(By.xpath("//section[@id=\"playlists\"]//a[contains(text(),\"" + playlistName + "\")]"));
//        Assert.assertEquals(0, checkPlaylistName.size());

    }

    @Test
    public void deletePlaylistWMenu() throws InterruptedException {
        openURL();

        inputEmail("marcello.ferraz.vieira@testpro.io");
        inputPassword("TestPro@123");
        btnLogin();

        createPlaylist("Rock Rules");
        menuDelPlaylistWMenu("Rock Rules");

        // Check if My Playlist exists
//        List<WebElement> checkPlaylistName = driver.findElements(By.xpath("//section[@id=\"playlists\"]//a[contains(text(),\"Rock Rules\")]"));
//        Assert.assertEquals(0, checkPlaylistName.size());

    }
}