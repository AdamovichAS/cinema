<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.02.2020
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/main/" method="GET">
    <div class="input-group flex-nowrap">
        <div class="input-group-prepend">
            <span class="input-group-text" id="addon-wrapping">@</span>
        </div>
        <input type="text" class="form-control" placeholder="filter" value="${page.searchRequest}" name="search" aria-label="Username"
               aria-describedby="addon-wrapping">
        <input type="submit" name="submit" value="Поиск" />
    </div>
</form>
</body>
</html>
