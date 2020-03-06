package CommonFunLibraray;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage {
	public static WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	//locator for login
	@FindBy(name="txtuId")
	WebElement EnterUsername;
	@FindBy(name="txtPword")
	WebElement Enterpassword;
	@FindBy(name="login")
	WebElement ClickLogin;
public boolean verifyLogin(String username,String password)throws Throwable
{
	EnterUsername.sendKeys(username);
	Enterpassword.sendKeys(password);
	ClickLogin.click();
	Thread.sleep(5000);
	String Expurl="adminflow";
	String Acturl=driver.getCurrentUrl();
	if(Acturl.toLowerCase().contains(Expurl.toLowerCase()))
	{
		Reporter.log("Login Success",true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail",true);
		return false;	
	}
			
}
}













