import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.HomePageFactory;
import pageFactory.LoginPageFactory;

public class LoginTests extends BaseTest {

    @Test
    public void loginValidEmailPassword()  {
        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
        HomePageFactory homePageFactory = new HomePageFactory(driver);

        // Fluent Way of Doing
        loginPageFactory.inputEmail("marcello.ferraz.vieira@testpro.io")
                .inputPassword("TestPro@123")
                .clickSubmit();

//        Normal Way
//        loginPageFactory.inputEmail("marcello.ferraz.vieira@testpro.io");
//        loginPageFactory.inputPassword("TestPro@123");
//        loginPageFactory.clickSubmit();

        Assert.assertTrue(homePageFactory.getUserAvatar().isDisplayed());
    }

    @Test (enabled = true, priority = 3, description = "Login with invalid email and/or password", dataProvider = "LoginNegativeTestData")
    public void loginInvalidEmailPassword(String Email, String Password){
        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);

        loginPageFactory.inputEmail(Email);
        loginPageFactory.inputPassword(Password);
        loginPageFactory.clickSubmit();

        String url = "https://qa.koel.app/";
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

}