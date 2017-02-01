<%-- 
    Document   : loggedBar
    Created on : Jun 11, 2016, 8:30:56 PM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>

<!DOCTYPE html>
<ul class="w3-navbar fd-dark-grey">
    <li><a href="index.jsp"><i class="fa fa-home"></i> Foodie</a></li>
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