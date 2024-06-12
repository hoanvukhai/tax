<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
	width: 60vw;
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

.function {
	text-align: center;
	width: 15vw;
}

.btn {
	background-color: lightblue;
	padding: 0.5vh 1.5vw;
	border-radius: 10vw;
	margin: 0 0.5vw;
}

.btn:hover {
	background-color: rgb(90, 176, 205);
}

.clicked {
	background-color: rgb(90, 176, 205);
}
</style>
</head>
<body>
	<div id="menu">
		<a class="menuSection clicked" href="userInfo">Thông tin người dùng</a><a
			class="menuSection" href="manageTaxRange">Phần trăm thuế</a><a
			class="menuSection" href="manageUser">Quản lý người dùng</a><a
			class="menuSection" href="logoutAdmin">Đăng xuất</a>
	</div>

	<div align="center">
		<div id="title">Thông Tin Chi Tiết</div>
		<table border="1">
			<tr>
				<td>Tax ID:</td>
				<td>${tax.taxId}</td>
			</tr>
			<tr>
				<td>Full Name:</td>
				<td>${tax.fullName}</td>
			</tr>
			<tr>
				<td>Company Name:</td>
				<td>${tax.companyName}</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>${tax.email}</td>
			</tr>
			<tr>
				<td>Salary:</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${tax.salary}" /></td>
			</tr>
			<tr>
				<td>Tax:</td>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${tax.tax}" /></td>
			</tr>
			<tr>
				<td>User ID:</td>
				<td>${tax.userId}</td>
			</tr>
		</table>

	</div>

	<div align="center">
		<div id="title">Thuế Hàng Tháng</div>
		
		<table border="1">
			<tr>
				<th>No</th>
				<th>Tháng</th>
				<th>Trạng Thái</th>
				<th>Mã Thuế</th>
				<td class="function"><a class="btn" href="createMonthlyTax?userId=${tax.userId}&taxId=${tax.taxId}">Tạo</a></td>
			</tr>
			<c:forEach items="${listMonthlytTax}" var="monthlyTax"
				varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${monthlyTax.time}</td>
					<td>${monthlyTax.status?"Đã Thanh Toán":"Chưa Thanh Toán"}</td>
					<td>${monthlyTax.taxId}</td>
					<td class="function"><a
						class="btn" href="editMonthlyTax?monthlyTaxId=${monthlyTax.monthlyTaxId}"
						id="registerLink">Sửa</a><a
						class="btn" href="removeMonthlyTax?monthlyTaxId=${monthlyTax.monthlyTaxId}"
						id="registerLink">Xóa</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>