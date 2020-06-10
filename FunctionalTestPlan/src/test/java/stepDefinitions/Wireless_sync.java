package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.HomePage;
import pageObjects.WirelessSync;
import resources.Updateconfig;
import resources.Base;

public class Wireless_sync extends Base {
public  static Logger log =Logger.getLogger(Wireless_sync.class.getName());

@Given("^set wireless sync feature in customer properties$")
public void set_wireless_sync_feature_in_customer_properties() throws Throwable {
log.info("Adding wireless sync properties in customer properties");
 Updateconfig.writetoproperties();	
//log.info("Restarting the ASG");
//Servicerestart restartservice= new Servicerestart();
//restartservice.service();
}
@Given("^Initialize the browser$")
public void initialize_the_browser() throws Throwable {
	driver=initializeDriver();
}

@When("^Redirect to ASGC \"([^\"]*)\"$")
public void redirect_to_ASGC(String asgurl) throws Throwable {
	driver.get(asgurl);
}

@When("^login in ASG with username \"([^\"]*)\" and password \"([^\"]*)\"$")
public void login_in_ASG_with_username_and_password(String username, String password) throws Throwable {
	login();
}

	@Then("^Add Wireless sync with name \"([^\"]*)\" and description \"([^\"]*)\" and perform other action$")
	public void add_Wireless_sync_with_name_and_description_and_perform_other_action(String syncname, String syncdescription) throws Throwable {
		WirelessSync wirelesssync=new WirelessSync(driver);
		HomePage homepage= new HomePage(driver);
		try {
		homepage.adminMenu().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.switchTo().frame(wirelesssync.frame1());
		log.info("Switched to first frame of wireless sync");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch (Exception e) {
			log.error(e);
		}
		try {
		if (driver.findElements(By.xpath("//span[text()='Wireless Sync']")).size() != 0) {
			log.info("Wireless Sync Option is exist");
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			wirelesssync.wirelesslink().click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.switchTo().frame(wirelesssync.frame2());
			log.info("Switched to second frame of wireless sync");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.switchTo().frame(wirelesssync.frame3());
			log.info("Switched to third frame of wireless sync");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			wirelesssync.addSyncButton().click();
			wirelesssync.groupName().sendKeys(syncname);
			wirelesssync.groupDesc().sendKeys(syncdescription);
			wirelesssync.save().click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Updateconfig.readAapConfigFile();
			wirelesssync.edit().click();
			wirelesssync.back().click();
			wirelesssync.delete().click();
			Updateconfig.readAapConfigFile();
		}
		else  {
		log.info( "Wireless_sync is not exist");
		}
		}
	 catch(Exception e){
			log.error(e);
		}
	}

	@Then("^Close the browser$")
	public void close_the_browser() throws Throwable {
		driver.close();
	}
	


}
