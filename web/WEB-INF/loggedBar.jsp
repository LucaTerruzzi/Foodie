<%-- 
    Document   : loggedBar
    Created on : Jun 11, 2016, 8:30:56 PM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>

<!DOCTYPE html>
<div>
    <h4>Hello!</h4><jsp:getProperty name="user" property="name"/>
    <form action="Logout" method="POST">
            <input type="submit" value="logout"/>
    </form>
            
</div>
