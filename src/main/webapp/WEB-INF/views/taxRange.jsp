<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

.clicked {
	background-color: rgb(90, 176, 205);
}

.btn {
	background-color: lightblue;
	padding: 0.5vh 1.5vw;
	border-radius: 10vw;
	margin: 0 0.5vw;
	color: white;
}

.btn:hover {
	background-color: rgb(90, 176, 205);
}

.function {
	text-align: center;
	width: 13vw;
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
		<div id="title">Danh Sách Thuế</div>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Lương từ (đ)</th>
				<th>Đến (đ)</th>
				<th>Phần trăm thuế (%)</th>
				<td class="function"><a class="btn" href="createTaxRange">Tạo</a></td>
			</tr>
			<c:forEach items="${listTaxRange}" var="taxRange" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${taxRange.startRange}" /></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${taxRange.endRange}" /></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${taxRange.taxPercentage}" /></td>
					<td class="function"><a class="btn"
						href="editTaxRange?id=${taxRange.id}">Sửa</a> <a class="btn"
						href="removeTaxRange?id=${taxRange.id}">Xóa</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>