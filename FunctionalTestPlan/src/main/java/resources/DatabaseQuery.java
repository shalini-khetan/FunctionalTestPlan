package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class DatabaseQuery {
	public static Logger log = Logger.getLogger(DatabaseQuery.class.getName());

	List<String> beforeRestart = new ArrayList<String>();
	List<String> afterRestart = new ArrayList<String>();

	DBConnection dbConnection = new DBConnection();

	String data = null;

	public List<String> getBeforeRestart() {
		return beforeRestart;
	}

	public void setBeforeRestart(List<String> beforeRestart) {
		this.beforeRestart = beforeRestart;
	}

	public List<String> getAfterRestart() {
		return afterRestart;
	}

	public void setAfterRestart(List<String> afterRestart) {
		this.afterRestart = afterRestart;
	}

	public void compare() {
		if (getBeforeRestart() != null && getAfterRestart() != null
				&& getBeforeRestart().size() == getAfterRestart().size()) {
			if (getAfterRestart().equals(getBeforeRestart()))
				log.info("Values are same");
			else
				log.info("Values are different");

		} else
			log.info("Array size is not equal");

	}

	public List<String> quarantineQuery() throws IOException {
		
		
		
		String query = "select factory_id, operation_type, total_failure_count, current_quarantine_count from wdt_quarantine_details where factory_id=74976637"
				+ "\n";
		ArrayList<List<String>> columnNameAndData = dbConnection.populateColumnNameAndColumnData(query);
		List<String> columnName = columnNameAndData.get(0);
		List<String> columnData = columnNameAndData.get(1);
		printData(columnName, columnData);
		return columnData;
	}

	public void wirelessQuery() throws IOException {
		String query = "select * from wireless_sync_group" + "\n";
		ArrayList<List<String>> columnNameAndData = dbConnection.populateColumnNameAndColumnData(query);
		List<String> columnName = columnNameAndData.get(0);
		List<String> columnData = columnNameAndData.get(1);
		printData(columnName, columnData);
	}

	public void printData(List<String> columnName, List<String> columnData) {
		for (int i = 0; i < columnName.size(); i++) {
			//System.out.println(columnName.get(i) + "\t");
			//log.info(columnName.get(i) + "\t");
		}
		//log.info("\n");
		for (int i = 0; i < columnData.size(); i++) {
//			System.out.println(columnData.get(i));
//			log.info(columnData.get(i));
		}

	}

}
