<%-- 
    Document   : editprofileuser
    Created on : 27-Mar-2019, 14:16:38
    Author     : andre
--%>

<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit My Profile</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
            crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
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
        <h1 class="text-center text-white">Edit your Profile Menu!</h1>
        <br><br>
        <div class="container col-lg-6" style="background-color: #CCA9CC;">
        <form box-sizing="border-box;" action="editProfile" method="post" enctype="multipart/form-data" class="form-horizontal col-lg-12" role="form">
            <div class="row">            
                <div class="col-md-9 col-lg-8 personal-info" style="margin-top: 10px;">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Username:</label>
                        <div class="col-lg-10">
                          <input class="form-control" type="text" name="username" value="<%=user.getUsername() %>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3" for="password1">Password:</label>
                        <div class="col-lg-5">
                            <input type="password" id="password1" name="password1" placeholder="new password" style="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Confirm Password:</label>
                        <div class="col-lg-5">
                            <input type="password" name="password2" placeholder="confirm new password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Firstname:</label>
                        <div class="col-lg-10">
                            <input class="form-control" type="text" name="firstname" value="<%=user.getFirstname() %>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Lastname:</label>
                        <div class="col-lg-10">
                            <input class="form-control" type="text" name="lastname" value="<%=user.getLastname() %>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Email:</label>
                        <div class="col-lg-10">
                            <input class="form-control" type="email" name="email" value="<%=user.getEmail() %>">
                        </div>
                    </div>    
                    <div class="form-group">
                        <label class="col-lg-6 control-label">Date of Birth:</label>
                        <div class="col-lg-10">
                            <input class="form-control" type="date" name="dob" value="<%=user.getDob() %>">
                        </div>
                    </div>
                    <div class="form-group">
                        <button style="margin-left: 15px" type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
                <div class="col-md-3 col-lg-4" style="margin-top: 10px;">
                    <div class="form-group">
                        <label class="control-label">Edit your Photo</label>
                        <img src="getUserPhoto/<c:out value='${user.uid}'/>" class="img-circle" width="120" alt="avatar">
                            <h6>Upload a photo...</h6>
                            <input type="file" name="photo" size="50" class="col-lg-10" style="color:transparent;">
                    </div>
                </div>
            </div>
        </form>
        </div>
        <br>
<!--        <button data-toggle="modal" data-target="#logout" style="margin-left: 20px;" class="btn btn-secondary">Back</button>
        -->
        <!--Modal-->
        
        <div style="display: none;" class="modal fade" id="logout" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Go back to your profile?</h5>                  
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                   <h6>(Any unsaved changes will be lost)</h6>
                  <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                  <button type="button" class="btn btn-danger"  onclick="window.location.href = 'goToUserProfile'">Go Back</button>
                </div>
              </div>
            </div>
            </div>    
    </body>
</html>
