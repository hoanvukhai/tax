<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<style>
body {
	font-size: 2.5vh;
	font-family: Arial, Helvetica, sans-serif;
}

table {
	width: 80vw;
	border: 0.1vw solid lightgray;
	border-collapse: collapse;
}

th, td {
	padding: 1vh 1vw;
	text-align: left;
	border: 0.2vw solid lightgray;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

/* Hover effect */
tr:hover {
	background-color: #f1f1f1;
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
</style>
</head>
<body>
	<div id="menu">
		<a class="menuSection clicked" href="userInfo">Thông Tin Người Dùng</a><a
			class="menuSection" href="manageTaxRange">Phần Trăm Thuế</a><a
			class="menuSection" href="manageUser">Quản Lý Tài Khoản</a><a
			class="menuSection" href="logoutAdmin">Đăng Xuất</a>
	</div>

	<div align="center">
		<div id="title">Thông Tin Người Dùng</div>
		<table border="1">
			<tr>
				<th>No</th>
				<th>tax_id</th>
				<th>full_name</th>
				<th>company_name</th>
				<th>email</th>
				<th>salary</th>
				<th>tax</th>
				<th>user_id</th>
			</tr>
			<c:forEach items="${listTax}" var="tax" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${tax.taxId}</td>
					<td>${tax.fullName}</td>
					<td>${tax.companyName}</td>
					<td>${tax.email}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${tax.salary}" /></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${tax.tax}" /></td>
					<td>${tax.userId}</td>
					<td><a id="detailTax" href="seeTaxDetail?userId=${tax.userId}"
						id="registerLink">Chi tiết</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>