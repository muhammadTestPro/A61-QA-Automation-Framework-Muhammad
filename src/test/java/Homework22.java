import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

public class Homework22 extends BaseTest{

    @Test
    public void renamePlaylist() {

        String oldPlaylistName = "My Playlist";
        String newPlaylistName = "My New Playlist";
        String displaySuccessMsg = "Updated playlist \"" + newPlaylistName + ".\"";

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.inputEmail("marcello.ferraz.vieira@testpro.io");
        loginPage.inputPassword("TestPro@123");
        loginPage.clickSubmit();

        homePage.doubleClickPlayList(oldPlaylistName);
        homePage.renamePlaylistField(newPlaylistName);

        Assert.assertTrue(homePage.findDiplayedElement(displaySuccessMsg));
    }
}
