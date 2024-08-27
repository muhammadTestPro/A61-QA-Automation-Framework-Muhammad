package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageFactory {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePageFactory(WebDriver givenDriver){
        this.driver = givenDriver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);
        PageFactory.initElements(driver,this);
    }
}
