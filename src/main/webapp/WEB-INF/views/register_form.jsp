<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Manager Home</title>
<style>
body {
	font-size: 1.5vw;
	font-family: Arial, Helvetica, sans-serif;
	background: rgb(250, 250, 250);
}

#loginBox {
	margin: auto;
	width: 20vw;
	height: 45vh;
	padding: 2vw;
	background: white;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.05), 0 6px 20px 0
		rgba(0, 0, 0, 0.04);
	background: white;
	text-align: center;
	margin-top: 10vh;
}

.inputBox {
	border: none; /* Removes border */
	outline: none; /* Removes outline when focused */
	padding: 1vw;
	background-color: #e8f0f8; /* Sets background color */
	width: 15vw;
	margin: auto;
	border: none;
	font-size: 1vw;
	margin: 1vh 0vh;
}

.loginBtn {
	background-color: rgb(239, 57, 56); /* Red background color */
	border: none;
	color: white;
	padding: 1vw;
	text-align: center;
	cursor: pointer;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	width: 17vw;
	font-size: 1vw;
	margin: 0.5vh 0vh;
	font-weight: bold;
}

#register {
	font-size: 1vw;
	margin: 3vh 0vh;
	color: gray;
	cursor: pointer;
}

#registerLink {
	text-decoration: none;
	color: #007bff;
}

#registerLink:hover {
	color: blue; /* White text color */
}

#title {
	font-family: 'Roboto Condensed', sans-serif;
	margin-top: 3vh;
	margin-bottom: 1vh;
	color: gray;
}

#bigTittle {
	font-size: 1.8vw;
	text-align: center;
	color: gray;
	margin-bottom: 3vh;
}

#noti {
	color: red;
	font-size: 1vw;
}
</style>
</head>
<body>

	<div id="loginBox">
		<div id="bigTittle">Đăng Ký Tài Khoản</div>
		<hr style="border: 1px solid #f0f0f0;">
		<form:form action="save" method="post" modelAttribute="user">
			<form:hidden path="isAdmin" value="false" />
			<form:input path="username" placeholder="Tài Khoản" class="inputBox"
				required="required" />
			<form:input path="password" placeholder="Mật Khẩu" type="password"
				class="inputBox" required="required" />
			<input type="submit" class="loginBtn" value="Đăng Ký" />
			<div id="register">
				<a id="registerLink" href="backLogin">Quay lại</a>
			</div>
			<div id="noti">${noti}</div>
		</form:form>
	</div>
</body>
</html>