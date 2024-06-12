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
	border: 0.2vw solid lightgray;
	border-radius: 1vw;
	background-color: #fffff5; /* Blue color, you can change it */
}

th, td {
	padding: 2vh 2vw;
	text-align: left;
}

#createEditInfoLink {
	text-decoration: none;
}

#linkContainer {
	text-align: center;
}

a {
	display: inline-block;
	padding: 1vh 1vw;
	background-color: #4CBB17;
	color: white;
	text-decoration: none;
	border-radius: 0.5vw;
}

input {
	width: 20vw;
	height: 5vh;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	font-size: 2.5vh;
}

#title {
	font-size: 4vh;
	margin-bottom: 1vw;
}

.btn {
	width: 10vw;
	font-size: 2.5vh;
}
</style>
</head>
<body>
	<a href="logout" style="background-color: rgb(239, 57, 56)">Log out</a>
	<div align="center">
		<div id="title">Điền Thông Tin</div>
		<div style="color: rgb(239, 57, 56);">${noti}</div>
		<form:form action="saveTax" method="post" modelAttribute="tax">
			<table>
				<form:hidden path="userId" />
				<form:hidden path="taxId" />
				<tr>
					<td>Full Name</td>
					<td><form:input path="fullName" required="required" /></td>
				</tr>
				<tr>
					<td>Company Name</td>
					<td><form:input path="companyName" required="required" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email" required="required" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><form:input path="address" required="required" /></td>
				</tr>
				<tr>
					<td>Phone</td>
					<td><form:input path="phone" required="required" /></td>
				</tr>
				<tr>
					<td>Salary</td>
					<td><form:input path="salary" required="required" /></td>
				</tr>
				<tr>
					<td><input type="submit" class="btn" value="Lưu	thông tin" />
					<td><input type="button" class="btn" value="Quay lại"
						onclick="history.back()" />
				</tr>
			</table>
		</form:form>

	</div>
</body>
</html>