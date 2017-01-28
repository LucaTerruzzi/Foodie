<%--
  Created by IntelliJ IDEA.
  User: Luca
  Date: 17/01/2017
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div>
    <h4>Hello!</h4><jsp:getProperty name="user" property="name"/>
    <form action="Logout" method="POST">
        <input type="submit" value="logout"/>
    </form>

    <a href="/user.jsp">PROFILO</a>
    <p><c:forEach items="${user.notifications}" end="2" var="notification">
        ${notification.text} - ${notification.type}<br>
        ${notification.restaurantClaimer} claims: <a href="RetrieveRestaurant?id=${notification.restaurantClaimed}">THIS</a>
        <form method="post" action="AssignRestaurant">
            <input type="hidden" name="id" value="${notification.id}">
            <button type="submit" name="dismiss" value="dismiss">Dismiss</button>
            <button type="submit" name="accept" value="accept">Accept</button>
        </form>
    </c:forEach></p>
    <a href="notifications.jsp">ALL</a>
    <a href="RefreshNotifications">REFRESH!</a>
    <!--AGGIUNGERE PULSANTE NOTIFICHE!!!-->
</div>