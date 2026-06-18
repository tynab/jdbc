<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
	Tóm tắt: Form đăng nhập nhận email/mật khẩu và hiển thị lỗi do
	LoginController truyền về khi thông tin không hợp lệ.
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./css/login.css">
<title>Login</title>
</head>
<body>
	<div class="login-box">
		<h2>Đăng nhập</h2>
		<form action="${pageContext.request.contextPath}/login" method="post" class="form">
			<div class="user-box">
				<input type="email" name="email" autocomplete="email" required> <label>Email</label>
			</div>
			<div class="user-box">
				<input type="password" name="password" autocomplete="current-password" required> <label>Mật khẩu</label>
			</div>
			<button type="submit">
				<span></span><span></span> <span></span><span></span> Đăng nhập
			</button>
		</form>
	</div>
	<c:if test="${not empty loginError}">
		<script>
			alert("${loginError}");
		</script>
	</c:if>
</body>
</html>
