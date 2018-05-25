<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<script type="text/javascript">

function onLoad()
{
	$("#password").keyup(checkPasswordMatch);
	$("#configpassword").keyup(checkPasswordMatch);

	$("#details").submit(canSubmit);
		
}//onloadFUNC

public function canSubmit()
{	
	var password = $("#password").val();
	var configpas = $("#configpassword").val();

	if(password != configpas)
		{
			return false;
		}else{
			return true;
			}

	}//FUNC
function checkPasswordMatch()
{
	var password = $("#password").val();
	var configpas =	$("#configpassword").val();

	if(password.length > 3 || configpas.length > 3)
	{
		if(password == configpas)
		{
			$("#matchpass").text("<fmt:message   key='MatchedPasswords.user.password'/>");
			$("#matchpass").addClass("dogru");
		}else{
			$("#matchpass").text("<fmt:message  key='UnmatchedPasswords.user.password' />");
			$("#matchpass").addClass("error");
		}
	}

}//FUNC

	$(document).ready(function(){

		onLoad();
		});
</script>



<h1> <c:out value="${sayfaAdi }" /></h1>

<sf:form  method="POST"  action="${pageContext.request.contextPath }/createaccount"  modelAttribute="user">
	
	<table class="formtable">
		<tr>
		<td class="label">username :</td>
		<td>
			<sf:input type="text" path="username" name="username" />
		</td>
		</tr>
		<tr>
		<td></td>
		<td>
		<sf:errors path="username" cssClass="error"></sf:errors>
		</td>
		</tr>
		<tr>
		<td class="label">Name : </td>
		<td>
		<sf:input type="text" path="name" name="name" />
		</td>
		<td>
		<sf:errors path="name" cssClass="error"></sf:errors>
		</td>
		</tr>
		
		<tr>
		<td class="label">Email : </td>
		<td>
		<sf:input type="text" path="email" name="email" />
		</td>
		
		</tr>
		<tr>
		<td></td>
		<td>
		<sf:errors path="email" cssClass="error"></sf:errors>
		</td>
		</tr>
		<tr>
		<td class="label">Password :</td>
		<td>
		<sf:input type="text" id="password" path="password" name="password"></sf:input>
		</td>
		</tr>
		<tr>
		<td></td>
		<td>
		<sf:errors path="password" cssClass="error"></sf:errors>
		</td>
		</tr>
		<tr>
		<td class="label">Confirm Password :</td>
		<td>
		<input type="text" id="configpassword"  name="confirmpassword"></input>
		</td>
		</tr>
		<tr>
		<td>
		<div id="matchpass">ttt</div>
		</td>
		</tr>
		<tr>
		<td></td>
		
		</tr>
			<tr>
			<td> <input id="details" type="submit" value="Create adviced" /> </td>
			</tr>
	</table>
	
</sf:form>

