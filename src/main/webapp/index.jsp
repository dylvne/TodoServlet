<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Base Index Page" %>
</h1>
<p><% System.out.println("Your IP address is " + request.getRemoteAddr()); %></p>
<p>Today's date: <%= (new Date()).toString()%></p>
<br/>



<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<form action="<%= request.getContextPath() %>/ListServlet" method="post">
    <label for="nitem">Enter New Item:</label><br>
    <input type="text" id="nitem" name="nitem"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
