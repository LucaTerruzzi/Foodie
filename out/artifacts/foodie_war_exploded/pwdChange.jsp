<%--
  Created by IntelliJ IDEA.
  Authors: Luca, Riccardo, Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Password change</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/js/pwdChangeScript.js"></script>
    <script src="js/zxcvbn.js"></script>
</head>
<c:choose>
    <c:when test="${param.error == 1}">
        <div class="w3-panel w3-red">Password not strong enough<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 2}">
        <div class="w3-panel w3-red">Passwords are different<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 4}">
        <div class="w3-panel w3-red">You must accept the terms of service<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 5}">
        <div class="w3-panel w3-red">Email already present<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
</c:choose>
<body>
<div class="w3-container">
    <h2 class="fd-white">Cambio Password</h2>
    <form role="form" class="w3-container" method="POST" action="PwdRecovery" id="change-form">
        <input type="hidden" name="user" value="<c:out value="${param.user}"/>">
        <input type="hidden" name="token" value="<c:out value="${param.token}"/>">
        <input class="w3-input w3-animate-input w3-margin-top" type="password" id="pwd" placeholder="Inserisci la password" name="password" style="width:30%">
        <label class="fd-invalid-label" id="pwd-error" style="display: none">Password is not strong enough</label>
        <input class="w3-input w3-animate-input w3-margin-top" type="password" id="pwd-rep" placeholder="Ripeti la password" name="password-rep" style="width:30%">
        <label class="fd-invalid-label" id="pwd-rep-error" style="display: none">Passwords are different</label><br>
        <button type="submit" class="fd-dark-grey w3-btn w3-margin-top">Fatto</button>
    </form>
</div>
</body>
</html>