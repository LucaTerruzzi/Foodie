<%-- 
    Document   : navbar
    Created on : Jun 11, 2016, 8:18:31 PM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
    <c:out value="${sessionScope.userType}"/>
    <c:choose>
        <c:when test="${sessionScope.userType == 0}"><jsp:include page="WEB-INF/anonimousBar.html"/></c:when>
        <c:when test="${sessionScope.userType == 1}"><jsp:include page="WEB-INF/loggedBar.jsp"/></c:when>
        <c:otherwise><h3>something wrong</h3></c:otherwise>
    </c:choose>
</div>