<%--
    Document   : register
    Created on : Jul 5, 2016, 11:08:21 AM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Password change</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="js/zxcvbn.js"></script>
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

            $('#pwd').change(function(){
                var result = zxcvbn($(this).val());
                if(result.score < 2){
                    $(this).removeClass("warning-input").removeClass("valid-input").addClass("invalid-input");
                    $('#pwd-error').show();
                    err = true;
                }else if(result.score < 4){
                    $(this).removeClass("invalid-input").removeClass("valid-input").addClass("warning-input");
                    $('#pwd-error').hide();
                }else{
                    $(this).removeClass("invalid-input").removeClass("warning-input").addClass("valid-input");
                    $('#pwd-error').hide();
                }
                $('#pwd-rep').change();
            });

            $('#pwd-rep').change(function(){
                if($(this).val() == '' || $(this).val() != $('#pwd').val()){
                    $(this).removeClass("valid-input").addClass("invalid-input");
                    $('#pwd-rep-error').show();
                    err = true;
                }else{
                    $(this).removeClass("invalid-input").addClass("valid-input");
                    $('#pwd-rep-error').hide();
                }
            });

            $('#change-form').submit(function(){
                err = false;
                $(this).children().change();
                if(err){
                    $('.invalid-input').first().focus();
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h2>Password change</h2>
    <form role="form" method="POST" action="PwdRecovery" id="change-form">
        <input type="hidden" name="user" value="<c:out value="${param.user}"/>">
        <input type="hidden" name="token" value="<c:out value="${param.token}"/>">
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
        <span id="pwd-error" style="display: none">Password is not strong enough</span>
        <input type="password" class="form-control" id="pwd-rep" placeholder="Repeat password" name="password-rep">
        <span id="pwd-rep-error" style="display: none">Passwords are different</span>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

<c:choose>
    <c:when test="${param.error == 1}">Password not strong enough</c:when>
    <c:when test="${param.error == 2}">Passwords are different</c:when>
    <c:when test="${param.error == 4}">You must accept the terms of service</c:when>
    <c:when test="${param.error == 5}">Email already present</c:when>
</c:choose>

</body>
</html>