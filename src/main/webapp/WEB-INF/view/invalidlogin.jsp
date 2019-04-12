<%-- 
    Document   : invalidlogin
    Created on : 15-Mar-2019, 09:16:33
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
        <title>Invalid Login</title>
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
<!--        <h1><b>Unauthorized Access!!</b></h1>
        <br>-->


        <div class="row">
            <div class="col">
            </div>
            <div class="col">
                <div class="card" style="">
                    <img src="/SongContests/pic/Unauthorised.jpg" class="card-img-top img-fluid">
                    <div class="card-body text-warning">
                        <h5 class="card-title">You failed to login</h5>
                        <p class="card-text">Wrong username and password combination. Please try again.</p>
                        <a class="btn btn-outline-warning" href="loginForm">Go back to login page</a>
                    </div>
                </div>
            </div>
            <div class="col">
            </div>
        </div>            
<!--        <p>Access denied!</p>
        <br><br>
        <p><a class="text-white" href="loginForm">Go back to login page</a></p>
        <br>-->
    </body>
</html>
