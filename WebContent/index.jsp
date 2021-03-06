<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Twittsire</title>
<!-- CSS -->
<!-- jQuery UI CSS -->
<link rel="stylesheet" type="text/css" href="media/css/jquery-ui.min.css">
<!-- FontAwesome -->
<link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<!-- CSS Framework -->
<link rel="stylesheet" type="text/css" href="media/css/framework.css" />
<!-- Style CSS -->
<link rel="stylesheet" type="text/css" href="media/css/style.css" />
<!-- JS -->
<!-- jQuery -->
<script type="text/javascript" src="media/js/jquery-1.7.1.js"></script>
<!-- jQuery UI -->
<script type="text/javascript" src="media/js/jquery-ui.min.js"></script>
<!-- Validate.js -->
<script type="text/javascript" src="media/js/jquery.validate.js"></script>
<!-- Utils -->
<script type="text/javascript" src="media/js/utils.js"></script>
<script type="text/javascript" src="media/js/form.js"></script>
<!-- Content loader JS -->
<script type="text/javascript" src="media/js/content.js"></script>
<!-- Index JS -->
<script type="text/javascript" src="media/js/index.js"></script>
<!-- Login helper JS -->
<script type="text/javascript" src="media/js/login.js"></script>
<!-- Tweet helper JS -->
<script type="text/javascript" src="media/js/tweet.js"></script>
<!-- Tweet list helper JS -->
<script type="text/javascript" src="media/js/tweet_list.js"></script>
</head>
<body>
	<%
		HttpSession session = request.getSession(false);
	%>
	<div id="index_load" class="loading col-12"></div>
	<div class="row">
		<div class="col-12">
			<!-- Header -->
			<jsp:include page="header.jsp" />
		</div>
		<div class="clear"></div>
		<div class="col-3">
			<!-- Left sidebar -->
			<jsp:include page="left_sidebar.jsp" />
		</div>
		<div class="col-6">
			<!-- Page content -->
			<jsp:include page="content.jsp" />
		</div>
		<div class="col-3">
			<!-- Right sidebar -->
			<jsp:include page="right_sidebar.jsp" />
		</div>
		<div class="clear"></div>
		<div class="col-12">
			<!-- Footer -->
			<jsp:include page="footer.jsp" />
		</div>
	</div>
</body>
</html>
