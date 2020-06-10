package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AAPConfig {
	public WebDriver driver;
	By aapadminbutton= By.xpath("//span[contains(text(),'AAP Admin')]");
	By autoconfigbutton=By.xpath("//body//input[1]");
	By unconfigerrormsg= By.xpath("//div[@id='errorStatement']");
	By backbutton=By.id("backBtn");
	By cancelbutton=By.xpath("//input[@id='cancelBtn']");
	
	public AAPConfig(WebDriver driver) {
		this.driver=driver;
	}
	public WebElement configButton()
	{
		return driver.findElement(autoconfigbutton);
	}
	public WebElement unconfigErrormessageg()
	{
		return driver.findElement(unconfigerrormsg);
	}
	public WebElement backButton()
	{
		return driver.findElement(backbutton);
	}
	public WebElement cancelButton()
	{
		return driver.findElement(cancelbutton);
	}
	public WebElement adminButton() {
		return driver.findElement(aapadminbutton);
	}
	
}
