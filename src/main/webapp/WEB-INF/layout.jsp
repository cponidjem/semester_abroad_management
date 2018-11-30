<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>${param.title}</title>
		<meta charset="utf-8" />
		<%--import css and javascript files here --%>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/skeleton.css" />
	</head>
	<body>
		<div class="wrapper">	
			<nav>
				<ul>
				<c:choose>
					<c:when test="${sessionScope.role=='student'}">
						<li><a href="">Universities</a></li>
						<li><a href="">Application</a></li>
						<li><a href="">Grades</a></li>
						<li><a href="">Notifications</a></li>
						<li><a href="${param.path}/sign_out_action">Sign out</a></li>
					</c:when>
					<c:when test="${sessionScope.role=='insa'}">
						<li><a href="">Application</a></li>
						<li><a href="">Grades</a></li>
						
						<li><a href="${param.path}/sign_out_action">Sign out</a></li>
					</c:when>
					<c:when test="${sessionScope.role=='university'}">
						<li><a href="">Student's application</a></li>
						<li><a href="">Grades</a></li>
						<li><a href="">Notifications</a></li>
						<li><a href="${param.path}/sign_out_action">Sign out</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${param.path}/sign_in">Sign in</a></li>
					</c:otherwise>
				</c:choose>				
				</ul>
			</nav>
			
			<h1>${param.title}</h1>
			<jsp:include page="/WEB-INF/${param.content}.jsp"/>
		</div>
	</body>
</html>