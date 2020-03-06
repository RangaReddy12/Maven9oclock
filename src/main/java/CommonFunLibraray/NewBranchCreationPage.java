package CommonFunLibraray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class NewBranchCreationPage {
public  WebDriver driver;
public NewBranchCreationPage(WebDriver driver)
{
this.driver=driver;	
}
@FindBy(xpath="//input[@id='BtnNewBR']")
WebElement ClicknewBranch;
@FindBy(name="txtbName")
WebElement EnterBname;
@FindBy(name="txtAdd1")
WebElement EnterAddress1;
@FindBy(name="txtZip")
WebElement EnterZipcode;
@FindBy(name="lst_counrtyU")
WebElement SelectCountry;
@FindBy(name="lst_stateI")
WebElement Selectstate;
@FindBy(name="lst_cityI")
WebElement SelectCity;
@FindBy(name="btn_insert")
WebElement ClickSubmit;
public boolean verifybranccreation(String bname,String address1,String zipcode,
		String country,String city,String state)throws Throwable
{
ClicknewBranch.click();
Thread.sleep(5000);
EnterBname.sendKeys(bname);
EnterAddress1.sendKeys(address1);
EnterZipcode.sendKeys(zipcode);
SelectCountry.sendKeys(country);
Thread.sleep(3000);
Selectstate.sendKeys(state);
Thread.sleep(3000);
SelectCity.sendKeys(city);
Thread.sleep(3000);
ClickSubmit.click();
Thread.sleep(5000);
//get alert message
String alertmessage=driver.switchTo().alert().getText();
System.out.println(alertmessage);
Thread.sleep(5000);
driver.switchTo().alert().accept();
Thread.sleep(5000);
String ExpText="new Branch";
if(alertmessage.toLowerCase().contains(ExpText.toLowerCase()))
{
	Reporter.log("Branch Creation Success",true);
	return true;
}
else
{
	Reporter.log("Branch Creation Fail",true);
	return false;
}
}
}















