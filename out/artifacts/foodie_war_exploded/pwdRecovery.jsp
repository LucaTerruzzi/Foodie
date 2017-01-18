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
    <title>Password recovery</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Password recovery</h2>
    <form role="form" method="POST" action="PwdRecoverySender">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

<c:choose>
    <c:when test="${param.error == 1}">Generic error</c:when>
    <c:when test="${param.error == 2}">Emails are different</c:when>
    <c:when test="${param.error == 3}">Passwords are different</c:when>
    <c:when test="${param.error == 4}">You must accept the terms of service</c:when>
    <c:when test="${param.error == 5}">Email already present</c:when>
</c:choose>

</body>
</html>