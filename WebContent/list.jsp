<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista usuarios</title>
<link rel="stylesheet" type="text/css" href="css/estil.css" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
</head>
<body>
	<%
		ArrayList<BeanUser> users = new ArrayList<BeanUser>();
		if (request.getAttribute("users") != null) {
			users = (ArrayList<BeanUser>) request.getAttribute("users");
		}
	%>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Username</th>
				<th>Mail</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (BeanUser user : users) {
					out.println("<tr>");
					out.println("<td>" + user.getName() + "</td>");
					out.println("<td>" + user.getSurname() + "</td>");
					out.println("<td>" + user.getUsername() + "</td>");
					out.println("<td>" + user.getMail() + "</td>");
					out.println("<td>" + user.getPwd() + "</td>");
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>