import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LoginPage;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test
    public void loginValidEmailPassword()  {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputEmail("marcello.ferraz.vieira@testpro.io");
        loginPage.inputPassword("TestPro@123");
        loginPage.clickSubmit();

        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar")));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    @Test (enabled = true, priority = 3, description = "Login with invalid email and/or password", dataProvider = "LoginNegativeTestData")
    public void loginInvalidEmailPassword(String Email, String Password){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputEmail(Email);
        loginPage.inputPassword(Password);
        loginPage.clickSubmit();

        String url = "https://qa.koel.app/";
        Assert.assertEquals(driver.getCurrentUrl(),url);


    }


}