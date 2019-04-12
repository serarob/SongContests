<%-- 
    Document   : messages
    Created on : 16-Mar-2019, 19:53:09
    Author     : andre
--%>

<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Friends Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
                        <a class="nav-link" style="font-size: 20px;" href="upToHomePage">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" style="font-size: 20px;" href="goToUserProfile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" style="font-size: 20px;" href="messages">Messages</a>
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
        <div class="container">
            <h1 class="text-center text-white">Prepare for messaging ${user.firstname}!</h1>
            <img src="/SongContests/pic/mail-envelope.jpg" class="col-lg-4 col-lg-offset-2" style="margin-left: 350px; margin-top: 10px;">
        </div>
        <div class="container col-lg-4">
        <form:form action="chatForm" modelAttribute="user"><button class="btn btn-outline-secondary btn-lg btn-block my-5 text-white disabled">
                            My friends:</button><form:select class="form-control form-control-lg" path="uid">
                <c:forEach items="${map}" var="entry">
                    <form:option value="${entry.key.uid}" label="${entry.key.firstname} ${entry.key.lastname}  ${entry.value}"/>
                </c:forEach>


            </form:select>
            <br>
            <button type="submit" class="btn btn-outline-secondary btn-lg btn-block my-5 text-white">Choose your friend to talk</button>
        </form:form>
        </div>        
    </body>
</html>
