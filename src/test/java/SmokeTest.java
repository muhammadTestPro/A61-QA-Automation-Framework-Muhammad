import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("kristofer.juhasz@testpro.io");
        loginPage.providePassword("Logintest1!");
        loginPage.clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    } // The first step to any functionality

    @Test
    public void testSkipSong() {
        HomePage homePage = new HomePage(driver);

        login(); // login with valid credentials
        homePage.playNextSong(); // won't work unless this is first
        homePage.playFirstSong(); // starts the song
        homePage.verifyPlay();   //  verify that the song is playing
    } // This also tests playing a song

}