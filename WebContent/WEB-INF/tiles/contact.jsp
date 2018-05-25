<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
User : ${fromUser}
<sf:form  method="POST"   modelAttribute="message">
	
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey }" />
	<input type="hidden" name="_eventId" value="send" />

	
	
	<table class="formtable">
	
		<tr>
		<td class="label">Your Name : </td>
		<td>
			<sf:input type="text" path="name" name="name" value="${fromName }"/>
		</td>
		</tr>
		
		<tr>
		<td></td>
		<td>
		<sf:errors path="name" cssClass="error"></sf:errors>
		</td>
		</tr>
		
		<tr>
		<td class="label">Your Email : </td>
		<td>
		<sf:input type="text" path="email" name="email" value="${fromEmail }" />
		</td>
		<td>
		<sf:errors path="email" cssClass="error"></sf:errors>
		</td>
		</tr>
		
			<tr>
		<td class="label"> Subject : </td>
		<td>
		<sf:input type="text" path="subject" name="subject" />
		</td>
		</tr>
		
		<tr>
		<td></td>
		<td>
		<sf:errors path="subject" cssClass="error"></sf:errors>
		</td>
		</tr>
		<tr>
		<td class="label">Your Message: </td>
		<td>
		<sf:textarea type="text" path="content" name="content" />
		</td>
		</tr>
		
		<tr>
		<td></td>
		<td>
		<sf:errors path="content" cssClass="error"></sf:errors>
		</td>
		</tr>
			
			<tr>
			<td> <input id="details" type="submit" value="Create adviced" /> </td>
			</tr>
	</table>
	
</sf:form>

