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
    <title>Password recovery</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/fdColours.css">
    <link rel="stylesheet" href="css/fdIndex.css">
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

            $('#email').change(function(){
                var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
                if(!pattern.test($(this).val())){
                    $(this).removeClass("valid-input").addClass("invalid-input");
                    $('#email-error').show();
                    err = true;
                }else{
                    $(this).removeClass("invalid-input").addClass("valid-input");
                    $('#email-error').hide();
                }
            });

            $('#recovery-form').submit(function(){
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
    <%@include file="WEB-INF/navbar.jsp" %>
    <c:choose>
        <c:when test="${param.error == 1}">
            <div class="w3-panel w3-red">Wrong email<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
        </c:when>
    </c:choose>
    <div class="w3-container fd-text-dark-grey">
        <h2>Recupero Password</h2>
        <form role="form" class="w3-container" method="POST" action="PwdRecoverySender" id="recovery-form">
            <input class="w3-input w3-animate-input w3-margin-bottom" type="email" id="email" placeholder="Inserisci la tua email" name="email" style="width:30%">
            <span id="email-error" style="display: none">Invalid email</span>
            <button type="submit" class="fd-dark-grey w3-btn">Submit</button>
        </form>
    </div>
</body>
</html>