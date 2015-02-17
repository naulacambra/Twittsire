<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Practica3</title>
<link rel="stylesheet" type="text/css" href="css/structure.css" />
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".menu").click(function(event) {
			$('#content').load('Content', {
				content : $(this).attr('id')
			});
		});
	});
</script>
</head>

<body>

	<!-- Begin Wrapper -->
	<div id="wrapper">

		<!-- Begin Header -->
		<div id="header"><h1>Twittsire</h1></div>
		<!-- End Header -->

		<!-- Begin Navigation -->
		<div id="navigation">

			<jsp:include page="menu.jsp" />

		</div>
		<!-- End Navigation -->
	<div class="testeo">texto de testeo</div>
		<!-- Begin Faux Columns -->
		<div id="faux">

			<!-- Begin Left Column -->
			<div id="leftcolumn"></div>
			<!-- End Left Column -->

			<!-- Begin Content Column -->
			<div id="content">
				<%
					HttpSession session = request.getSession(false);
					if ((session != null) && (session.getAttribute("user") != null)) {
				%>
				<jsp:include page="loginOk.jsp" />
				<%
					} else {
				%>
				<jsp:include page="login.jsp" />
				<%
					}
				%>

			</div>
			<!-- End Content Column -->



			<!-- Begin Right Column -->
			<div id="rightcolumn"></div>
			<!-- End Right Column -->

		</div>
		<!-- End Faux Columns -->

		<!-- Begin Footer -->
		<div id="footer">
			<br></br>
			<p>Twittsire rules!</p>
			<br>
				<hr align="center" width="95%">
					<a href=# onclick="$('#results').load('loginresults.jsp')">Mostra
						els resultats de la taula usuaris</a> <br></br> <a href=#
						onclick="$('#results').empty()">Amaga els resultats de la
						taula usuaris</a>
					<div id="results">
						<!--< %@ include file="/loginresults.jsp"%> -->
					</div>
		</div>
		<!-- End Footer -->

	</div>
	<!-- End Wrapper -->
</body>
</html>