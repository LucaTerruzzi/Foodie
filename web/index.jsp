<%-- 
    Document   : index
    Created on : Jun 11, 2016, 4:35:06 PM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!--W3-CSS-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
    <link rel="stylesheet" href="css/fdIndex.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.5/handlebars.min.js"></script>
    <script src="js/typeahead.bundle.min.js"></script>
    <script src="js/indexScripts.js"></script>

    <title>Home</title>
</head>
<body>
<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
    <%@include file="WEB-INF/navbar.jsp" %>
    <c:choose>
        <c:when test="${param.error == 1}">
            <div class="w3-panel w3-red">Wrong username or password<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.error == 2}">
            <div class="w3-panel w3-red">Invalid link<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.error == 3}">
            <div class="w3-panel w3-red">Something went wrong :(<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.alert == 1}">
            <div class="w3-panel w3-yellow">Input too short<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.message == 1}">
            <div class="w3-panel w3-green">Account confirmed<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.message == 2}">
            <div class="w3-panel w3-green">Registration successful. Check email<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.message == 3}">
            <div class="w3-panel w3-green">Recovery email sent. Check email<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.message == 4}">
            <div class="w3-panel w3-green">Password successfully changed<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.message == 5}">
            <div class="w3-panel w3-green">Restaurant claimed!<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.message == 6}">
            <div class="w3-panel w3-green">Notification dismissed!<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
        <c:when test="${param.message == 7}">
            <div class="w3-panel w3-green">Owner set!<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
    </c:choose>
    <div class="w3-display-middle">
        <div class="container">
            <form role="form" id="search" method="POST" action="Search">
                <div class="w3-row">

                    <div class="w3-layout-cell">
                        <input class="w3-input typeahead" type="text" id="field" size="64" placeholder="Search" name="term">
                        <input type="hidden" id="type" name="type" value="0">
                        <input type="hidden" id="pricefilter" name="pricefilter" value="0"/>
                        <input type="hidden" id="order" name="order" value="2"/>
                    </div>
                    <div class="w3-layout-cell">
                        <button type="submit" class="w3-btn fd-green"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
