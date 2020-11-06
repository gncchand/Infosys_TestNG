package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssert_GoogleTitle {
	SoftAssert sa=new SoftAssert();
	public static ChromeDriver driver;
	@Test
	public void title_verify() throws Exception
	{
		 driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		
		String title=driver.getTitle();
	
	sa.assertTrue(title.equalsIgnoreCase("Googul"));//Soft Assert - this return false but still run remaining lines
		System.out.println("soft assert completed");
		take_screenshot();		
	//	Assert.assertTrue(title.equalsIgnoreCase("Googul"));// Hard Assert - this return false and doesnt run remaining lines
		//System.out.println("hard assert completed");		
	}
	public static void take_screenshot() throws Exception
	{
		File src= driver.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
		Date d =new Date();
		String fname=sf.format(d)+".png";
		File dest=new File("C:\\Users\\tanvi\\Downloads\\"+fname);
		System.out.println(dest);
		FileHandler.copy(src, dest);
		Reporter.log("<br><img src='"+dest+"' height='300' width='300'/><br>");  
	}
}
