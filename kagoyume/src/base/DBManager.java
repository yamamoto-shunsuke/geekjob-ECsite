package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Kagoyume_db?characterEncoding=UTF-8&serverTimezone=JST",
					"yamamoto", "shun1220");
			System.out.println("DBConnected");
			return con;
		} catch (ClassNotFoundException e) {
			throw new IllegalMonitorStateException(e.getMessage());
		} catch (SQLException e) {
			throw new IllegalMonitorStateException(e.getMessage());
		}
	}

}
