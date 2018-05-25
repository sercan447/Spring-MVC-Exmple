<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/kaynaklar/css/dosyam.css"  rel="stylesheet" />

</head>
<body>



<table class="offer">
	<tr>
	<td>Name</td>
	<td>Email</td>
	<td>Text</td>
	</tr>
	
	<c:forEach var="it" items="${offers}">
		<tr>
		
		<td>
		<c:out value="${it.id}" />
		</td>
		<td>
		<a href="<c:url value='/contact?uid=${it.user.username}' />">${it.user.username}</a>
		</td>
		<td>
		<c:out value="${it.text}" />
		</td>
		</tr>
	</c:forEach>
</table>




	
	




</body>
</html>