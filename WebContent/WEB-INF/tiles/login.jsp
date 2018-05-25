<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<h3>Sistem giriş ekranı</h3>



<form name='f' action='/Z-Proje-01/login' method='POST'>
<table>
	<tr><td>Ad :</td><td><input type='text' name='username' value=''></td></tr>
	<tr><td>Parola:</td><td><input type='password' name='password'/></td></tr>
	<tr><td>Hatırla Beni :</td><td colspan='2'><input name="hatirlabeni" type="checkbox" /></td></tr>
	<tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
</table>
</form>

<p> <a href="<c:url value='/newaccount' />">Create new Account </a> </p>

