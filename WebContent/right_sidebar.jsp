<div class="row">
	<div id="right_sidebar" class="container">
		<%
			if (session.getAttribute("user") != null) {
		%>
		<jsp:include page="content/user_panel.jsp" />
		<%
			} else {
		%>
		<jsp:include page="content/login.jsp" />
		<%
			}
		%>
	</div>
</div>
