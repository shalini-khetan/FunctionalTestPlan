package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public WebDriver driver;
	
	By username=By.id("loginForm_user_username");
	By password=By.id("loginForm_user_password");
	By login=By.id("loginForm_0");
	By console=By.xpath("//p[contains(text(),'xyz')]");
	By icon=By.xpath("//div[@id='titleForm']//img");
	By homeicon=By.xpath("//div[@id='logo']//a//img");
	
	//Console Login
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	public WebElement username()
	{
		return driver.findElement(username);
	} 	
	public WebElement password()
	{
		return driver.findElement(password);
	}
	public WebElement login()
	{
		return driver.findElement(login);
	}
	public WebElement console()
	{
		return driver.findElement(console);
	} 
	public WebElement icon()
	{
		return driver.findElement(icon);
	} 
	public WebElement homeicon()
	{
		return driver.findElement(homeicon);
	} 
}
