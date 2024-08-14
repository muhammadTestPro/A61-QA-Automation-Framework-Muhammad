package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    private By emailField = By.cssSelector("input[type='email']");
    private By passwordField = By.cssSelector("input[type='password']");
    private By submitBtn = By.cssSelector("button[type='submit']");

    /**
     *
     * @param email
     */
    public void inputEmail(String email){
        sendKeys(emailField,email);
    }

    /**
     *
     * @param pwd
     */
    public void inputPassword(String pwd){
        sendKeys(passwordField,pwd);
    }

    /**
     *
     */
    public void clickSubmit(){
        click(submitBtn);
    }




}
