import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegressionTest extends BaseTest {

    @Test
    public void loginEmptyEmailPassword() {
        setEmailPassword("", "");
        inputEmail();
        inputPassword();
        loginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa.koel.app/");
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

//     Login Test using POM


}