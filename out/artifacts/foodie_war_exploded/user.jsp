<%-- 
    Document   : register
    Created on : Jul 5, 2016, 11:08:21 AM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<!DOCTYPE html>
<html>
<head>
    <title>User Info</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <%@include file="WEB-INF/navbar.jsp" %>
    <h3><jsp:getProperty name="user" property="name"/> <jsp:getProperty name="user" property="surname"/></h3>
    <form role="form" method="POST" action="EditInfos/name">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <p class="form-control-static"><jsp:getProperty name="user" property="name"/></p>
                <input type="text" class="form-control" id="name" placeholder="<jsp:getProperty name="user" property="name"/>" name="name">
            </div>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

    <form role="form" method="POST" action="EditInfos/surname">
        <div class="form-group">
            <label class="control-label col-sm-2" for="surname">Surname:</label>
            <div class="col-sm-10">
                <p class="form-control-static"><jsp:getProperty name="user" property="surname"/></p>
                <input type="text" class="form-control" id="surname" placeholder="<jsp:getProperty name="user" property="surname"/>" name="surname">
            </div>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

    <form role="form">
        <div class="form-group">
            <label class="control-label col-sm-2">Email:</label>
            <div class="col-sm-10">
                <p class="form-control-static"><jsp:getProperty name="user" property="email"/></p>
            </div>
        </div>
    </form>

    <form role="form" method="POST" action="EditInfos/pwd">
        <div class="form-group">
            <label class="control-label col-sm-2">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="old_pwd" placeholder="Old Password" name="password-old">
                <input type="password" class="form-control" id="pwd" placeholder="New Password" name="password">
                <input type="password" class="form-control" id="pwd_rep" placeholder="Repeat Password" name="password-rep">
            </div>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

</div>

<c:choose>
    <c:when test="${param.error == 1}">Name not valid</c:when>
    <c:when test="${param.error == 2}">Surname not valid</c:when>
    <c:when test="${param.error == 3}">Check password</c:when>
    <c:when test="${param.message == 1}">Password changed successfully</c:when>
</c:choose>

</body>
</html>