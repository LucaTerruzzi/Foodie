<%--
  Created by IntelliJ IDEA.
  User: Luca
  Date: 19/01/2017
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Restaurants</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/js/searchResultsScript.js"></script>
</head>
<body class="fd-light-grey">
<%@include file="WEB-INF/navbar.jsp" %>
<div class="w3-container w3-padding-16">

    <form method="post" action="Search">
        <input type="hidden" id="type" name="type" value="<c:out value="${type}"/>">
        <input type="hidden" id="term" name="term" value="<c:out value="${term}"/>">
        Filter by Price
        <select name="pricefilter">
            <option value="1" <c:if test="${pricefilter == 1}">selected</c:if>>€</option>
            <option value="2" <c:if test="${pricefilter == 2}">selected</c:if>>€€</option>
            <option value="3" <c:if test="${pricefilter == 3}">selected</c:if>>€€€</option>
            <option value="0" <c:if test="${pricefilter == 0}">selected</c:if>>All</option>
        </select>
        Order by
        <select name="order">
            <option value="1" <c:if test="${order == 1}">selected</c:if> <c:if test="${type == 0}">disabled</c:if>>Classifica</option>
            <option value="2" <c:if test="${order == 2}">selected</c:if>>Alfabetico</option>
            <option value="3" <c:if test="${order == 3}">selected</c:if>>Prezzo</option>
        </select>
        <button class="w3-btn fd-light-grey"type="submit">Apply</button>
    </form>

    <div><c:forEach items="${results}" var="result">
        <div class="w3-padding-8">
            <div class="w3-card-2 fd-white">
                <header class="w3-container fd-green">
                    <h4>${result.getName()}<c:if test="${type != 0}"> - ${result.getRank()}</c:if></h4>
                </header>
                <div class="w3-container w3-layout-cell">
                    <div class="w3-margin-top">
                        ${result.getCity()}, ${result.getState()}<br>
                        Rating: <fmt:formatNumber value="${result.getRating()}" type="number" maxFractionDigits="1"/><br>
                        N. recensioni: ${result.getNrev()}<br>
                        Prezzo: <c:choose>
                                    <c:when test="${result.getRange() == 1}">€</c:when>
                                    <c:when test="${result.getRange() == 2}">€€</c:when>
                                    <c:when test="${result.getRange() == 3}">€€€</c:when>
                                </c:choose><br>
                        Cucine: <c:forEach items="${result.getCuisine()}" var="cuisine">${cuisine} </c:forEach><br>
                        <button class="w3-btn fd-light-grey w3-section" onclick="loadMap('${result.getName()}, ${result.getCity()}, ${result.getState()}')">Mappa</button>
                        <form method="POST" action="RetrieveRestaurant">
                            <input type="hidden" name="id" value="${result.getId()}"/>
                            <button type="submit" class="w3-btn fd-light-grey">Apri</button>
                        </form>
                    </div>
                </div>
                <div class="w3-container w3-layout-cell w3-layout-middle">
                    <img class="fd-small-image fd-dark-grey w3-round" src="pics/${result.getId()}/${result.getId()}_owner_1.jpg" alt="foto" style="max-width: 100px; max-height: 100px"/>
                </div>
                <!--footer class="w3-container fd-green">
                </footer-->
            </div>
        </div>
    </c:forEach></div>

    <div id="map-modal" class="w3-modal">
        <div class="w3-modal-content">
                <iframe style="width:100%; height:85%;border:0"
                        frameborder="0"
                        src="" allowfullscreen
                        id="map-frame">
                </iframe>
        </div>
    </div>
</div>
</body>
</html>
