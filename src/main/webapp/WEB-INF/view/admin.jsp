<%-- 
    Document   : admin
    Created on : 14-Mar-2019, 16:59:47
    Author     : andre
--%>

<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
        <% User user = (User) session.getAttribute("admin"); %>
        <h1 class="text-center text-white" style="margin-top:80px;"><%="Hello "+user.getFirstname()+" !" %></h1>
        <br><br>
        <div class="container">
        <div class="row">
                <div class="col">
                </div>
                <div class="col text-center">
                    <ul class="list-unstyled">
                        <li><button  class="btn btn-outline-secondary btn-lg btn-block my-5 text-white" type="button" onclick="window.location.href = 'viewAllMessages'" >View All Messages</button></li>
                        <li><button  class="btn btn-outline-secondary btn-lg btn-block my-5 text-white" type="button" onclick="window.location.href = 'allUsers'" >View All Users</button></li>
                        <li><button  class="btn btn-outline-secondary btn-lg btn-block my-5 text-white" type="button" onclick="window.location.href = 'viewRankings'">Rankings</button></li>
                        <li><button  class="btn btn-outline-secondary btn-lg btn-block my-5 text-white" type="button" onclick="window.location.href = 'logoutForm'">Logout</button></li>
                    </ul>
                </div>
                <div class="col">
                </div>
        </div>
        </div>  
    </body>
</html>