package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	public WebDriver driver;
	// Object of retail ops menu
 By retailops =By.xpath("//a[contains(text(),'Retail Ops')]");
 
//Object of Admin menu
By admin =By.xpath("//a[contains(text(),'Admin')]");

//Object of schedule menu
By schedule =By.xpath("//a[contains(text(),'Schedule')]");
By fframe=By.xpath("//iframe[@name='display']");
By sframe=By.xpath("//iframe[@id='dashIframe']");
By store= By.xpath("//tr[2]//td[2]//a[1]");
By home= By.xpath("//a[contains(text(),'Home')]");

public HomePage(WebDriver driver) {
	this.driver=driver;
}

public WebElement retailopsMenu()
{
return driver.findElement(retailops);
}
public WebElement adminMenu()
{

	return driver.findElement(admin);
   
}
public WebElement scheduleMenu()
{
	return driver.findElement(schedule);
}
public WebElement fframe()
{
	return driver.findElement(fframe);
}
public WebElement sframe()
{
	return driver.findElement(sframe);
}
public WebElement store()
{
	return driver.findElement(store);
}
public WebElement home()
{
	return driver.findElement(home);
}

}
 
