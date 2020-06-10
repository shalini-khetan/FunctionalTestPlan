package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WirelessSync {

	public WebDriver driver;
	By admin =By.xpath("//a[contains(text(),'Admin')]");
	By wirelesssync = By.xpath("//span[contains(text(),'Wireless Sync')]");
	By addsync = By.xpath("//div[@class='navigation']//input");
	By groupname = By.xpath("//input[@id='saveOrUpdateSyncGroup_wsg_wsgName']");
	By groupdesc= By.xpath("//input[@name='wsg.description']");
	By save= By.xpath("//input[@value='Save']");
	By edit=By.xpath("//input[@value='Edit']");
	By delete=By.xpath("//input[@value='Delete']");
	By back=By.xpath("//input[@value='<<']");
	//Frames
	By frame1= By.xpath("//iframe[@name='display']");
	By frame2=By.xpath("//iframe[@name='window']");
	By frame3= By.xpath("//frame[@name='list']");
	public WirelessSync(WebDriver driver) {
		this.driver=driver;
	}
	public WebElement admin()
	{
	 return driver.findElement(admin);
	}
	public WebElement wirelesslink()
	{
	 return driver.findElement(wirelesssync);
	}
	public WebElement addSyncButton()
	{
	 return driver.findElement(addsync);
	}
	public WebElement groupName()
	{
	 return driver.findElement(groupname);
	}
	public WebElement groupDesc()
	{
	 return driver.findElement(groupdesc);
	}
	
	public WebElement save()
	{
	 return driver.findElement(save);
	}
	public WebElement edit()
	{
	 return driver.findElement(edit);
	}
	public WebElement delete()
	{
	 return driver.findElement(delete);
	}
	public WebElement back()
	{
	 return driver.findElement(back);
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
	public WebElement frame3()
	{ 
		WebElement framethree = driver.findElement(frame3);
		return framethree;
	}	
}
