<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/menu.css">
</head>
<body>
<div align="center">
	<div>
		<ul>
			<li><a class="active" href="home.do"> π  </a></li>
			<c:if test="${empty id }">
				<li><a href="memberLoginForm.do"> λ‘κ·ΈμΈ </a></li>
			</c:if>
			<c:if test="${not empty id }">
				<li><a href="logout.do"> λ‘κ·Έμμ </a></li>
			</c:if>
			<c:if test="${author eq 'ADMIN' }">
				<li><a href="memberList.do"> λ©€λ²λͺ©λ‘ </a></li>
			</c:if>
			<li><a href="noticeList.do"> κ³΅μ§μ¬ν­ </a></li>
			<li><a href="#"> νμ¬μκ° </a></li>
			<c:if test="${not empty id }">
				<li><a href="#"> μ νμκ° </a></li>
				<li><a href="memberInfo.do"> λ§μ΄νμ΄μ§ </a></li>
			</c:if>
			<c:if test="${empty id }">
				<li><a href="memberJoinForm.do"> νμκ°μ</a></li>
			</c:if>
		</ul>
	</div>
</div>
</body>
</html>