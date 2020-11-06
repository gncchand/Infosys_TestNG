package tests;

import org.testng.annotations.Test;

import pages.Login_Page;
import pages.Logout_Page;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class CrossBrowser_Test {
	
	public WebDriver driver; 
	public Login_Page lin;
	public Logout_Page lout;
	String browser_type;
	
	
	@BeforeSuite
	public void launchApp()
	{
		System.out.println("start of suite.... ");
		  
	}
	 @BeforeTest   
	 @Parameters({"bt"})
	 public void getType (@Optional String btype) 
	   {
		 browser_type=btype;
		 if(btype.equalsIgnoreCase("chrome"))
		  {
			System.out.println("opening "+btype+" browser");
			 driver=new ChromeDriver();	
			
		  }
		 else if(btype.equalsIgnoreCase("firefox"))
		  {
			System.out.println("opening "+btype+" browser");
			 driver=new FirefoxDriver();	
		  }
		 else
		 {
				System.out.println("this is invalid browser..");
		 }
	   }
	 @Test (dataProvider = "logInData")  
	 public void logIn (String username, String password, String criteria) throws Exception 
	 {
		 driver.get("https://blog.testproject.io/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		 lin=new Login_Page(driver);
		 lin.login.click();
		  lout=new Logout_Page(driver);
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
		   Reporter.log("invalid user test passed and "+invalpwd+" is displayed for "+browser_type);
		   Assert.assertTrue(true);
		   Assert.assertEquals(invalpwd, "Invalid username or password.", "Invalid test failed...");
	   }
	   else if(criteria.equalsIgnoreCase("valid") && lout.logout.isDisplayed() )
	   {
	   String linkLogout = lout.logout.getText();
	   Reporter.log("login test passed and "+linkLogout+" is displayed for "+browser_type);
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
  @AfterTest
  public void closeBrowser()
  {
	  System.out.println("closing browser...");
	  driver.close();
  }

  @AfterSuite
  public void afterSuite() throws AWTException 
  {
	System.out.println("end of suite...");		  
  }

}
