<%@ page import="Entities.TodoItemEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: delanechen
  Date: 10/16/22
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>todo</title>
</head>
<body>

<h1>Todo List</h1>
<p>add new items at the bottom</p>
<p>click the x to remove an item...</p><br/>

<form name="removeitem" action="<%= request.getContextPath() %>/TodoServlet" method="post">
    <ol>
        <% List<TodoItemEntity> list = (ArrayList<TodoItemEntity>)request.getAttribute("todolist");

            for (TodoItemEntity item : list) {
                String html = "<li>"+item.getItem()+"<button type='submit' value="+item.getId()+" name='ritem'>x</button>"+"</li>";
                out.print(html);
            }

        %>
    </ol>
</form>



<br/>
<br/>
<br/>

<form name="additem" action="<%= request.getContextPath() %>/ListServlet" method="post">
    <label for="nitem">Enter New Item:</label><br>
    <input type="text" id="nitem" name="nitem"><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
