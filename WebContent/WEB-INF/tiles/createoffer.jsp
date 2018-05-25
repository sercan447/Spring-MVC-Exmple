<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Insert title here</title>
		
<link  href="${pageContext.request.contextPath }/kaynaklar/css/dosyam.css" rel="stylesheet" />		
		
</head>
<body>

<h1> <c:out value="${sayfaAdi }" /></h1>

<sf:form  method="POST"  action="${pageContext.request.contextPath }/docreate"  modelAttribute="offer">
	
	
	<table class="formtable">
		
		<tr>
		<td class="label">Your Offer :</td>
			<td>
			<sf:textarea rows="10" cols="40" path="text" name="text"></sf:textarea>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<sf:errors path="text" cssClass="error"></sf:errors>
			</td>
		</tr>
		
			<tr>
			<td> <input type="submit" value="Kaydet" /> </td>
			</tr>
			<c:if test="${offer.id != 0 }">
		
				<tr>
					<td> <input type="submit"  name="delete" value="delete" /> </td>
				</tr>
				<sf:input type="hidden" name="id" path="id"/>
			</c:if>
	</table>
	
</sf:form>


</body>
</html>