<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/twittsire" user="consulter" password="qwerty" />

<sql:query dataSource="${snapshot}" var="result">
SELECT * from users;
</sql:query>
<h2>DB login results:</h2>
<table border="1" width="100%">
	<tr>
		<th>NOM</th>
		<th>USUARI</th>
		<th>MAIL</th>
		<th>PASSWORD</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
		<tr>
			<td><c:out value="${row.fullName}" /></td>
			<td><c:out value="${row.username}" /></td>
			<td><c:out value="${row.mail}" /></td>
			<td><c:out value="${row.pwd}" /></td>
		</tr>
	</c:forEach>
</table>