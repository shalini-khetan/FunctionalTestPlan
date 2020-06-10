package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import pageObjects.HomePage;
import pageObjects.RetailsOps;
import resources.AsgUpgrade;
import resources.Base;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class RetailOps extends Base {

	public static Logger log = Logger.getLogger(RetailOps.class.getName());

	@Given("^Initialize the browser with chrome$")
	public void initialize_the_browser_with_chrome() throws Throwable {
		driver = initializeDriver();
	}

	@Given("^Redirect to the ASGC \"([^\"]*)\"$")
	public void redirect_to_the_ASGC(String asgc) throws Throwable {
		driver.get(asgc);

	}

	@Given("^login into ASG with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void login_into_ASG_with_username_and_password(String username, String password) throws Throwable {
		login();
	}

	@Given("^Click on retail ops menu$")
	public void click_on_retail_ops_menu() throws Throwable {
		try {
			HomePage Homepage = new HomePage(driver);
			Homepage.retailopsMenu().click();
			// Assert.assertTrue(false);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@When("^Select \"([^\"]*)\" and enter \"([^\"]*)\" value$")
	public void select_something_and_enter_something_value(String selectscreen, String timeout) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		RetailsOps retailops = new RetailsOps(driver);
		driver.switchTo().frame(retailops.frame1());
		log.info("Switch to first frame of retail ops");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.switchTo().frame(retailops.frame2());
		log.info("Switch to second frame of retail ops");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		retailops.screen().selectByVisibleText(selectscreen);
		retailops.timeout().clear();
		retailops.timeout().sendKeys(timeout);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		retailops.process().click();
	}

	@Given("^Upgrade the ASG$")
	public void upgrade_the_asg() throws Throwable {
		AsgUpgrade upgradeasg = new AsgUpgrade();
		// upgradeasg.asgupgrade();
		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.MINUTES);

	}

	@Then("^Retail ops batch should created$")
	public void retail_ops_batch_should_created() throws Throwable {
		log.info("Retail Ops operation is success");
	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		driver.close();
	}

}
