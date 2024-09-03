import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.HomePageFactory;
import pageFactory.LoginPageFactory;

public class Homework23 extends BaseTest{

    // creates playlist
    @Test(priority=1)
    public void createPlaylist()  {
        String playlistName = "My Playlist";

        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
        HomePageFactory homePageFactory = new HomePageFactory(driver);

        loginPageFactory.inputEmail("marcello.ferraz.vieira@testpro.io");
        loginPageFactory.inputPassword("TestPro@123");
        loginPageFactory.clickSubmit();

        Assert.assertTrue(homePageFactory.getUserAvatar().isDisplayed());

        homePageFactory.clickAddPlayListIcon();
        homePageFactory.clickMenuNewPlayList();
        homePageFactory.addNamePlaylistField(playlistName);

        Assert.assertTrue(homePageFactory.isMyPlayListDisplayed().isDisplayed());
    }

    @Test(priority=2)
    public void renamePlaylist() {

        String oldPlaylistName = "My Playlist";
        String newPlaylistName = "My New Playlist";
        String displaySuccessMsg = "Updated playlist \"" + newPlaylistName + ".\"";

        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
        HomePageFactory homePageFactory = new HomePageFactory(driver);

        loginPageFactory.inputEmail("marcello.ferraz.vieira@testpro.io");
        loginPageFactory.inputPassword("TestPro@123");
        loginPageFactory.clickSubmit();

        Assert.assertTrue(homePageFactory.getUserAvatar().isDisplayed());

        homePageFactory.doubleClickPlayList();
        homePageFactory.renamePlaylistField("My New Playlist");
        Assert.assertTrue(homePageFactory.isNewPlayListDisplayed().isDisplayed());
    }


    @Test(priority=3)
    public void deletePlaylist() {
        String playlistName = "My New Playlist";

        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
        HomePageFactory homePageFactory = new HomePageFactory(driver);

        loginPageFactory.inputEmail("marcello.ferraz.vieira@testpro.io");
        loginPageFactory.inputPassword("TestPro@123");
        loginPageFactory.clickSubmit();

        Assert.assertTrue(homePageFactory.getUserAvatar().isDisplayed());

        homePageFactory.rightClickNewPlaylist();
        homePageFactory.clickDeleteMenuOption();

        Assert.assertTrue(homePageFactory.isNewPlayListDisplayed().isDisplayed());

    }
}
