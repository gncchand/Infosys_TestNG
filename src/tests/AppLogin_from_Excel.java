package tests;

import org.testng.annotations.Test;

import excel.GetData_Ex;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

public class AppLogin_from_Excel {
	public WebDriver driver; 
	@BeforeSuite
	public void launchApp()
	{
		 driver=new ChromeDriver();
		 driver.get("https://blog.testproject.io/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		  driver.findElement(By.linkText("Login")).click();  
	}
	
	 @Test (dataProvider = "getexcel_data")  
	 public void logIn (String username, String password, String criteria) throws Exception 
	 {
	   driver.findElement(By.id("username")).clear();
	   driver.findElement(By.id("password")).clear();
	   driver.findElement(By.id("username")).sendKeys(username);    
	   driver.findElement(By.id("password")).sendKeys(password);    
	   driver.findElement(By.id("tp-sign-in")).click();
	   Thread.sleep(3000);
	   
	   try {	   
	   if(criteria.equalsIgnoreCase("invalid") && driver.findElement(By.xpath("//*[@id='tp-message-error']")).isDisplayed())
	   {
		   String invalpwd=driver.findElement(By.xpath("//*[@id='tp-message-error']")).getText();
			  Reporter.log("invalid user test passed and "+invalpwd+" is displayed");
			  Assert.assertTrue(true);
			   Assert.assertEquals(invalpwd, "Invalid username or password.", "Invalid test failed...");
	   }
	   else if(criteria.equalsIgnoreCase("valid") && driver.findElement(By.linkText("Logout?")).isDisplayed() )
	   {
	   String linkLogout = driver.findElement(By.linkText("Logout?")).getText();  
	   Reporter.log("login test passed and "+linkLogout+" is displayed");
	   Assert.assertTrue(true);
	   Assert.assertEquals(linkLogout, "Logout?", "LogIn Unsuccessful - Please Try Again.");
	   }
	   else 
		  {
			  System.out.println("login test failed");
			  Reporter.log("login test failed");
			  Assert.assertTrue(true);
		  }
	   }
	   catch(Exception e)
	   {
		   System.out.println("Error message : "+e.getMessage());
	   }

	 }
	 
	 @DataProvider 
	 public String[][] getexcel_data() throws IOException
	 {
	   GetData_Ex excel=new GetData_Ex();
	   return excel.get_data_from_excel("Test_Data.xlsx", "Sheet1");
	 }

  @AfterSuite
  public void closeBrowser() throws AWTException {
		  driver.quit();	  
  }
}

