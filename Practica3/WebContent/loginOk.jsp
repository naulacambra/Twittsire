<%@ page session="true" %>
<%@ page import="java.util.Date"%>
<script type="text/javascript">

$(document).ready(function() {
        $('#navigation').load('menu.jsp');   
});
</script>
	<%@include  file="Login.html" %>
<%
	// la variable session es declarada cuando indicamos que este jsp 
	// va a hacer uso de la session
	session = pageContext.getSession();
	if(session != null) {
		String id = session.getId();
		Date creationTime = new Date(session.getCreationTime());
		Date lastAccesedTime = new Date(session.getLastAccessedTime());
		long maxInactiveInterval = session.getMaxInactiveInterval();
		String userName = (String)session.getAttribute("user");
		
		%>
		Hola de nou <b><%=userName %>!</b>
		<br>
		<hr align="center" width="30%">
		<br>
		<ul>
			<li><b>creation time: </b><%= creationTime %></li>
			<li><b>last accesed: </b><%= lastAccesedTime %></li>
			<li><b>max interval: </b><%= maxInactiveInterval %></li>
		</ul>
		<%
	}
	else {
		%>
		sessió no iniciada: HA OCURRIDO ALGÚN ERROR Y NO TENDRÍAS QUE ESTAR VIENDO ESTO :s
		<%
	}
%>