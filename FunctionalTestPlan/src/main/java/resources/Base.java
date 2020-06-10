package resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import pageObjects.LoginPage;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public static Logger log = Logger.getLogger(Base.class);

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.properties");
		prop.load(input);
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			// execute in chrome driver
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			// execute in firefox driver
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equals("IE")) {
			// execute in internet explorer
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Drivers\\geckodriver.exe");
			driver = new InternetExplorerDriver();

		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;

	}

	public void login() throws IOException {
		try {
			LoginPage loginPage = new LoginPage(driver);
			try {
				log.info(String.format("Opening ASGC-"+ prop.getProperty("url")));
				String.format("Browser initiated" + prop.getProperty("url"));
				log.info("Login page is displayed");
			} catch (AssertionError e) {
				log.error("Error while opening ASGC" + e);
			}
			try {
				loginPage.username().sendKeys("asgadmin");
				loginPage.password().sendKeys("asgAdm1n!");
				loginPage.login().click();
				// Assert.assertTrue(l.homeicon().isDisplayed());
				log.info("Login is successful");
			} catch (AssertionError e) {
				log.error("Error while login into ASGC" + e);
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Exception: " + e.toString());
		}
	}

}
