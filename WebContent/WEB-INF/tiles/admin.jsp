<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<h1>Admin Sayfası</h1>


<table>
	<tr>
	<td>Username</td><td>Email</td><td>Role</td><td>Enabled</td>
	</tr>
	<c:forEach var="kes" items="${users}">
	<tr>
	<td><c:out value="${kes.username }" /></td>
	<td><c:out value="${kes.email }" /></td>
	<td><c:out value="${kes.authority }" /></td>
	<td><c:out value="${kes.enabled }" /></td>
	</tr>
	</c:forEach>
	
</table>
