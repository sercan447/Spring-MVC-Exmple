<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title> <tiles:insertAttribute name="title"  /> </title>

<link href="${pageContext.request.contextPath }/kaynaklar/css/dosyam.css" />
<script src="${pageContext.request.contextPath }/kaynaklar/script/jquery.js" type="text/javascript"></script>	



</head>
<body>

<tiles:insertAttribute name="header"></tiles:insertAttribute>

<div class="content">
<tiles:insertAttribute name="content"></tiles:insertAttribute>
</div>




<div class="footer">
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>

</body>
</html>