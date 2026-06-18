package bai_tap_nop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static bai_tap_nop.constant.Constant.SESSION_USER;

/**
 * Tóm tắt: Filter xác thực theo đúng yêu cầu README. Người chưa đăng nhập bị
 * chuyển về trang login, còn người đã đăng nhập không quay lại form login nữa.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "/login", "/login.jsp", "/welcome", "/welcome.jsp" })
public class AuthFilter implements Filter {
	private static final String LOGIN_PATH = "/login";
	private static final String LOGIN_JSP_PATH = "/login.jsp";
	private static final String WELCOME_PATH = "/welcome";
	private static final String WELCOME_JSP_PATH = "/welcome.jsp";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Filter không cần cấu hình khởi tạo riêng.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var req = (HttpServletRequest) request;
		var resp = (HttpServletResponse) response;
		var session = req.getSession(false);
		var isLoggedIn = session != null && session.getAttribute(SESSION_USER) != null;
		var servletPath = req.getServletPath();

		if (isLoginPath(servletPath) && isLoggedIn) {
			resp.sendRedirect(req.getContextPath() + WELCOME_PATH);
			return;
		}

		if (isWelcomePath(servletPath) && !isLoggedIn) {
			resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
			return;
		}

		chain.doFilter(request, response);
	}

	private boolean isLoginPath(String servletPath) {
		return LOGIN_PATH.equals(servletPath) || LOGIN_JSP_PATH.equals(servletPath);
	}

	private boolean isWelcomePath(String servletPath) {
		return WELCOME_PATH.equals(servletPath) || WELCOME_JSP_PATH.equals(servletPath);
	}

	@Override
	public void destroy() {
		// Filter không giữ tài nguyên ngoài nên không cần dọn dẹp.
	}
}
