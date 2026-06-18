package bai_tap_nop.constant;

/**
 * Tóm tắt: Gom các khóa dùng chung trong ứng dụng để controller, filter và
 * JSP không phải tự gõ lại chuỗi literal.
 */
public class Constant {
	// Khóa session lưu thông tin người dùng đã đăng nhập.
	public static final String SESSION_USER = "currentUser";

	// Tên request attribute dùng để hiển thị lỗi trên màn hình đăng nhập.
	public static final String LOGIN_ERROR = "loginError";

	private Constant() {
		// Không cho khởi tạo lớp tiện ích chỉ chứa hằng số.
	}
}
