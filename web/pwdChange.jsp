<%--
    Document   : register
    Created on : Jul 5, 2016, 11:08:21 AM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Password change</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Password change</h2>
    <form role="form" method="POST" action="PwdRecovery">
        <input type="hidden" name="user" value="<c:out value="${param.user}"/>">
        <input type="hidden" name="token" value="<c:out value="${param.token}"/>">
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
        </div>
        <div class="form-group">
            <label for="pwd-rep">Ripeti password:</label>
            <input type="password" class="form-control" id="pwd-rep" placeholder="Repeat password" name="password-rep">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

<c:choose>
    <c:when test="${param.error == 1}">Password not valid</c:when>
    <c:when test="${param.error == 2}">Passwords are different</c:when>
    <c:when test="${param.error == 4}">You must accept the terms of service</c:when>
    <c:when test="${param.error == 5}">Email already present</c:when>
</c:choose>

</body>
</html>