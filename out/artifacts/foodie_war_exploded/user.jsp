<%-- 
    Document   : user
    Created on : Jun 14, 2016, 10:38:49 PM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
    </head>
    <body>
        <%@include file="WEB-INF/navbar.jsp" %>
        <h3><jsp:getProperty name="user" property="username"/></h3>
        <p>
            Name: <jsp:getProperty name="user" property="name"/><br/>
            Surname: <jsp:getProperty name="user" property="surname"/><br/>
            Email: <jsp:getProperty name="user" property="email"/><br/>

        </p>
    </body>
</html>
