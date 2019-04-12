<%-- 
    Document   : profileuser
    Created on : 16-Mar-2019, 16:46:39
    Author     : andre
--%>

<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Userprofile Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
              crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
        <% User user = (User) session.getAttribute("loguser");%>

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
                        <a class="nav-link active" style="font-size: 20px;" href="goToUserProfile">Profile</a>
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

        <div class="container">
            <div class="row">
                <div class="col col-lg-3 col-md-3">
                </div>
                <div class="col col-lg-6 col-lg-6">
                    <div class="card border-primary mb-3">
                        <div class="card-header text-center bg-info">
                            <h3 class="card-title text-white">About me</h3>
                            <h5 style=" text-align: right" class="card-title">
                                <button class="btn btn-sm btn-danger" type="button"
                                        data-toggle="modal"
                                        data-target="#deleteMyProfile"
                                        title="Delete My Profile"
                                        data-original-title="profile" ><i class="fa fa-remove"></i></button>
                                <button class="btn btn-sm btn-warning" type="button"
                                        data-toggle="tooltip"
                                        title="Edit My Profile"
                                        data-original-title="Edit My Profile" onclick="window.location.href = 'upToEditProfile'"><i class="fa fa-edit"></i></button>
                            </h5>
                        </div>

                        <div class="card-body">
                            <div class="row">
                                <div class="column col-lg-6">
                                    <div class="col-xs-8 col-sm-6 col-md-3 col-lg-3">
                                        <img class="img-circle"
                                             src="getUserPhoto/<c:out value='${user.uid}'/>" width="120" 
                                             alt="User Pic">
                                    </div>
                                </div>
                                <div class="column col-lg-6">


                                </div>
                            </div>
                            <div class=" col-md-9 col-lg-9 hidden-xs hidden-sm">
                                <strong><%=user.getUsername()%></strong><br>
                                <table class="table table-sm table-user-information">
                                    <tbody>
                                        <tr>
                                            <td>Firstname </td>
                                            <td>${user.firstname} </td>
                                        </tr>
                                        <tr>
                                            <td>Lastname </td>
                                            <td>${user.lastname} </td>
                                        </tr>
                                        <tr>
                                            <td>Email </td>
                                            <td>${user.email} </td>
                                        </tr>

                                        <tr>
                                            <td>Date of Birth </td>
                                            <td>${user.dob} </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">Results</td>
                                            <td>Win:${results.win} Draw:${results.draw}</td>
                                            <td>Lose:${results.lose} Points:${results.points}</td>
                                        </tr>
                                        <tr>
                                            <td><a href="latestScores">My Latest Scores</a></td> 
                                        </tr>
                                        <tr>
                                            <td><a href="viewRankings">Ranking Table</a></td> 
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <div class="card-footer">
                            <button class="btn btn-sm btn-success" type="button"
                                    data-toggle="tooltip"
                                    title="See all users"
                                    data-original-title="users" onclick="window.location.href = 'viewUsers/1'"><i class="fa fa-user"></i></button>
                            <button class="btn btn-sm btn-primary" type="button"
                                    data-toggle="tooltip"
                                    title="Chat with your friends"
                                    data-original-title="Inbox" onclick="window.location.href = 'messages'"><i class="fa fa-envelope"></i><span style="color: red"><b>${notifications}</b></span></button>

                            <span class="pull-right">
                                <button class="btn btn-sm btn-warning" type="button"
                                        data-toggle="modal"
                                        data-target="#download"
                                        title="Download all my messages"
                                        data-original-title="Download all my messages" ><i class="fa fa-cloud-download"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-3 col-md-3">
                </div>        
            </div>
        </div>



        <br>

        <!--Modal-->
        <div class="container">
            <div class="modal fade" id="download" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none;">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"> You are about to download all your messages in txt file!</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                            <button type="button" class="btn btn-warning text-white"  onclick="window.location.href = 'downloadMyMessages'">Download</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Modal1-->
        <div class="container">
            <div style="display: none;" class="modal fade" id="deleteMyProfile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" style="" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Be Careful! Do you really want to delete your Profile?</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                            <button type="button" class="btn btn-danger"  onclick="window.location.href = 'deleteMyProfile'">Delete My Profile</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>



<!--<dd><a href="latestScores">My Latest Scores</a></dd>
<dd><a href="viewRankings">Ranking Table</a></dd>
<tr>
    <td><a href="latestScores">My Latest Scores</a></td> 
</tr>
<tr>
    <td><a href="viewRankings">Ranking Table</a></td> 
</tr>-->