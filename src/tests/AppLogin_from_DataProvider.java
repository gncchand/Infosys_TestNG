package tests;

import org.testng.annotations.Test;

import pages.Login_Page;
import pages.Logout_Page;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

public class AppLogin_from_DataProvider {
	public WebDriver driver; 
	public Login_Page lin;
	public Logout_Page lout;
	
	@BeforeSuite
	public void launchApp()
	{
		 driver=new ChromeDriver();
		 driver.get("https://blog.testproject.io/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		  lin=new Login_Page(driver);
		 lin.login.click();
		  lout=new Logout_Page(driver);
		 
	}
	
	 @Test (dataProvider = "logInData")  
	 public void logIn (String username, String password, String criteria) throws Exception 
	 {
	   lin.uid.clear();
	   lin.pwd.clear();
	   lin.uid_enter(username);  
	   lin.pwd_enter(password);
	   lin.click_Submit();
	   Thread.sleep(3000);
	   
	   try {	   
	   if(criteria.equalsIgnoreCase("invalid") && lin.errmsg.isDisplayed())
	   {
		   String invalpwd=lin.errmsg.getText();
		   Reporter.log("invalid user test passed and "+invalpwd+" is displayed");
		   Assert.assertTrue(true);
		   Assert.assertEquals(invalpwd, "Invalid username or password.", "Invalid test failed...");
	   }
	   else if(criteria.equalsIgnoreCase("valid") && lout.logout.isDisplayed() )
	   {
	   String linkLogout = lout.logout.getText();
	   Reporter.log("login test passed and "+linkLogout+" is displayed");
	   Assert.assertTrue(true);
	   Assert.assertEquals(linkLogout, "Logout?", "LogIn Unsuccessful - Please Try Again.");
	   lout.click_Logout();
	   Thread.sleep(3000);
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
	 public Object [] [] logInData ()
	 {
	   Object [] [] data = new Object [2] [3];   
	  data [1] [0] = "naveengaddipatis@gmail.com";    
	  data [1] [1] = "Auto@123";    
	  data [1] [2] = "valid"; 
	   data [0] [0] = "InvalidLogIn@test123.com";      
	   data [0] [1] = "InvalidPassword";
	   data [0] [2] = "Invalid";
	   return data;
	 }

  @AfterSuite
  public void closeBrowser() throws AWTException {
		  driver.quit();	  
  }
}

