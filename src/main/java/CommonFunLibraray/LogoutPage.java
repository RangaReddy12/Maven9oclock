package CommonFunLibraray;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LogoutPage {
public  WebDriver driver;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//td//td//td//td[3]//a[1]//img[1]")
WebElement ClickLogout;
@FindBy(name="login")
WebElement ClickLogin;
public boolean verifylogout()throws Throwable
{
	ClickLogout.click();
	Thread.sleep(4000);
if(ClickLogin.isDisplayed())
{
	Reporter.log("Logout Success",true);
	return true;
}
else
{
	Reporter.log("Logout Fail",true);
	return false;
}
}
}
