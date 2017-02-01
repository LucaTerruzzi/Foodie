<%--
  Created by IntelliJ IDEA.
  User: Luca
  Date: 17/01/2017
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>

<!DOCTYPE html>
<ul class="w3-navbar fd-dark-grey">
    <li><a href="#"><i class="fa fa-home"></i> Foodie</a></li>
    <li class="w3-dropdown-hover w3-quarter w3-right">
        <a href="#"><jsp:getProperty name="user" property="name"/> <jsp:getProperty name="user" property="surname"/></a>
        <div class="w3-dropdown-content w3-card-4" >
            <a href="/user.jsp" class="w3-btn fd-light-grey">Profile</a>
            <form action="Logout" method="POST" style="width: inherit">
                <button class="w3-btn fd-light-grey" type="submit" value="logout">Logout</button>
            </form>
        </div>
    </li>
</ul>

<!--AGGIUNGERE PULSANTE NOTIFICHE!!!-->
<!--AGGIUNGERE PULSANTE RISTORANTE!!!-->