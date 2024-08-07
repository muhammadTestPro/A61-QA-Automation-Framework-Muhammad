import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest{

    @Test
    public void renamePlaylist() {

        openURL();
        String oldPlaylistName = "My Playlist";
        String newPlaylistName = "My New Playlist";
        String displaySuccessMsg = "Updated playlist \"" + newPlaylistName + ".\"";

        inputEmail("marcello.ferraz.vieira@testpro.io");
        inputPassword("TestPro@123");
        btnLogin();

        rightClickPlaylistMenu(oldPlaylistName);
        selectSubMenuPlaylistOption("Edit");
        updatePlaylistTextBoxContent(newPlaylistName);

        WebElement divSuccessDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+ displaySuccessMsg + "')]")));
        Assert.assertTrue(divSuccessDisplay.isDisplayed());
    }
}
