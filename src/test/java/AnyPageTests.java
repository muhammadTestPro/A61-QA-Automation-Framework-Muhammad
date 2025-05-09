import Pages.HomePage;
import Pages.LoginPage;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AnyPageTests extends BaseTest {

    @Test
    public void testSkipSong() {
        HomePage homePage = new HomePage(driver);

        login(); // login with valid credentials
        homePage.playNextSong(); // won't work unless this is first
        homePage.playFirstSong(); // starts the song
        homePage.verifyPlay();   //  verify that the song is playing
    }

    @Test
    public void deletePlaylist() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        login(); // login with valid credentials
        homePage.createPlaylist(); // creates a playlist
        homePage.deleteFirstPlaylist(); // deletes the first playlist on the list
    }

    @Test
    public void renamePlaylist() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        login(); // login with valid credentials
        homePage.createPlaylist(); // creates a playlist
        homePage.renameFirstPlaylist(); // renames the first playlist on the list
        homePage.deleteFirstPlaylist(); // deletes the first playlist on the list
    }


    @Test
    public void addSongToPlaylist() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        login(); // login with valid credentials
        homePage.createPlaylist(); // creates a playlist
        homePage.gotoHomePage(); // exits playlist screen (this is necessary because of a website bug)
        homePage.inputSearch("dark days"); // searches a song
        homePage.viewAllButton(); // enters list view
        homePage.firstSongSelect(); // selects first song on list view
        homePage.addToPlaylist(); // opens playlist list
        homePage.selectPlaylist(); // selects first playlist

        // Fix Assert when you figure out how to read ALL success messages and get ALL of their texts
//        String actualMessage = homePage.getSuccessMessage().getText(); // retrieves success message
//        String expectedMessage = "Added 1 song into \"PlaylistTest.\""; // defines what result is a success
//        Assert.assertEquals(actualMessage, expectedMessage, "Song wasn't added to the specified playlist."); // verifies if the test is successful
        homePage.deleteFirstPlaylist(); // resets test conditions
        homePage.getConfirmationOk().click(); // needed now that there's a song in it
    }

    @Test
    public void logoutTest() {
        HomePage homePage = new HomePage(driver);

        login();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
        homePage.logout();
        Assert.assertTrue(homePage.getLogo().isDisplayed());
    }

    //@Test
    public void createNewPlaylistNegativeTest() {
        HomePage homePage = new HomePage(driver);

        login();
        homePage.getCreatePlaylist().click();
        homePage.getNewPlaylist().click();
        homePage.getNewPlaylistName().clear();
        homePage.getNewPlaylistName().sendKeys("CreateNewPlaylistTest"); // Should be invalid because its more than 10 chars
        homePage.getNewPlaylistName().sendKeys(Keys.ENTER);
    } // This test isn't finished yet

    @Test
    public void createNewAccount() {
        HomePage homePage = new HomePage(driver);

        homePage.getRegistration().click();
        homePage.getEmail().clear();
        homePage.getEmail().sendKeys("kristofer.juhasz@testpro.io");
        homePage.getSubmit().click();
        Assert.assertTrue(homePage.getMessage().isDisplayed());
    }

    @Test
    public void createNewAccountNegativeTestNoAt() {
        HomePage homePage = new HomePage(driver);

        homePage.getRegistration().click();
        homePage.getEmail().clear();
        homePage.getEmail().sendKeys("kristofer.juhasztestpro.io");
        homePage.getSubmit().click();
        //Assert.assertFalse(homePage.getMessage().isDisplayed()); Needs an implicit wait to use assertFalse
    }

    @Test
    public void createNewAccountNegativeTestNoPlus() {
        HomePage homePage = new HomePage(driver);

        homePage.getRegistration().click();
        homePage.getEmail().clear();
        homePage.getEmail().sendKeys("kristofer.juhasz+5@testpro.io");
        homePage.getSubmit().click();
        //Assert.assertFalse(homePage.getMessage().isDisplayed());  Needs an implicit wait to use assertFalse
    }


}
