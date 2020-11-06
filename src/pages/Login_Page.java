package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
	//properties
	public WebDriver driver;
	
	//locators - elements
	@FindBy(linkText="Login")
	public WebElement login;
	
	@FindBy(id="username")
	public WebElement uid;
	
	@FindBy(id="password")
	public WebElement pwd;
	
	@FindBy(id="tp-sign-in")
	public WebElement submit;
	
	//locators  - error messages
	@FindBy(xpath="//*[@id='tp-message-error']")
	public WebElement errmsg;
	
	//methods - constructor methods
	public Login_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//methods- operational methods
	public void uid_enter(String x)
	{
		uid.sendKeys(x);
	}
	
	public void pwd_enter(String y)
	{
		pwd.sendKeys(y);
	}
	
	public void click_Submit()
	{
		submit.click();
	}	

}
