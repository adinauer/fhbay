package at.dinauer.fhbay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCleaner {
	private static final String[] TABLES_TO_CLEAN = {
		"Address",
		"Article",
		"Article_Category",
		"Category",
		"Customer",
		"Customer_Phone",
		"PaymentData",
		"Phone"
	};
	
	private Connection connection;

	public void clean() throws Exception {
		prepareForCleaning();
        
        cleanTables();
        
        afterCleaning();
	}

	private void cleanTables() throws SQLException {
		for (String table : TABLES_TO_CLEAN) {
			connection.createStatement().execute("TRUNCATE TABLE " + table);
		}
	}

	private void prepareForCleaning() throws ClassNotFoundException,
			SQLException {
		loadDriver();
        openConnection();
        disableForeignKeyChecks();
	}

	private void afterCleaning() throws SQLException {
		enableForeignKeyChecks();
        closeConnection();
	}

	private void loadDriver() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	private void openConnection() throws ClassNotFoundException, SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/fhbay", "fhbay", "pwd");
	}
	
	private void disableForeignKeyChecks() throws SQLException {
		connection.createStatement().execute("SET FOREIGN_KEY_CHECKS=0");
	}

	private void enableForeignKeyChecks() throws SQLException {
		connection.createStatement().execute("SET FOREIGN_KEY_CHECKS=1");
	}

	private void closeConnection() throws SQLException {
		connection.close();
	}
}