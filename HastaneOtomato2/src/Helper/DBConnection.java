package Helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String serverName = "DESKTOP-Q0N2SBA";
	private static String dbName = "diyetisyen";
	private static String url = "jdbc:sqlserver://" + serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
	private static String user = "test";
	private static String pass = "123456";
	private Connection conn = null;

	public DBConnection() {}

	public Connection connDb() {
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}