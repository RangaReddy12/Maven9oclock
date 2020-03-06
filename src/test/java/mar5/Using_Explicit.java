package mar5;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Using_Explicit {
	WebDriver driver;
@Test
public void login() 
{
driver=new ChromeDriver();
driver.get("https://gmail.com");
driver.manage().window().maximize();
//create object for webdriverwait class
WebDriverWait wait=new WebDriverWait(driver, 500);
//wait for username until visible
wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
driver.findElement(By.name("identifier")).sendKeys("pranga2010@gmail.com");
//wait until next button clickable
wait.until(ExpectedConditions.elementToBeClickable(By.className("CwaK9")));
driver.findElement(By.className("CwaK9")).click();
//wait for password until visible
wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
driver.findElement(By.name("password")).sendKeys("Test123345466");
}
}
