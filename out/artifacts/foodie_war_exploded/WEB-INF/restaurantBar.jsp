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
<div>
    <h4>Hello!</h4><jsp:getProperty name="user" property="name"/>
    <form action="Logout" method="POST">
        <input type="submit" value="logout"/>
    </form>

    <!--AGGIUNGERE PULSANTE PROFILO!!!-->
    <!--AGGIUNGERE PULSANTE NOTIFICHE!!!-->
    <!--AGGIUNGERE PULSANTE RISTORANTE!!!-->
</div>
