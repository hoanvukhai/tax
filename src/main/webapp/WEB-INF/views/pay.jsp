<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang thanh toán</title>
<style>
body {
	font-family: Arial, sans-serif;
}

a {
	display: inline-block;
	padding: 1vh 1vw; /* Adjust padding using vh and vw units */
	background-color: lightgreen; /* Blue color, you can change it */
	text-decoration: none; /* Remove underline */
	border-radius: 0.5vw; /* Rounded corners */
	color: white;
	font-size: 2.5vh;
}

.payment-info {
	background-color: #f9f9f9;
	border: 1px solid #ddd;
	padding: 20px;
	border-radius: 5px;
	width: 25vw;
	margin-top: 10vh;
}
</style>
</head>
<body>
	<a href="javascript:history.back()">Go Back</a>

	<div align="center">
		<div class="payment-info">Thanh toán số tài khoản là: 0123456789
		</div>
		<div class="salary-value" style="margin-top: 1vh;">
			Lương:
			<fmt:formatNumber type="number" maxFractionDigits="3"
				value="${tax.salary}" />
			đ
		</div>
		<div class="tax-value" style="margin-top: 1vh;">
			Thuế:
			<fmt:formatNumber type="number" maxFractionDigits="3"
				value="${tax.tax}" />
			đ
		</div>
		<div class="qr-code" style="margin-top: 1vh;">
			<img src="path/to/your/qr_code_image.png" alt="QR Code">
		</div>
	</div>
</body>
</html>