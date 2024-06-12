<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	width: 50vw;
	border: 0.1vw solid lightgray;
	border-collapse: collapse;
}

th, td {
	padding: 1vh 1vw;
	text-align: left;
	border: 0.2vw solid lightgray;
}

tr:nth-child(even) {
	background-color: #fffff0;
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

a {
	display: inline-block;
	padding: 1vh 1vw; /* Adjust padding using vh and vw units */
	background-color: lightgreen; /* Blue color, you can change it */
	text-decoration: none; /* Remove underline */
	border-radius: 0.5vw; /* Rounded corners */
	color: white;
}

.title {
	margin-bottom: 2vw;
	font-weight: bold;
	font-size: 4vh;
}
</style>
</head>
<body>
	<a href="logout" style="background-color: rgb(239, 57, 56)">Log out</a>
	<a href="pay?taxId=${tax.taxId}">Thanh Toán</a>
	<div>${noti}</div>
	<div align="center">
		<div class="title">Thông Tin Người Dùng</div>
		<table>
			<tr>
				<th>Tax ID</th>
				<td>${tax.taxId}</td>
			</tr>
			<tr>
				<th>Full Name</th>
				<td>${tax.fullName}</td>
			</tr>
			<tr>
				<th>Company Name</th>
				<td>${tax.companyName}</td>
			</tr>
			<tr>
				<th>Email</th>
				<td>${tax.email}</td>
			</tr>
			<tr>
				<th>Address</th>
				<td>${tax.address}</td>
			</tr>
			<tr>
				<th>Phone</th>
				<td>${tax.phone}</td>
			</tr>
			<tr>
				<th>Salary</th>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${tax.salary}" /></td>
			</tr>
			<tr>
				<th>Tax</th>
				<td><fmt:formatNumber type="number" maxFractionDigits="3"
						value="${tax.tax}" /></td>
			</tr>
			<tr>
				<th>User ID</th>
				<td>${tax.userId}</td>
			</tr>
			<tr>
				<td colspan="2" id="linkContainer"><a href="addTax"
					id="createEditInfoLink">Thêm/Sửa thông tin</a></td>
			</tr>
		</table>

		<table style="margin-top: 4vh;">
			<tr>
				<th>No</th>
				<th>Tháng</th>
				<th>Trạng Thái</th>
				<th>Mã Thuế</th>
			</tr>
			<c:forEach items="${monthlyTaxList}" var="monthlyTax"
				varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${monthlyTax.time}</td>
					<td>${monthlyTax.status?"Đã thanh toán":"Chưa thanh toán"}</td>
					<td>${monthlyTax.taxId}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>