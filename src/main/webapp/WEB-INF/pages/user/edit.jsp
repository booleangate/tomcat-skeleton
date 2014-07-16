<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User data</title>
    </head>
    <body>
        <h2>${isNewUser ? "Create a New User" : "Edit User"}</h2>
        
        <c:if test="${f:length(errors) > 0}">
            <h3>Something is derping</h3>
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
        
        <c:choose>
            <c:when test="${isNewUser}"><c:set var="action" value="create" /></c:when>
            <c:otherwise><c:set var="action" value="update/${user.id}" /></c:otherwise>
        </c:choose>
        
        <form method="post" action="${pageContext.request.contextPath}/user/${action}" >
            <!-- Hack for turning off autocomplete in chrome -->
            <input style="display:none"><input type="password" style="display:none">

            <input type="text" name="name" required placeholder="Name" value="<c:out value="${user.name}" />" />
            <input type="email" name="email" required placeholder="Email" value="<c:out value="${user.email}" />" />
            <c:choose>
                <c:when test="${isNewUser}">
	                <input type="password" name="password" placeholder="Password" autocomplete="off" />
	            </c:when>
	            <c:otherwise>
	                <input type="password" name="password" required placeholder="Current password" autocomplete="off" />
	                <input type="password" name="passwordNew" placeholder="New password" autocomplete="off" />
	            </c:otherwise>
            </c:choose>
            
            <input type="submit" title="Create">
        </form>

        <jsp:include page="/WEB-INF/pages/common/footer.jsp" />
    </body>
</html>
