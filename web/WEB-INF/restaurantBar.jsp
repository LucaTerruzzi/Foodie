<%--
  Created by IntelliJ IDEA.
  Authors: Luca, Riccardo, Mario
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<ul class="w3-navbar fd-dark-grey">
    <li><a href="index.jsp"><i class="fa fa-home"></i> Foodie</a></li>
    <li class="w3-dropdown-hover w3-quarter w3-right">
        <a href="#"><jsp:getProperty name="user" property="name"/> <jsp:getProperty name="user" property="surname"/></a>
        <div class="w3-dropdown-content w3-card-4" >
            <a href="/user.jsp" class="w3-btn fd-light-grey">Profile</a>
            <form class="w3-margin-0" action="Logout" method="POST" style="width: inherit">
                <button class="w3-btn fd-light-grey" type="submit" value="logout">Logout</button>
            </form>
        </div>
    </li>

    <li class="w3-dropdown-hover w3-quarter w3-right">
        <a href="#">I miei ristoranti</a>
        <div class="w3-dropdown-content w3-card-4" >
            <c:forEach items="${user.ownedRestaurants}" end="2" var="ownedRestaurant">
                <h5 class="w3-container fd-text-dark-grey">${ownedRestaurant.name}</h5>
                <a class="w3-btn fd-light-grey" href="RetrieveRestaurant?id=${ownedRestaurant.id}">Vai</a>
            </c:forEach>
            <a class="w3-btn fd-light-grey" href="ownedRestaurants.jsp">Vedi Tutte</a>
            <a class="w3-btn fd-light-grey" href="RefreshOwnedRestaurants">Aggiorna</a>
        </div>
    </li>

</ul>
