package bai_tap_nop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bai_tap_nop.model.UserModel;

import static bai_tap_nop.constant.Constant.*;

/**
 * Tóm tắt: Điều khiển màn hình đăng nhập, nhận email/mật khẩu từ form, kiểm tra
 * database và lưu User vào session khi đăng nhập thành công.
 */
@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final int SESSION_TIMEOUT_SECONDS = 15 * 60;

	private final UserModel userModel = new UserModel();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		var email = req.getParameter("email");
		var password = req.getParameter("password");

		try {
			var user = userModel.findByEmailAndPassword(email, password);
			if (user != null) {
				var session = req.getSession(true);
				session.setAttribute(SESSION_USER, user);
				session.setMaxInactiveInterval(SESSION_TIMEOUT_SECONDS);
				resp.sendRedirect(req.getContextPath() + "/welcome");
				return;
			}
			forwardLoginWithError(req, resp, "Email hoặc mật khẩu không đúng.");
		} catch (IllegalStateException e) {
			log("Lỗi kiểm tra đăng nhập.", e);
			forwardLoginWithError(req, resp, "Không thể kết nối cơ sở dữ liệu.");
		}
	}

	private void forwardLoginWithError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute(LOGIN_ERROR, message);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
}
