package resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.apache.log4j.Logger;

public class Servicerestart {
	public static Logger log = Logger.getLogger(Servicerestart.class.getName());

	// Run in cmd and eclipse in admin mode otherwise access is denied error will
	// come
	public void service() throws InterruptedException {

		invokeASGService("stop", "ASG Service stopped.");

		invokeASGService("start", "ASG Service Started.");

	}

	public void invokeASGService(String operation, String message) throws InterruptedException {

		try {
			Process p = Runtime.getRuntime().exec("sc " + operation + " ALTI-ASG");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				log.info(line);
				line = reader.readLine();
			}

		} catch (IOException e1) {
			log.error(e1);
		} catch (InterruptedException e2) {
			log.error(e2);
		}
		Thread.sleep(50000);
		log.info(message);

	}
}
