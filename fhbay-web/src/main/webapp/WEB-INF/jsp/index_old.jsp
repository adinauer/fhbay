<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<h1>${greeting}</h1>
	<sec:authentication property="principal" var="user"/>
	<p>Your name is ${user}</p>
	
	<sec:authorize ifAnyGranted="ROLE_USER">
		<a href="<c:url value="j_spring_security_logout" />" >Logout</a>
	</sec:authorize>
</body>
</html>