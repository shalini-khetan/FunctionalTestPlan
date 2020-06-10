package resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AsgUpgrade {
	public static Logger log = Logger.getLogger(AsgUpgrade.class.getName());

	public void asgupgrade() throws IOException, InterruptedException {
		
		Properties prop = new Properties();
		prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.properties");
		prop.load(input);
		String ASGPath = prop.getProperty("ASGpath");
		String DbSuperUserPassword = prop.getProperty("Super_User_Password");
		String DbUserJbossPassword=prop.getProperty("Jboss_Password");
		String command = "cmd /c start " + "Start /w " + ASGPath
				+ " /S /DBSUPERUSERPASSWORD" + DbSuperUserPassword + "/DBUSERJBOSSPASSWORD" + DbUserJbossPassword;
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (IOException e) {
			log.error(e);
		}

	}
}
