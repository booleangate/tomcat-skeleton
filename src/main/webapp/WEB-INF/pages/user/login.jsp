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
        <h2>Login</h2>
        <form method="post" action="${pageContext.request.contextPath}/user/search">
            <input name="email" value="<%= email %>" placeholder="email" />
            <input type="submit" title="Search">
        </form>
        
        <jsp:include page="/WEB-INF/pages/common/footer.jsp" />
    </body>
</html>
