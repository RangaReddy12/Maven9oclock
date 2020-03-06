package DriverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import CommonFunLibraray.BranchUpdationPage;
import CommonFunLibraray.BranchesPages;
import CommonFunLibraray.LoginPage;
import CommonFunLibraray.LogoutPage;
import CommonFunLibraray.NewBranchCreationPage;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;



public class DriverScript extends PBConstant {
String inputpath="D:\\December_Selenium\\Maven_11oClockProject\\TestInput\\Controller.xlsx";
String outputpath="D:\\December_Selenium\\Maven_11oClockProject\\TestOutPut\\keywordResults.xlsx";
String TCSheet="TestCases";
String TSSheet="TestSteps";
ExtentReports report;
ExtentTest test;
@Test
public void startTest()throws Throwable
{
//generate reports folder
report=new ExtentReports("./Extent-Report/Keyword.html");
//call all page classes
LoginPage login=PageFactory.initElements(driver, LoginPage.class);
BranchesPages branch=PageFactory.initElements(driver, BranchesPages.class);
NewBranchCreationPage branchc=PageFactory.initElements(driver, NewBranchCreationPage.class);
BranchUpdationPage bupdate=PageFactory.initElements(driver, BranchUpdationPage.class);
LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
boolean res=false;
String tcres="";
//creating object for excelutil class
ExcelFileUtil xl=new ExcelFileUtil(inputpath);
//count no of rows in TCSheet
int TCCount=xl.rowCount(TCSheet);
//count no of rows in TSSheet
int TSCount=xl.rowCount(TSSheet);
Reporter.log(TCCount+"    "+TSCount,true);
for(int i=1;i<=TCCount;i++)
{

	//start test case 
	test=report.startTest("Keyword framework");
	test.assignAuthor("Ranga Senior manager");
	test.assignCategory("Keyword Driven Framework");
//read execute column
String Excute=xl.getCellData(TCSheet, i, 2);
if(Excute.equalsIgnoreCase("Y"))
{
//read tcid column from TCSheet
	String Tcid=xl.getCellData(TCSheet, i, 0);
	for(int j=1;j<=TSCount;j++)
	{
		//read description column from TSSheet
	String Description=xl.getCellData(TSSheet, j, 2);	
		//read tsid column from TSSheet	
String Tsid=xl.getCellData(TSSheet, j, 0);
if(Tcid.equalsIgnoreCase(Tsid))
{
	//read keyword column from TSSheet
	String keyword=xl.getCellData(TSSheet, j, 3);
	if(keyword.equalsIgnoreCase("AdminLogin"))
	{
res=login.verifyLogin("Admin", "Admin");
test.log(LogStatus.INFO, Description);
	}
	else if(keyword.equalsIgnoreCase("NewBranchCreation"))
	{
branch.navigateBranches();
res=branchc.verifybranccreation("Srnagar123", "Hyderabad", "12345", "INDIA", "GOA", "GOA");
test.log(LogStatus.INFO, Description);
	}
	else if(keyword.equalsIgnoreCase("UpdateBranch"))
	{
		branch.navigateBranches();
	res=bupdate.verifyBUpdation("Kadiri", "madanapalii");
	test.log(LogStatus.INFO, Description);
	}
	else if(keyword.equalsIgnoreCase("AdminLogout"))
	{
		res=logout.verifylogout();
		test.log(LogStatus.INFO, Description);
	}
	String tsres="";
	//res holds trure or false
	if(res)
	{
	//write as pass into TSSheet results column
		tsres="Pass";
xl.setCellData(TSSheet, j, 4, tsres, outputpath);	
test.log(LogStatus.PASS, Description);
	}
	else
	{
		//write as Fail into TSSheet results column
			tsres="Fail";	
xl.setCellData(TSSheet, j, 4, tsres, outputpath);	
test.log(LogStatus.FAIL, Description);
	}
	if(!tsres.equalsIgnoreCase("Fail"))
	{
	tcres=tsres;	
	}
}
report.endTest(test);
report.flush();
}
//write as pass or fail into TCSheet
xl.setCellData(TCSheet, i, 3, tcres, outputpath);	
}
else
{
	//write as blocked into results column in TCSheet
xl.setCellData(TCSheet, i, 3, "blocked", outputpath);	
}
}

}

}


















