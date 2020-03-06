package DriverFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest {
WebDriver driver;
ExtentReports report;
ExtentTest test;
Workbook wb;
Sheet ws;
FileInputStream fi;
FileOutputStream fo;
@BeforeTest
public void setup()
{
System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
driver=new ChromeDriver();
	report=new ExtentReports("./Reports/Logintest.html");
}
@Test
public void verifyLogin()throws Throwable
{
	fi=new FileInputStream("d://LoginData.xlsx");
	wb=WorkbookFactory.create(fi);
	ws=wb.getSheetAt(0);
int rc=ws.getLastRowNum();
Reporter.log("No of rows are::"+rc,true);
for  (int i=1;i<=rc;i++)
{
	test=report.startTest("Login Test");
	test.assignAuthor("Ranga Qa Engineer");
	test.assignCategory("Maven Framework");
String username=ws.getRow(i).getCell(0).getStringCellValue();
String password=ws.getRow(i).getCell(1).getStringCellValue();
driver.get("http://orangehrm.qedgetech.com/");
driver.manage().window().maximize();
driver.findElement(By.name("txtUsername")).sendKeys(username);
driver.findElement(By.name("txtPassword")).sendKeys(password);
driver.findElement(By.name("Submit")).submit();
if(driver.getCurrentUrl().contains("dash"))
{
	Reporter.log("Login success",true);
	test.log(LogStatus.PASS, "Login success");
ws.getRow(i).createCell(2).setCellValue("Login Success");
ws.getRow(i).createCell(3).setCellValue("Pass");	
}
else
{
Reporter.log("Login Fail",true);
test.log(LogStatus.FAIL, "Login Fail");
ws.getRow(i).createCell(2).setCellValue("Login Fail");
ws.getRow(i).createCell(3).setCellValue("Fail");		
}
report.endTest(test);
report.flush();
}
fi.close();
fo=new FileOutputStream("E://LoginResults.xlsx");
wb.write(fo);
fo.close();
wb.close();
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}




















