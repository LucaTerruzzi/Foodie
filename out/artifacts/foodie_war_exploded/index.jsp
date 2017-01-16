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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>        
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.5/handlebars.min.js"></script>
        <script src="js/typeahead.bundle.min.js"></script>
        <script>
            $(function(){
                var cuisine = new Bloodhound({
                    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
                    queryTokenizer: Bloodhound.tokenizers.whitespace,
                    remote: {
                      url: 'service/autocomplete/cuisine/%QUERY',
                      wildcard: '%QUERY'
                    }
                });
                
                var places = new Bloodhound({
                    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
                    queryTokenizer: Bloodhound.tokenizers.whitespace,
                    remote: {
                      url: 'service/autocomplete/places/%QUERY',
                      wildcard: '%QUERY'
                    }
                });
                
                var restaurants = new Bloodhound({
                    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
                    queryTokenizer: Bloodhound.tokenizers.whitespace,
                    remote: {
                      url: 'service/autocomplete/restaurants/%QUERY',
                      wildcard: '%QUERY'
                    }
                });
                
                $("#search .typeahead").typeahead({
                    hint: true,
                    highlight: true,
                    minLength: 3
                },{
                    name: 'couisine',
                    source: cuisine,
                    display : 'value',
                    limit: 100,
                    templates: {
                        header: '<h4 class="header"><span class="glyphicon glyphicon-record"></span>Cuisine</h4>'
                    }
                },{
                    name: 'places',
                    source: places,
                    display : 'value',
                    limit: 100,
                    templates: {
                        header: '<h4 class="header"><span class="glyphicon glyphicon-map-marker"></span>Places</h4>',
                        suggestion: Handlebars.compile('<div>{{value}}, {{spec1}}{{#if spec2}}, {{spec2}}{{/if}}</div>')
                        
                    }
                },{
                    name: 'restaurants',
                    source: restaurants,
                    display : 'value',
                    limit: 100,
                    templates: {
                        header: '<h4 class="header"><span class="glyphicon glyphicon-cutlery"></span>Restaurants</h4>',
                        suggestion: Handlebars.compile('<div>{{value}}, {{spec1}}, {{spec2}}, {{spec3}}</div>')
                        
                    }
                });
                
                $("#search .typeahead").bind('typeahead:select', function(ev, suggestion) {
                    console.log('Selection: ' + suggestion.value);
                });
            });
        </script>
        <style>
            /*.typeahead {
              width: 396px;
              height: 30px;
              padding: 8px 12px;
              font-size: 24px;
              line-height: 30px;
              border: 2px solid #ccc;
              outline: none;
            }*/
                
            .tt-hint {
              color: #999
            }

            .tt-menu {
              width: 422px;
              margin: 12px 0;
              padding: 8px 0;
              background-color: #fff;
              border: 1px solid #ccc;
              border: 1px solid rgba(0, 0, 0, 0.2);
              -webkit-border-radius: 8px;
                 -moz-border-radius: 8px;
                      border-radius: 8px;
              -webkit-box-shadow: 0 5px 10px rgba(0,0,0,.2);
                 -moz-box-shadow: 0 5px 10px rgba(0,0,0,.2);
                      box-shadow: 0 5px 10px rgba(0,0,0,.2);
            }

            .tt-suggestion {
              padding: 3px 20px;
              font-size: 18px;
              line-height: 24px;
            }

            .tt-suggestion:hover {
              cursor: pointer;
              color: #fff;
              background-color: #0097cf;
            }
              
            #search .header {
                border-bottom: 1px solid #ccc;
            }
        </style>
        <title>Home</title>
    </head>
    <body>
        <div class="container">
            <%@include file="WEB-INF/navbar.jsp" %>
            
            <form role="form" id="search">
                <div class="form-group">
                    <input type="text" class="form-control typeahead" placeholder="What do you need">
                </div>
            </form>

            <c:choose>
                <c:when test="${param.error == 1}">Wrong username or password</c:when>
            </c:choose>
            
        </div>
    </body>
</html>
