package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout_Page {
	//properties
public WebDriver driver;
	
//locators - elements
	@FindBy(linkText="Logout?")
	public WebElement logout;
	
	//methods - constructor methods
	public Logout_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//methods- operational methods
	public void click_Logout()
	{
		logout.click();
	}
	
}
