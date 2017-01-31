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
        <title>Register</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/fdColours.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/valida.2.1.7.min.js"></script>
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

                $('#name').change(function(){
                   if($(this).val().length < 2 || $(this).val().length > 20){
                       $(this).removeClass("valid-input").addClass("invalid-input");
                       $('#name-error').show();
                       err = true;
                   }else{
                       $(this).removeClass("invalid-input").addClass("valid-input");
                       $('#name-error').hide();
                   }
                });

                $('#surname').change(function(){
                    if($(this).val().length < 2 || $(this).val().length > 20){
                        $(this).removeClass("valid-input").addClass("invalid-input");
                        $('#surname-error').show();
                        err = true;
                    }else{
                        $(this).removeClass("invalid-input").addClass("valid-input");
                        $('#surname-error').hide();
                    }
                });

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
                    $('#email-rep').change();
                });

                $('#email-rep').change(function(){
                    if($(this).val() == '' || $(this).val() != $('#email').val()){
                        $(this).removeClass("valid-input").addClass("invalid-input");
                        $('#email-rep-error').show();
                        err = true;
                    }else{
                        $(this).removeClass("invalid-input").addClass("valid-input");
                        $('#email-rep-error').hide();
                    }
                });

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

                $('#terms').change(function(){
                    if(!this.checked){
                        $(this).removeClass("valid-input").addClass("invalid-input");
                        $('label[for="'+ this.id +'"]').removeClass("valid-label").addClass("invalid-label");
                        err = true;
                    }else{
                        $(this).removeClass("invalid-input").addClass("valid-input");
                        $('label[for="'+ this.id +'"]').removeClass("invalid-label").addClass("valid-label");
                    }
                });

                $('#register-form').submit(function(){
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
                <div class="w3-panel w3-red">Generic error<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 2}">
                <div class="w3-panel w3-red">Emails are different<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 3}">
                <div class="w3-panel w3-red">Passwords are dfferent<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 4}">
                <div class="w3-panel w3-red">You must accept the terms of service<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
            <c:when test="${param.error == 5}">
                <div class="w3-panel w3-red">This email has already been registered<span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span></div>
            </c:when>
        </c:choose>
        <div class="w3-container">
            <h2>Register</h2>
            <form class="w3-container" role="form" method="POST" action="Register" id="register-form">
                <!--label class="w3-label" for="name">Nome:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="text" id="name" placeholder="Inserisci il tuo nome" name="name" style="width:30%">
                <span id="name-error" style="display: none">Name must be between 2 and 20</span>
                <!--label class="w3-label" for="surname">Cognome:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="text" id="surname" placeholder="Inserisci il tuo cognome" name="surname" style="width:30%">
                <span id="surname-error" style="display: none">Surname must be between 2 and 20</span>
                <!--label class="w3-label" for="email">Email:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="email" id="email" placeholder="Inserisci un'email" name="email" style="width:30%">
                <span id="email-error" style="display: none">Email must be valid</span>
                <!--label class="w3-label" for="email-rep">Ripeti email:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="email" id="email-rep" placeholder="Ripeti l'email" name="email-rep" style="width:30%">
                <span id="email-rep-error" style="display: none">Emails are different</span>
                <!--label class="w3-label" for="pwd">Password:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="password" id="pwd" placeholder="Inserisci una password" name="password" style="width:30%">
                <span id="pwd-error" style="display: none">Password is not strong enough</span>
                <!--label class="w3-label" for="pwd-rep">Ripeti password:</label-->
                <input class="w3-input w3-animate-input w3-margin-bottom" type="password" id="pwd-rep" placeholder="Ripeti la password" name="password-rep" style="width:30%">
                <span id="pwd-rep-error" style="display: none">Passwords are different</span>

                <label class="fd-text-dark-grey w3-validate" for="terms">Accetto i termini di servizio</label>
                <input class="w3-check w3-margin-bottom" type="checkbox" id="terms" name="terms" value="yes"><br>

                <button type="submit" class="fd-dark-grey w3-btn">Submit</button>
            </form>
        </div>
    </body>
</html>
