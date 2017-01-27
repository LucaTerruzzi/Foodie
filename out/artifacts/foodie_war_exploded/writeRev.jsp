<%--
    Document   : register
    Created on : Jul 5, 2016, 11:08:21 AM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="user" scope="session" class="it.progettoweb.data.User"/>
<!DOCTYPE html>
<html>
<head>
    <title>User Info</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <%@include file="WEB-INF/navbar.jsp" %>
    <form role="form" method="POST" action="StoreReview">
        <div class="form-group">
            <label class="control-label col-sm-2" for="title">Titolo:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="title" placeholder="Titolo" name="title">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="desc">Descrizione:</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="desc" placeholder="Scrivi qui la tua recensione" name="desc" rows="10" cols="50"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Voto:</label>
            <div class="col-sm-10">
                <input class="form-control" type="radio" name="rating" value="1"> 1
                <input class="form-control" type="radio" name="rating" value="2"> 2
                <input class="form-control" type="radio" name="rating" value="3"> 3
                <input class="form-control" type="radio" name="rating" value="4"> 4
                <input class="form-control" type="radio" name="rating" value="5"> 5
            </div>
        </div>
        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

<c:choose>
    <c:when test="${param.error == 1}">Rating not valid</c:when>
    <c:when test="${param.error == 2}">Title not valid</c:when>
    <c:when test="${param.error == 3}">Description not valid</c:when>
    <c:when test="${param.message == 1}">
        Review posted succesfully
        <form action="RetrieveRestaurant" method="post">
            <input type="hidden" name="id" value="${param.id}">
            <button type="submit">OK!</button>
        </form>
    </c:when>
</c:choose>

</body>
</html>