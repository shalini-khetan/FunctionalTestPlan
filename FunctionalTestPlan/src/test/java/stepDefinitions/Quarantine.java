package stepDefinitions;

import org.apache.log4j.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import resources.DatabaseQuery;
import resources.Servicerestart;

public class Quarantine {
	public static Logger log = Logger.getLogger(Quarantine.class.getName());
	DatabaseQuery queryfromdb = new DatabaseQuery();

	@Given("^Connect to database and fetch the value Before ASG Restart$")
	public void connect_to_database_and_fetch_the_value_Before_ASG_Restart() throws Throwable {
		log.info("Fetching value from database before ASG restart");
		queryfromdb.setBeforeRestart(queryfromdb.quarantineQuery());
		log.info("Fetched value before ASG restart" + queryfromdb.getBeforeRestart());

	}

	@Given("^Restart the ASG$")
	public void restart_the_ASG() throws Throwable {
		Servicerestart servicerestart = new Servicerestart();
		log.info("Service is going to restart");
		//servicerestart.service();
		log.info("Restarted");
	}

	@Then("^Connect to database and fetch the value After ASG Restart$")
	public void connect_to_database_and_fetch_the_value_After_ASG_Restart() throws Throwable {
		log.info("Fetching value from database after ASG restart");
		queryfromdb.setAfterRestart(queryfromdb.quarantineQuery());
		log.info("Fetched value before ASG restart" + queryfromdb.getBeforeRestart());
	}

	@Then("^Compare the value$")
	public void compare_the_value() throws Throwable {
		queryfromdb.compare();
	}
}
