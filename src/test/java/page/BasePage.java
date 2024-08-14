package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver givenDriver){

        this.driver = givenDriver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);

    }

    /**\
     *
     * @param locator
     * @return WebElement by a given locator
     */
    public WebElement findElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     *
     * @param locator
     */
    public void click(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    /**
     *
     * @param locator
     */
    public void doubleClick(By locator){
        WebElement waitLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        actions.doubleClick(waitLocator).perform();
    }

    /**
     *
     * @param locator
     * @param text
     */
    public void sendKeys(By locator,String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    /**
     * Right Click the locator
     * @param locator
     */
    public void rightClick(By locator){
        WebElement waitLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        actions.contextClick(waitLocator).perform(); // Right click
    }

    /**
     * Highlight all previous word(s) in the Field and replace with given new word
     */
    public void sendKeysUpdate(By locator, String text){
        WebElement waitLocator = findElement(locator);
        waitLocator.sendKeys(Keys.chord(Keys.CONTROL,"a"),text);
    }

    public void pressEnter(By locator){
        WebElement waitLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        waitLocator.sendKeys(Keys.ENTER);
    }

    public Boolean elementDisplayed(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

}
