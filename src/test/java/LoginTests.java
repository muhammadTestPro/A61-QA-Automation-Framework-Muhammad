import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void loginEmptyEmailPassword() {
    setEmailPassword("", "");
    inputEmail();
    inputPassword();
    loginButton();
    Assert.assertEquals(driver.getCurrentUrl(),"https://qa.koel.app/");
}

//     Login Test using POM

    @Test
    public void positiveLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("kristofer.juhasz@testpro.io");
        loginPage.providePassword("Logintest1!");
        loginPage.clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }



//     Login Test using PF

//    @Test
//    public void positiveLoginTestPF() throws InterruptedException {
//        LoginPage loginPage = new LoginPage(getDriver());
//        HomePage homePage = new HomePage(getDriver());
//
//        // Login
//        loginPage.provideEmailPF("kristofer.juhasz@testpro.io");
//        loginPage.providePasswordPF("Logintest1!");
//        loginPage.clickSubmitBtnPF();
//
//        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
//    }
//
//    @Test
//    public void negativeLoginTestPF() throws InterruptedException {
//        LoginPage loginPage = new LoginPage(getDriver());
//        HomePage homePage = new HomePage(getDriver());
//
//        // Login
//        loginPage.provideEmailPF("wrongusername@testpro.io");
//        loginPage.providePasswordPF("Logintest");
//        loginPage.clickSubmitBtnPF();
//
//        Assert.assertEquals(getDriver().getCurrentUrl(), url);
//    }

}
