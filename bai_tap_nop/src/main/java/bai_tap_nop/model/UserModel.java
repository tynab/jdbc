package bai_tap_nop.model;

import java.sql.SQLException;

import bai_tap_nop.pojo.User;

import static bai_tap_nop.connection.MySQLConnection.getConnection;

/**
 * Tóm tắt: Chứa logic truy vấn bảng user để kiểm tra email và mật khẩu khi
 * người dùng gửi form đăng nhập.
 */
public class UserModel {
	private static final String FIND_BY_EMAIL_AND_PASSWORD_SQL = """
			SELECT email
			FROM `user`
			WHERE email = ? AND password = MD5(?)
			""";

	/**
	 * Trả về User khi email/mật khẩu hợp lệ, ngược lại trả về null để controller
	 * hiển thị lỗi đăng nhập.
	 */
	public User findByEmailAndPassword(String email, String password) {
		if (isBlank(email) || isBlank(password)) {
			return null;
		}

		try (
				var connection = getConnection();
				var statement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD_SQL)) {
			statement.setString(1, email.trim());
			statement.setString(2, password);

			try (var result = statement.executeQuery()) {
				if (result.next()) {
					return new User(result.getString("email"));
				}
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Không thể truy vấn thông tin đăng nhập.", e);
		}
		return null;
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
