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
        <h2>Find a user</h2>
        <form method="get" action="${pageContext.request.contextPath}/user/search">
            <input name="query" placeholder="Name or email" />
            <input type="submit" title="Search" value="${query}">
        </form>
        
        <jsp:include page="/WEB-INF/pages/common/footer.jsp" />
    </body>
</html>
