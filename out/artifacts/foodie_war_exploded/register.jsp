<%-- 
    Document   : register
    Created on : Jul 5, 2016, 11:08:21 AM
    Author     : Luca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h2>Register</h2>
            <form role="form" method="POST" action="Register">
              <div class="form-group">
                <label for="name">Nome:</label>
                <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
              </div>
              <div class="form-group">
                <label for="surname">Cognome:</label>
                <input type="text" class="form-control" id="surname" placeholder="Enter surname" name="surname">
              </div>
              <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
              </div>
              <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
              </div>
              <div class="form-group">
                <label for="email-rep">Ripeti email:</label>
                <input type="text" class="form-control" id="email-rep" placeholder="Repeat email" name="email-rep">
              </div>
              <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
              </div>
              <div class="form-group">
                <label for="pwd-rep">Ripeti password:</label>
                <input type="password" class="form-control" id="pwd-rep" placeholder="Repeat password" name="password-rep">
              </div>
              <div class="checkbox">
                  <label><input type="checkbox" name="terms" value="yes">Accetto i termini di servizio</label>
              </div>
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
      </div>
    </body>
</html>
