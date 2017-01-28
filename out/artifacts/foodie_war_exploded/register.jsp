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
        <title>Register</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/fdColours.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    </head>
    <body>
        <%@include file="WEB-INF/navbar.jsp" %>
        <c:choose>
            <c:when test="${param.error == 1}">
                <div class="w3-panel w3-red">Generic error<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 2}">
                <div class="w3-panel w3-red">Emails are different<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 3}">
                <div class="w3-panel w3-red">Passwords are dfferent<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 4}">
                <div class="w3-panel w3-red">You must accept the terms of service<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 5}">
                <div class="w3-panel w3-red">This email has already been registered<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
        </c:choose>
        <div class="w3-container">
            <h2>Register</h2>
            <form class="w3-container" role="form" method="POST" action="Register">
                <!--label class="w3-label" for="name">Nome:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="text" id="name" placeholder="Inserisci il tuo nome" name="name" style="width:30%">

                <!--label class="w3-label" for="surname">Cognome:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="text" id="surname" placeholder="Inserisci il tuo cognome" name="surname" style="width:30%">

                <!--label class="w3-label" for="email">Email:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="email" id="email" placeholder="Inserisci un'email" name="email" style="width:30%">

                <!--label class="w3-label" for="email-rep">Ripeti email:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="email" id="email-rep" placeholder="Ripeti l'email" name="email-rep" style="width:30%">

                <!--label class="w3-label" for="pwd">Password:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="password" id="pwd" placeholder="Inserisci una password" name="password" style="width:30%">

                <!--label class="w3-label" for="pwd-rep">Ripeti password:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="password" id="pwd-rep" placeholder="Ripeti la password" name="password-rep" style="width:30%">

                <label class="fd-text-dark-grey w3-validate">Accetto i termini di servizio</label>
                <input class="w3-check w3-margin-bottom" type="checkbox" name="terms" value="yes"><br>

                <button type="submit" class="fd-dark-grey w3-btn">Submit</button>
            </form>
        </div>
    </body>
</html>
