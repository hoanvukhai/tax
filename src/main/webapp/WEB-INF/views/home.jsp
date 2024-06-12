<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tax Management</title>
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

#title {
	margin: 1.5vw 0;
	font-size: 4vh;
}

#inputSalary {
	width: 20vw;
	height: 5vh;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	font-size: 2.5vh;
}

#submitSalary {
	width: 10vw;
	height: 5vh;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	font-size: 2.5vh;
}

a {
	display: inline-block;
	padding: 1vh 1vw; /* Adjust padding using vh and vw units */
	background-color: lightgreen; /* Blue color, you can change it */
	text-decoration: none; /* Remove underline */
	border-radius: 0.5vw; /* Rounded corners */
	color: white;
	font-size: 3.0vh;
}
</style>
</head>
<body>
	<a href="loginPage">Login</a>
	<div align="center">
		<div id="title">Danh Sách Thuế</div>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Lương từ</th>
				<th>Đến</th>
				<th>Phần trăm thuế(%)</th>
			</tr>
			<c:forEach items="${taxRangeList}" var="taxRange" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${taxRange.startRange}" /> đ</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${taxRange.endRange}" /> đ</td>
					<td>${taxRange.taxPercentage}</td>
				</tr>
			</c:forEach>
		</table>

		<div id="title">Tính Thuế</div>


		<form:form action="calculateTax" method="post" modelAttribute="tax">
			<form:input type="number" path="salary" placeholder="Nhập lương"
				required="required" id="inputSalary" />
			<input type="submit" class="btn" value="Lưu	thông tin"
				id="submitSalary" />
		</form:form>
		<div>
			<fmt:formatNumber type="number" maxFractionDigits="3"
				value="${taxMoney}" />
		</div>
		<div>${noti}</div>
	</div>
</body>
</html>