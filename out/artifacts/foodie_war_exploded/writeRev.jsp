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
    <!--W3-CSS-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
        .invalid-input { color : red}
        .warning-input { color : orange}
        .valid-input { color : green}
        .invalid-label {color: red}
        .valid-label {color : green}
        .warning-label {color : orange}
    </style>
    <script>
        $(function () {
            var err = false;

            $('#title').change(function(){
                if($(this).val().length < 3 || $(this).val().length > 63){
                    $(this).removeClass("valid-input").addClass("invalid-input");
                    $('#title-error').show();
                    err = true;
                }else{
                    $(this).removeClass("invalid-input").addClass("valid-input");
                    $('#title-error').hide();
                }
            });

            $('#desc').change(function(){
                if($(this).val().length < 16 || $(this).val().length > 1023){
                    $(this).removeClass("valid-input").addClass("invalid-input");
                    $('#desc-error').show();
                    err = true;
                }else{
                    $(this).removeClass("invalid-input").addClass("valid-input");
                    $('#desc-error').hide();
                }
            });

            $('input:radio[name=rating]').change(function(){
                if(!$('input:radio[name=rating]:checked').length){
                    $(this).parent().removeClass("valid-input").addClass("invalid-input");
                    err = true;
                }else{
                    $(this).parent().removeClass("invalid-input").addClass("valid-input");
                }
            });

            $('#review-form').submit(function(){
                err = false;
                $(this).children().change();
                $('input:radio[name=rating]').change();
                if(err){
                    $('.invalid-input').first().focus();
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<%@include file="WEB-INF/navbar.jsp" %>
<c:choose>
    <c:when test="${param.error == 1}">
        <div class="w3-panel w3-red">Invalid Rating<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 2}">
        <div class="w3-panel w3-red">Invalid Title<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.error == 3}">
        <div class="w3-panel w3-red">Invalid Description<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
    </c:when>
    <c:when test="${param.message == 1}">
        <div class="w3-panel w3-green">Review posted successfully
            <span onclick="this.parentElement.style.display='none'" class="w3-closebtn">
                    <form action="RetrieveRestaurant" method="post">
                        <input type="hidden" name="id" value="${param.id}">
                        <button class="w3-btn w3-green" type="submit">&times;</button>
                    </form>
                </span>
        </div>

    </c:when>
</c:choose>

<div class="w3-container w3-padding-16" style="max-width: 50%">
    <form class="w3-container" role="form" method="POST" action="StoreReview" id="review-form">
        <input class="w3-input w3-margin-bottom fd-text-dark-grey" type="text" id="title" placeholder="Titolo" name="title">
        <span id="title-error" style="display: none">Title must be beween 3 and 63</span>
        <textarea class="w3-input w3-margin-bottom fd-text-dark-grey" rows="10"  id="desc" placeholder="Corpo" name="desc"></textarea>
        <span id="desc-error" style="display: none">Description must be beween 16 and 1023</span>
        <p class="fd-text-dark-grey">Voto:
            1 <input class="w3-radio" type="radio" name="rating" value="1">
            2 <input class="w3-radio" type="radio" name="rating" value="2">
            3 <input class="w3-radio" type="radio" name="rating" value="3">
            4 <input class="w3-radio" type="radio" name="rating" value="4">
            5 <input class="w3-radio" type="radio" name="rating" value="5">
        </p>
        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
        <button type="submit" class="w3-btn fd-light-grey">Submit</button>
    </form>
</div>

</body>
</html>