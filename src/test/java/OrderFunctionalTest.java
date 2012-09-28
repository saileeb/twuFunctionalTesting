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
        nameElement.sendKeys("awesome");

        WebElement emailElement = driver.findElement(By.id("email_field"));
        emailElement.sendKeys("123");

        WebElement submitButtonElement = driver.findElement(By.id("submitButton"));
        submitButtonElement.submit();
//        submitButtonElement.click();

        String PageTitle=driver.getTitle();
        String check="Add Order";
      
        boolean checkEmail=PageTitle.equals(check);

	assertTrue("Email should be of the form abc@xyz.com",checkEmail);

	
        driver.close();
    }
}
