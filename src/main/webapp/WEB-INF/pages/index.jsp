<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User data</title>
    </head>
    <body>
        <h1>User Functions</h1>
        <ul>
            <li><a href="${pageContext.request.contextPath}/user/login">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/user/search">Find a user</a></li>
            <li><a href="${pageContext.request.contextPath}/user/list">List all users</a></li>
            <li><a href="${pageContext.request.contextPath}/user/edit/0">Create new user</a></li>
        </ul>
    </body>
</html>
