import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderFunctionalTest {

    @Test
    public void shouldBeAbleToSubmitOrder() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8080/twuFunctionalTesting/order/new");

        WebElement nameElement = driver.findElement(By.id("name_field"));
        nameElement.sendKeys("123");

        WebElement emailElement = driver.findElement(By.id("email_field"));
        emailElement.sendKeys("awesome@awesome.com");

        WebElement submitButtonElement = driver.findElement(By.id("submitButton"));
        submitButtonElement.submit();
//        submitButtonElement.click();

        String PageTitle=driver.getTitle();
        String check="Add Order";
      
        boolean checkName=PageTitle.equals(check);

	assertTrue("Test Unsuccessful!!Name should not contain any numbers and special charcters!",checkName);

	
        driver.close();
    }
}
