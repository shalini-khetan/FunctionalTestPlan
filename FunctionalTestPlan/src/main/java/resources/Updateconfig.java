package resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Updateconfig {
	public static Properties prop;
	public static Logger log = Logger.getLogger(Updateconfig.class.getName());

	public static void readAapConfigFile() throws IOException {
		String text = "";
		int lineNumber;
		prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.properties");
		prop.load(input);
		String config = prop.getProperty("aapconfig");
		BufferedReader readbuffer = null;

		try {
			FileReader readfile = new FileReader(config);
			readbuffer = new BufferedReader(readfile);
			for (lineNumber = 1; lineNumber < 10; lineNumber++) {
				if (lineNumber == 4) {
					text = readbuffer.readLine();
				} else
					readbuffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (readbuffer != null) {
				readbuffer.close();
			}

		}
		log.info(" The specific Line is:" + text);
	}

	public static void writetoproperties() throws IOException {
		prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.properties");
		prop.load(input);
		String customer = prop.getProperty("Customerproperties");
		BufferedWriter out = null;

		try {
			FileWriter fstream = new FileWriter(customer, true); // true tells to append data.
			out = new BufferedWriter(fstream);
			out.write("\nsend.wireless.aap.config=true");
			log.info("Updated the customer.properties file");
		}

		catch (IOException e) {
			log.error("Error: " + e.getMessage());
		}

		finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
