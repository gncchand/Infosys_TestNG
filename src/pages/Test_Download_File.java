package pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test_Download_File
{
public WebDriver driver;
	
	@BeforeClass
	public void testSetup() throws Exception
	{
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
	}
	
	@Test
	public void example_VerifyExpectedFileName() 
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		String tmpFolderPath = System.getProperty("java.io.tmpdir");
		String expectedFileName = "test.pdf";
		File file = new File(tmpFolderPath + expectedFileName);
		 System.out.println("file name : "+expectedFileName);
		 System.out.println("folder path : "+tmpFolderPath);
		if (file.exists())
		    System.out.println(file.getName()+" is present in : "+file.getPath());
	}
	

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
}
