package bai_tap_nop.pojo;

/**
 * Tóm tắt: Đại diện cho người dùng đã đăng nhập. Session chỉ cần lưu email để
 * nhận diện người dùng, không lưu lại mật khẩu.
 */
public class User {
	private String email;

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
