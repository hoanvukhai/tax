<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	font-size: 2.5vh;
	font-family: Arial, Helvetica, sans-serif;
}

table {
	width: 40vw;
	border: 0.1vw solid lightgray;
	background-color: rgb(245, 251, 252);
	border-radius: 1vw;
}

th, td {
	padding: 1vh 1vw;
	text-align: left;
	padding: 2vh 1vw;
}

#createEditInfoLink {
	text-decoration: none;
}

#linkContainer {
	text-align: center;
}

#detailTax {
	display: inline-block;
	padding: 1vh 1vw; /* Adjust padding using vh and vw units */
	background-color: lightblue; /* Blue color, you can change it */
	text-decoration: none; /* Remove underline */
	border-radius: 0.5vw; /* Rounded corners */
	color: white;
}

a {
	text-decoration: none;
	color: black;
}

#title {
	margin: 1.5vw 0;
	font-size: 4vh;
}

#menu {
	width: 60vw;
	margin: auto;
	font-size: 3vh;
	padding: 2vw;
	text-align: center;
}

.menuSection {
	border: 0.2vw solid lightgray;
	padding: 1vw;
	background-color: lightblue;
	color: white;
}

.menuSection:hover {
	background-color: rgb(90, 176, 205);
}

.clicked {
	background-color: rgb(90, 176, 205);
}

.userInput {
	width: 18vw;
	height: 3vh;
}
</style>
</head>
<body>
	<div id="menu">
		<a class="menuSection" href="userInfo">Thông Tin Người Dùng</a><a
			class="menuSection clicked" href="manageTaxRange">Phần Trăm Thuế</a><a
			class="menuSection" href="manageUser">Quản Lý Tài Khoản</a><a
			class="menuSection" href="logoutAdmin">Đăng Xuất</a>
	</div>
	<div align="center">
		<div id="title">Điền Thông Tin</div>
		<div style="color: rgb(239, 57, 56);">${noti}</div>
		<form:form action="saveTaxRange" method="post"
			modelAttribute="taxRange">
			<table>
				<form:hidden path="id" />
				<tr>
					<td>Lương Từ</td>
					<td><form:input type="number" class="userInput"
							path="startRange" required="required" /> đ</td>
				</tr>
				<tr>
					<td>Đến</td>
					<td><form:input type="number" class="userInput"
							path="endRange" required="required" /> đ</td>
				</tr>
				<tr>
					<td style="width: 15vw;">Phần Trăm Thuế</td>
					<td><form:input type="number" class="userInput"
							path="taxPercentage" required="required" /> %</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center"><input
						style="padding: 1vh 1vw; background-color: rgb(90, 176, 205); color: white; border: none; font-size: 1vw; border-radius: 0.5vw;"
						type="submit" value="Lưu Thông Tin" />
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>