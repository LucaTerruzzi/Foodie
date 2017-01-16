<%-- 
    Document   : index
    Created on : Jun 11, 2016, 4:35:06 PM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" integrity="sha256-xNjb53/rY+WmG+4L6tTl9m6PpqknWZvRt0rO1SRnJzw=" crossorigin="anonymous"></script>
        <script>
            $(function(){
                $("#search").autocomplete({
                    source: "service/autocomplete",
                    minLength: 3
                });
            });
        </script>
        <title>Home</title>
    </head>
    <body>
        <%@include file="WEB-INF/navbar.jsp" %>
        <div class="ui-widget">
            <label for="search">Search: </label>
            <input id="search">
        </div>
        
        <c:choose>
            <c:when test="${param.error == 1}">Wrong username or password</c:when>
        </c:choose>
    </body>
</html>
