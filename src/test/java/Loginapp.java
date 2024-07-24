import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class Loginapp {
    public static void main(String[] args){
      WebDriver driver = new ChromeDriver();
      String url =" https://qa.koel.app";
      driver.get(url);
      Assert.assertEquals(driver.getCurrentUrl(), url);
      driver.quit();
    }
}
