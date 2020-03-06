package CommonFunLibraray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class BranchUpdationPage {
public  WebDriver driver;
public BranchUpdationPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
WebElement ClickEdit;
@FindBy(name="txtbnameU")
WebElement Enterbname;
@FindBy(name="txtadd1u")
WebElement EnterAddress1;
@FindBy(name="btnupdate")
WebElement ClickUpdate;
public boolean verifyBUpdation(String bname,String address1)throws Throwable
{
	ClickEdit.click();
	Thread.sleep(4000);
	Enterbname.clear();
	Enterbname.sendKeys(bname);
	EnterAddress1.clear();
	EnterAddress1.sendKeys(address1);
	ClickUpdate.click();
	Thread.sleep(5000);
	String updatealert=driver.switchTo().alert().getText();
	System.out.println(updatealert);
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String Exptext="Branch upd";
	if(updatealert.toLowerCase().contains(Exptext.toLowerCase()))
	{
		Reporter.log("Branch Update Success",true);
		return true;
	}
	else
	{
		Reporter.log("Branch Update Fail",true);
		return false;
	}
}


}
















