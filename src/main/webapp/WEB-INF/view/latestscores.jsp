<%-- 
    Document   : latestscores
    Created on : 05-Apr-2019, 19:20:55
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Latest Scores Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
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
        <h1 class="text-center text-white">Your Latest Scores ${user.firstname}</h1>
        <div class="row">
        <div class="col col-lg-3"></div>
        <div class="col col-lg-6">
            <div class="container">
           
            <table border="1" style="border-color: white;" id="myTable" class="table table-hover table-responsive-lg">
                    <thead class="text-white" style="background-color: #665566;">
                        <tr style="border-color: white;">
                                <th style="border-color: white;">Player1</th>
                                <th style="border-color: white;">Points</th>
                                <th style="border-color: white;">Player2</th>
                                <th style="border-color: white;">Points</th>
                        </tr>
                    </thead>
                        <c:forEach items="${scoresList}" var="score">

                                <tr class="table-dark" style="border-color: white;">
                                        <td style="border-color: white;"><img class="img-circle"
                                                     src="getUserPhoto/<c:out value='${score.userOne.uid}'/>" width="60" 
                                                     alt=" "><h3> ${score.userOne.firstname}</h3></td>
                                        <td style="border-color: white;">${score.pointsOne}</td>
                                        <td style="border-color: white;"><img class="img-circle"
                                                     src="getUserPhoto/<c:out value='${score.userTwo.uid}'/>" width="60" 
                                                     alt=" "><h3> ${score.userTwo.firstname}</h3></td>
                                        <td style="border-color: white;">${score.pointsTwo}</td>    
                                </tr>
                        </c:forEach>
                </table>
                </div>
            </div>
            <div class="col col-lg-3"></div>
            </div>
    </body>
</html>
