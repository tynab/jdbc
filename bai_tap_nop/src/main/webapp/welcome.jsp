<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
	Tóm tắt: Trang thông tin sau đăng nhập. AuthFilter đảm bảo chỉ session hợp lệ
	mới được vào trang này, còn form POST dùng để đăng xuất.
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./css/welcome.css">
<title>Welcome</title>
</head>
<body>
	<div class="main">
		<video autoplay muted loop id="video" playsinline>
			<source src="./video/background.mp4" type="video/mp4">
		</video>
		<div class="overlay"></div>
		<div class="heading">
			<h1 class="head">
				WELCOME TO OUR <span>WEBSITE</span>
			</h1>
			<h3 class="sub">Bạn đã đăng nhập với email: <c:out value="${sessionScope.currentUser.email}" /></h3>
			<form action="${pageContext.request.contextPath}/welcome" method="post">
				<div class="btns">
					<button type="submit">Đăng xuất</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
