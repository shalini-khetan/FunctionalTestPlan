package resources;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBConnection {
	public Properties prop;
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;

	public static Logger log = Logger.getLogger(DBConnection.class.getName());

	public void makeDBConnection() throws SQLException, ClassNotFoundException, IOException {
		prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.properties");
		prop.load(input);
		String DB_URL = prop.getProperty("DB_URL");
		String DB_Username = prop.getProperty("DB_Username");
		String DB_password = prop.getProperty("DB_Password");
		// Register JDBC driver
		Class.forName("org.postgresql.Driver");
		// Open a connection
		conn = DriverManager.getConnection(DB_URL, DB_Username, DB_password);
	}

	public ResultSet queryDB(String query) throws SQLException {
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery(query);
		} catch (Exception e) {
			log.error("Error while querying database: " + e.toString());
		}
		return null;
	}

	public ArrayList<List<String>> populateColumnNameAndColumnData(String query) throws IOException {
		List<String> columnData = new ArrayList<>();
		List<String> columnNames = new ArrayList<>();
		ArrayList<List<String>> columnNameAndData = new ArrayList<List<String>>();
//		Make DB Connection
		try {
			makeDBConnection();
			rs = queryDB(query);
			if (rs != null) {
				ResultSetMetaData columns = rs.getMetaData();
				for (int i = 1; i < columns.getColumnCount(); i++) {
					columnNames.add(columns.getColumnName(i));
				}

				while (rs.next()) {
					for (int i = 0; i < columnNames.size(); i++) {
						columnData.add(rs.getString(columnNames.get(i)));
					}
					// System.out.println("\n");
				}

				columnNameAndData.add(columnNames);
				columnNameAndData.add(columnData);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception mysqlEx) {
				log.error(mysqlEx.toString());
			}
		}
		return columnNameAndData;

	}
}
