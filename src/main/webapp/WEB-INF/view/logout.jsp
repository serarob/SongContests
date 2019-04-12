<%-- 
    Document   : logout
    Created on : 15-Mar-2019, 09:23:25
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
        <% session.invalidate();%>
        <div class="container">
        <div class="row">
            <div class="col">
            </div>
            <div class="col text-center">
                <h1 class="text-center text-white">You logged out</h1>
                <h6 class="text-center text-white">We are sorry to see you go</h6>
                <button  class="btn btn-outline-secondary btn-block btn-lg my-5 text-white" type="button" onclick="window.location.href = 'loginForm'" >Go back</button>
            </div>
            <div class="col">
            </div>
        </div>    
        </div>
    </body>
</html>
