<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
         
        
        <% User user = (User) session.getAttribute("loguser"); %>
        
        <nav class="navbar navbar-expand-sm navbar-dark" style="background-color: #665566;">
            <a href="#">
                    <div class="logo-container">
                        <div class="logo">
                            <img  src="<c:url value="/pic/vinyl-2241789__340.png"/>" width="70" height="70">
                        </div>
                        <div class="brand">
                            
                        </div>
                    </div>
                </a>
            <button class="navbar-toggler mr-2" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <span class="navbar-brand d-flex"></span>
            <div class="navbar-collapse collapse" id="navbar">
                <ul class="navbar-nav justify-content-left d-flex flex-fill">
                    <li class="nav-item">
                        <a class="nav-link active" style="font-size: 20px;" href="upToHomePage">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="font-size: 20px;" href="goToUserProfile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="font-size: 20px;" href="messages">Messages</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="font-size: 20px;" href="chooseYourOpponent">MusicBattle</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="font-size: 20px;" href="logoutForm">Logout</a>
                    </li>
                </ul>
                <ul class="navbar-nav justify-content-right d-flex">
                    
                </ul>
            </div>
            <div class="d-flex flex-fill"><!--spacer--> </div>
        </nav>
        <h1 class="text-center text-white" style="margin-top:80px;"><%="Welcome "+user.getFirstname()+" !" %></h1>
        <br><br>
        
        <div class="container">
        <div class="row">
                <div class="col">
                </div>
                <div class="col text-center">
                    <ul class="list-unstyled">
                        <li> <button type="button" class="btn btn-outline-secondary btn-lg btn-block my-5 text-white" onclick="window.location.href = 'chooseYourOpponent'" > Ready to battle? </button></li>
                        <li> <button id="menu1" type="button" class="btn btn-outline-secondary btn-lg btn-block my-5 text-white"  onclick="window.location.href = 'goToUserProfile'"> Go to profile </button> </li>
                        <li><button type="button" class="btn btn-outline-secondary btn-lg btn-block my-5 text-white" onclick="window.location.href='upToConcertFeed'"> Concerts feed</button></li>
                    </ul>
                </div>
                <div class="col">
                </div>
        </div>
        </div>
        
        
            <!--
            <div>x`
            <p class="ml-3 mt-3 text-white"> Logo </p> 
            <button type="button" class="btn btn-outline-secondary btn-lg btn-block my-5 text-white" type="button" onclick=window.open("game-chat.jsp")> Ready to battle? </button>
            <button type="button" class="btn btn-outline-secondary btn-lg btn-block my-5 text-white"> Go to profile </button>
            <button type="button" class="btn btn-outline-secondary btn-lg btn-block text-white"> Concerts feed </button>
             <footer 
                <ul class="page-footer list-unstyled list-inline text-center text-white font-small mt-5" >
                    <li class="footer-help list-inline-item mr-5"> Need help? </li>               
                    <li class="footer-download list-inline-item"> Download the app </li>              
            </footer>    
            <footer
                class="footer-copyright font-small text-center text-white mt-5" > © Copyright 2019               
            </footer>
        </div>-->
    </body>
</html>
