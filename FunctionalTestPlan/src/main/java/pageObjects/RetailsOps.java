package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RetailsOps {

	public WebDriver driver;
	
	By screen = By.xpath("//select[@id='executeStoreOps_screenName']");
	By timeout = By.id("executeStoreOps_duration");
	By process = By.xpath("//tr[1]//td[1]//input[1]");
	//Frames
	By frame1= By.xpath("//iframe[@name='display']");
	By frame2=By.name("form");
	By toogle=By.xpath("//div[@class='title']");
	public RetailsOps(WebDriver driver) {
		this.driver=driver;
	}

	public Select screen()
	{
	WebElement screenDropdown = driver.findElement(screen);
	Select dropdown = new Select(screenDropdown);
	return dropdown;
	}
	public WebElement timeout()
	{
	 return driver.findElement(timeout);
	}
	public WebElement process()
	{
	 return driver.findElement(process);
	}
	public WebElement frame1()
	{ 
		WebElement frameone = driver.findElement(frame1);
		return frameone;
	}	
	public WebElement frame2()
	{ 
		WebElement frametwo = driver.findElement(frame2);
		return frametwo;
	}	
	public WebElement toogle()
	{
	 return driver.findElement(toogle);
	}
}
