<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<h1>${sayfaAdi }</h1>

<sec:authorize access="!Authenticated">
	<a href="<c:url value='/login' />">Login</a>
</sec:authorize> <br/>
<!-- 
<c:choose>
	<c:when test="${hasOffer }">
	<table class="offer">
	<tr>
	<td>Name</td>
	<td>Email</td>
	<td>Text</td>
	</tr>
	
	<c:forEach var="it" items="${offers}">
		<tr>
		<td>
		${it.id}
		</td>
		<td>
		<c:out value="${it.user.name}" />
		</td>
		<td>
		<c:out value="${it.text}" />
		</td>
		</tr>
	</c:forEach>
</table>
	
	</c:when>
	
	<c:otherwise>
	<a href="${pageContext.request.contextPath}/createoffer">Create Offer</a>
	</c:otherwise>
</c:choose>
-->

<a href="${pageContext.request.contextPath}/offers">Offers List</a> <br/>



 21.10 =40 - 19
 a = 40 * x / 100


<p> <a href="<c:url value='/admin'/>"> admine git </a> </p> <br/>
<p>  <a href="<c:url value='/newaccount' />">Create Account </a> </p>

<sec:authorize access="Authenticated">
	<a href="<c:url value='/contact' />">Messages (<span id="numberMessages">0</span>)</a>	
</sec:authorize>


<sec:authorize access="Authenticated">
 <c:url var="cikisyap" value='/logout'/>
 
 <form action="${cikisyap}"  method="POST">
 <input type="submit" value="Cikis Yap" />
 <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
 </form>
</sec:authorize>
 
 <div id="messages"> 
 	
 </div>
 

<script src="${pageContext.request.contextPath }/kaynaklar/script/jquery.js" type="text/javascript"></script>	
<link href="${pageContext.request.contextPath }/kaynaklar/css/dosyam.css" type="text/css">
<script type="text/javascript">

	function showReply(i)
	{
		stopTimer();
		$("#form"+i).toggle();
	}
	
	function success(data){
			alert("basarili");
		}
	function error(data){
		alert("hata"+data.message);
		}
	function sendMessage(i,name,email)
	{
		var text = $("#replyarea"+i).val();

			/*$.ajax({
				"type":'POST',
				"url":"<c:url value='/sendmessage' />",
				"data":JSON.stringify({"text":text,"name":name,"email":email}),
				"success":success,
				"error":error,
				contentType:"application/json",
				dataType:"json"	
				});
			*/
			$.ajax({
					method:"POST",
					url:"<c:url value='/sendmessage' />",
					data:{"text":text,"name":name,"email":email},
					//dataType:"json",
					//contentType:"application/json",
					success:function(data)
					{
						alert("bb");
						},
					error:function(jqXHR, textStatus, errorThrown)
					{
						//console.log(jqXHR.responseText);
						alert("kk");
						}	
				});
			
		
		
		}//FUNC

	function showMessages(data)
	{
		$("div#messages").html("");
		for(var i=0;i<data.messages.length;i++)
			{
				var message = data.messages[i];

				var messageDiv = document.createElement("div");
					messageDiv.setAttribute("class","message");

				var subjectSpan = document.createElement("span");
					subjectSpan.setAttribute("class","subject");
					subjectSpan.appendChild(document.createTextNode(message.subject+"<br/>"));

				var contentSpan = document.createElement("span");
					contentSpan.setAttribute("class","messagebody");
					contentSpan.appendChild(document.createTextNode(message.content+"<br/>"));

				var nameSpan = document.createElement("span");
					nameSpan.setAttribute("class","messagebody");
					nameSpan.appendChild(document.createTextNode(message.name+"("+message.email+")"));	

				var replyForm = document.createElement("form");			
					replyForm.setAttribute("class","replyForm");
					replyForm.setAttribute("id","form"+i);

				var textarea = document.createElement("textarea");
					textarea.setAttribute("class","replyarea");
					textarea.setAttribute("id","replyarea"+i);

				var replyButton = document.createElement("input");
					replyButton.setAttribute("class","replybutton");
					replyButton.setAttribute("type","button");
					replyButton.setAttribute("value","Reply");
					replyButton.onclick = function(j,name,email){
							return function(){
								sendMessage(j,name,email);
								}
						}(i,message.name,message.email);

			var link = document.createElement("a");
				link.setAttribute("class","replylink");
				link.setAttribute("href","#");
				link.setAttribute("onclick","showReply("+i+")");
				link.appendChild(document.createTextNode(message.email));
					
				nameSpan.appendChild(link);

					
						replyForm.appendChild(textarea);
						replyForm.appendChild(replyButton);

				
				messageDiv.appendChild(nameSpan);	
				messageDiv.appendChild(contentSpan);		
				messageDiv.appendChild(subjectSpan);
				messageDiv.appendChild(replyForm);		

					
				$("div#messages").append(messageDiv);
			}//for
		}
	
function updateMessageLink(data)
{	
	$("#numberMessages").text(data.number);
}

function onLoad()
{
	updatePage();
	window.setInterval(5000,updatePage);
	}

function startTimer()
{
	timer = window.setInterval(updatePage,5000);
	}
	

function stopTimer()
{
	window.clearInterval(timer);
	}
function updatePage()
{	
	$.getJSON("<c:url value='/getmessages' />",showMessages);
}


$(document).ready(onLoad);

</script>

