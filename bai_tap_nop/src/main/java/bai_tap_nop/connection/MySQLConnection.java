package bai_tap_nop.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Tóm tắt: Tạo kết nối JDBC tới MySQL cho các lớp model sử dụng khi truy vấn
 * thông tin đăng nhập.
 */
public class MySQLConnection {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3307/backendfoundation";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";

	/**
	 * Trả về một kết nối mới. Caller chịu trách nhiệm đóng kết nối bằng
	 * try-with-resources để tránh rò rỉ tài nguyên.
	 */
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Không tìm thấy MySQL JDBC Driver.", e);
		}
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	private MySQLConnection() {
		// Không cho khởi tạo lớp tiện ích chỉ tạo connection.
	}
}
