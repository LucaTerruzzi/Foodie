<%--
  Created by IntelliJ IDEA.
  Authors: Luca, Riccardo, Mario
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
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
    <link rel="stylesheet" href="css/fdUser.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<%@include file="WEB-INF/navbar.jsp" %>

<c:choose>
    <c:when test="${param.error == 1}">
        <div class="w3-panel w3-red">Invalid name<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 2}">
        <div class="w3-panel w3-red">Invalid surname<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 3}">
        <div class="w3-panel w3-red">Check password<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.message == 1}">
        <div class="w3-panel w3-green">Password changed successfully<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
</c:choose>

<div class="w3-container">
    <h3 class="fd-text-dark-grey"><jsp:getProperty name="user" property="name"/> <jsp:getProperty name="user" property="surname"/></h3>

    <form class="w3-margin-bottom" role="form">
        <div class="w3-layout-container">
            <div class="w3-container w3-layout-col">
                <label class="w3-white w3-label w3-margin-right">Email: </label>
                <jsp:getProperty name="user" property="email"/>
            </div>
        </div>
    </form>

    <form class="w3-margin-bottom" role="form" method="POST" action="EditInfos/name">
            <div class="w3-container w3-layout-col">
                <label class="w3-white w3-label w3-margin-right" for="name">Nome: </label>
                <jsp:getProperty name="user" property="name"/>
            </div>
            <div class="w3-layout-col" style="width:220px;">
                <input type="text" class="w3-input w3-animate-input" id="name" placeholder="<jsp:getProperty name="user" property="name"/>" name="name" style="width:80%">
            </div>
            <div class="w3-container w3-layout-col">
                <button type="submit" class="fd-light-grey w3-btn">Cambia</button>
            </div>
    </form>

    <form class="w3-margin-bottom" role="form" method="POST" action="EditInfos/surname">
            <div class="w3-container w3-layout-col">
                <label class="w3-white w3-label w3-margin-right" for="surname">Cognome: </label>
                <jsp:getProperty name="user" property="surname"/>
            </div>
            <div class="w3-layout-col" style="width:220px;">
                <input type="text" class="w3-input w3-animate-input" id="surname" placeholder="<jsp:getProperty name="user" property="surname"/>" name="surname" style="width:80%">
            </div>

            <div class="w3-container w3-layout-col">
                <button type="submit" class="fd-light-grey w3-btn">Cambia</button>
            </div>
    </form>

    <form class="w3-margin-bottom" role="form" method="POST" action="EditInfos/pwd">
        <div class="w3-container w3-layout-col">
            <label class="w3-white w3-label" for="old_pwd">Password: </label>
        </div>

        <div class="w3-layout-col" style="width:220px;">
            <input type="password" class="w3-input w3-animate-input" id="old_pwd" placeholder="Vecchia Password" name="password-old" style="width:80%">
        </div>
        <div class="w3-layout-col" style="width:220px;">
            <input type="password" class="w3-input w3-animate-input" id="pwd" placeholder="Nuova Password" name="password" style="width:80%">
        </div>
        <div class="w3-layout-col" style="width:220px;">
            <input type="password" class="w3-input w3-animate-input" id="pwd_rep" placeholder="Ripeti Password" name="password-rep" style="width:80%">
        </div>

        <div class="w3-container w3-layout-col">
                <button type="submit" class="fd-light-grey w3-btn">Cambia</button>
        </div>
    </form>


</div>
</body>
</html>