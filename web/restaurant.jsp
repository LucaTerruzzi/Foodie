<%--
  Created by IntelliJ IDEA.
  Authors: Luca, Riccardo, Mario
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="restaurant" class="it.progettoweb.data.Restaurant" scope="request" />
<html>
<head>
    <title><jsp:getProperty name="restaurant" property="name"/></title>
    <!--W3-CSS-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
</head>
<body>
<%@include file="WEB-INF/navbar.jsp" %>
<div class="w3-container w3-padding-16 fd-text-dark-grey">
    <div class="w3-row">
        <div class="w3-half w3-container">
            <img class="fd-image fd-dark-grey w3-round" src="pics/<jsp:getProperty name="restaurant" property="id"/>/<jsp:getProperty name="restaurant" property="id"/>_owner_1.jpg" alt="foto"/>
        </div>
        <div class="w3-half w3-container">
            <div class="w3-card-2">
                <header class="w3-container w3-row fd-light-grey" style="max-width: 100%">
                    <div class="w3-container w3-layout-col">
                        <h2><jsp:getProperty name="restaurant" property="name"/></h2>
                    </div>

                    <div class="w3-container w3-layout-col">
                        <h4><c:choose>
                            <c:when test="${restaurant.priceRange == 1}">€</c:when>
                            <c:when test="${restaurant.priceRange == 2}">€€</c:when>
                            <c:when test="${restaurant.priceRange == 3}">€€€</c:when>
                        </c:choose></h4>
                    </div>
                    <div class="w3-container w3-layout-col">
                        <h4><jsp:getProperty name="restaurant" property="rating"/></h4>
                    </div>
                    <div class="w3-container w3-layout-col">
                        <c:if test="${sessionScope.userType != 0 && sessionScope.user.email == restaurant.owner}"><h3>You are the owner!</h3></c:if>
                        <c:if test="${restaurant.owner == null && sessionScope.userType != 0}">
                            <form method="post" action="ClaimRestaurant">
                                <input type="hidden" name="id" value="<jsp:getProperty name="restaurant" property="id"/>">
                                <button class="fd-dark-grey w3-btn" type="submit">CLAIM!</button>
                            </form>
                        </c:if>
                    </div>
                </header>

                <div class="w3-container">
                    <p>Descrizione:<br>
                        <jsp:getProperty name="restaurant" property="description"/>
                    </p>
                    <p>Tipi di cucina:<br>
                        <c:forEach items="${restaurant.cuisine}" var="cuisine">
                        ${cuisine}
                        </c:forEach>
                    </p>
                    <p>Orari:<br>
                        <jsp:getProperty name="restaurant" property="openingHours"/>
                    </p>
                    <p>Indirizzo:<br>
                        <c:out value="${restaurant.location.city}"/>, <c:out value="${restaurant.location.address}"/>
                    </p>
                    <p><a href="<jsp:getProperty name="restaurant" property="link"/>">Link al sito</a></p>
                        
                    <img class="w3-margin-bottom" src="pics/<jsp:getProperty name="restaurant" property="id"/>/<jsp:getProperty name="restaurant" property="id"/>_qr.jpg" alt="qr"/>

                </div>

                <footer class="w3-container fd-light-grey">
                    <c:if test="${canreview == 1}">
                        <p><a href="writeRev.jsp?id=<jsp:getProperty name="restaurant" property="id"/>">Lascia una Recensione</a> </p>
                    </c:if>
                </footer>

            </div>

        </div>
    </div>
    <div class="w3-padding-16">
        <c:forEach items="${restaurant.reviews}" var="review">
            <div class="w3-padding-16">
                <div class="w3-card-2">
                    <header class="w3-container fd-light-grey">
                        <h4>${review.title} - Voto: ${review.rating}</h4>
                    </header>
                    <div class="w3-container fd-text-dark-grey">
                        <p>${review.body}</p>
                    </div>
                    <footer class="w3-container fd-light-grey">
                        <h6>${review.author} il ${review.date.toString()}</h6>
                    </footer>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
