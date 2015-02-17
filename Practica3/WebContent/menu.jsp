<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<script type="text/javascript">
	$(document).ready(function() {
		$(".menu").click(function(event) {
			$('#content').load('Content', {
				content : $(this).attr('id')
			});
		});
	});
</script>


<%
	HttpSession session = request.getSession(false);
	System.out.println("cargamos menu.jsp");
	System.out.println("        (menu.jsp)Sesion:" + session);
	//System.out.println("Sesion(user):"+session.getAttribute("user"));
	if ((session != null) && (session.getAttribute("user") != null)) {
%>
<table>
	<tr>
		<td><a class="menu" id="logout.jsp" href=#> Tanca conexió </a></td>
	</tr>
</table>
<%
	} else {
%>
<table>
	<tr>
		<td><a class="menu" id="form.jsp" href=#> Registre't </a></td>
		<td><a class="menu" id="login.jsp" href=#> Entra </a></td>
	</tr>
</table>
<%
	}
	;
%>
