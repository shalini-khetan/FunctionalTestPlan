package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pageObjects.AAPConfig;
import pageObjects.HomePage;
import pageObjects.WirelessSync;
import resources.Base;

public class Autoconfig extends Base {
	public static Logger log = Logger.getLogger(Autoconfig.class.getName());

	@Given("^Initialize browser")
	public void initialize_the_browser_with_chrome() throws Throwable {
		driver = initializeDriver();
	}

	@Given("^Redirect to ASG console \"([^\"]*)\"$")
	public void redirect_to_ASG_console(String url) throws Throwable {
		driver.get(url);
	}

	@Given("^Login into ASG with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void login_into_ASG_with_username_and_password(String username, String password) throws Throwable {
		login();
	}

	@Given("^Click on admin menu$")
	public void click_on_admin_menu() throws Throwable {
		WirelessSync wirelesssync=new WirelessSync(driver);
		HomePage homepage= new HomePage(driver);
		try {
		homepage.adminMenu().click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.switchTo().frame(wirelesssync.frame1());
		log.info("Switched to first frame of autoconfig sync");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath("//span[text()='AAP Admin']")).size() != 0) { 
			log.info("AAP Admin Option is exist");
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    driver.findElement(By.xpath("//span[contains(text(),'AAP Admin')]")).click();
		    log.info("Clicked on AAP admin button");
			driver.switchTo().frame(wirelesssync.frame2());
			log.info("Switched to second frame of autoconfig sync");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.switchTo().frame(wirelesssync.frame3());
			log.info("Switched to third frame of autoconfig sync");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		}catch (Exception e) {
			log.error(e);
		}
	}
	@Then("^Perform Autoconfig function$")
	public void perform_Autoconfig_function() throws Throwable {
		AAPConfig aapconf= new AAPConfig(driver);
		//driver.findElement(By.xpath("//body//input[1]")).click();
		aapconf.adminButton().click();
		log.info("Clicked on Auto config button");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//String error = driver.findElement(By.xpath("//div[@id='errorStatement']")).getText();
		String error=aapconf.unconfigErrormessageg().getText();
		log.info(error);
		if (error.contentEquals("No unconfigured AAPs found.")) {
			//driver.findElement(By.id("backBtn")).click();
		     aapconf.backButton().click();
		} else {
			aapconf.cancelButton().click();
			log.info("Unconfigured AAP found");
		}

	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			try {
				File destination = new File(
						System.getProperty("user.dir") + "/ErrorScreenshots/" + screenshotName + dateName + ".png");
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, destination);
				Reporter.addScreenCaptureFromPath(destination.toString());
			} catch (IOException e) {
			}
		}

	}

}
