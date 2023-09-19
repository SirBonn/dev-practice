<%-- 
    Document   : Administration
    Created on : 15 sept. 2023, 2:53:11
    Author     : sirbon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    <c:if test="${sessionScope.user == null}">
        <meta http-equiv="refresh" content="0;url=${pageContext.request.contextPath}/login.jsp">        
    </c:if>

    <jsp:include page="../common-resources.jsp"/>

    <title>Bank</title>
</head>

<body>
    <jsp:include page="emp-sidebar.jsp"/>
    <jsp:include page="emp-body.jsp"/>
</body>
</html>

