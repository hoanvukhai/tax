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
	padding: 1vh 1vw;
}

th, td {
	padding: 0.5vh 1vw; /* Padding in vh and vw units */
	text-align: left;
	font-size: 2.5vh; /* Font size in vh units */
	padding: 2vh 1vw;
}

#linkContainer {
	
}

#createEditInfoLink {
	text-decoration: none;
	color: black;
	font-weight: bold;
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
		<a class="menuSection clicked" href="userInfo">Thông tin người
			dùng</a><a class="menuSection" href="manageTaxRange">Phần trăm thuế</a><a
			class="menuSection" href="manageUser">Quản lý người dùng</a><a
			class="menuSection" href="logoutAdmin">Đăng xuất</a>
	</div>
	<div align="center">
		<div id="title">Điền Thông Tin</div>
		<form:form action="saveMonthlyTax" method="post"
			modelAttribute="monthlyTax">
			<table>
				<tr>
					<td>Tháng:</td>
					<td><form:select required="required" class="userInput"
							path="time" style="font-size: 1vw; height: 4vh;">
							<form:option value="" label="-- Chọn tháng --" />
							<form:option value="Tháng 1">Tháng 1</form:option>
							<form:option value="Tháng 2">Tháng 2</form:option>
							<form:option value="Tháng 3">Tháng 3</form:option>
							<form:option value="Tháng 4">Tháng 4</form:option>
							<form:option value="Tháng 5">Tháng 5</form:option>
							<form:option value="Tháng 6">Tháng 6</form:option>
							<form:option value="Tháng 7">Tháng 7</form:option>
							<form:option value="Tháng 8">Tháng 8</form:option>
							<form:option value="Tháng 9">Tháng 9</form:option>
							<form:option value="Tháng 10">Tháng 10</form:option>
							<form:option value="Tháng 11">Tháng 11</form:option>
							<form:option value="Tháng 12">Tháng 12</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Trạng Thái:</td>
					<td><form:radiobutton path="status" value="true" /> Đã Thanh
						Toán <br>
					<form:radiobutton path="status" value="false" /> Chưa Thanh Toán</td>
				</tr>
				<tr>
					<td>Mã Thuế:</td>
					<td><form:input class="userInput" path="taxId" readonly="true" /></td>
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