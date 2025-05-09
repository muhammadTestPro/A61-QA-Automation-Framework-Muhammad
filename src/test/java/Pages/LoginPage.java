package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // PageFactory Elements

    @FindBy(css = "input[type='email']")
    private WebElement emailFieldPF;

    @FindBy(css = "input[type='password']")
    private WebElement passwordFieldPF;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButtonPF;


    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    // PageFactory Methods

    public LoginPage clickSubmitBtnPF() {
        submitButtonPF.click();
        return this;
    }

    public LoginPage provideEmailPF(String email) {
        emailFieldPF.sendKeys(email);
        return this;
    }

    public LoginPage providePasswordPF(String password) {
        passwordFieldPF.sendKeys(password);
        return this;
    }

    // Elements

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitButton = By.cssSelector("button[type='submit']");

    // Helper Methods

    public void provideEmail(String email) {
        findElement(emailField).sendKeys(email);
    }

    public void providePassword(String password) {
        findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        findElement(submitButton).click();
    }

    public void login() {
        provideEmail("kristofer.juhasz@testpro.io");
        providePassword("Logintest1!");
        clickSubmit();
    }
}
