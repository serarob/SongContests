<%-- 
    Document   : cchat
    Created on : 09-Apr-2019, 19:40:06
    Author     : andre
--%>

<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
    <head>
        <title>Chat</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="/styles/mystyle.css"/>"
              <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <!--Coded With Love By Mutiullah Samim-->
    <body>
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
        <div class="container-fluid h-100">
            <div class="row justify-content-center h-100">

                <div class="col-md-8 col-xl-6 chat" >
                    <div class="card">
                        <div class="card-header msg_head">
                            <div class="d-flex bd-highlight">
                                <div class="img_cont">
                                    <img src="getUserPhoto/<c:out value='${receiver.uid}'/>" class="rounded-circle user_img">
                                    <span class="online_icon"></span>
                                </div>
                                <div class="user_info">
                                    <span>Chat with  ${receiver.firstname} ${receiver.lastname}</span>
                                    
                                </div>
                  
                            </div>
                            
                        </div>
                        <div class="card-body msg_card_body"  id="magic">
                            <c:forEach items="${map}" var = "entry">
                                ${entry.value}
                                        ${entry.key.text}
                                        
                                        <button class="btn btn-sm btn-danger" type="button" style="font-size:8px; border-radius:80%"
                                            data-toggle="tooltip"
                                            title="Delete this message"
                                            data-original-title="Delete this message" onclick="window.location.href = '${pageContext.request.contextPath}/delete/${entry.key.mid}/${receiver.uid}'"><i class="fa fa-trash"></i></button>
                                        <button class="btn btn-sm btn-warning" type="button" style="font-size:8px; border-radius:80%"
                                                data-toggle="tooltip"
                                                title="Edit Message"
                                                data-original-title="Edit Message" onclick="window.location.href = '${pageContext.request.contextPath}/edit/${entry.key.mid}/${receiver.uid}'"><i class="fa fa-pencil-alt"></i></button>
                                        
                                        <span class="msg_time">${entry.key.doc}</span>
                                    </div>
                                    <div class="img_cont_msg">
                                        <img src="getUserPhoto/<c:out value='${entry.key.sender.uid}'/>" class="rounded-circle user_img_msg">
                                    </div>
                                    
                                </div>
                            </c:forEach>

                        </div>
                        <div class="card-footer">
                            <form:form method="post" action="${pageContext.request.contextPath}/save" commandName="message">
                                <div class="input-group">



                                    <form:hidden path="mid"></form:hidden>

                                    <form:textarea path="text" cssClass="form-control type_msg" placeholder="Type your message..." maxlength="250"></form:textarea>

                                        <div class="input-group-append">
                                            <span class="input-group-text send_btn"><button type="submit"><i class="fas fa-location-arrow"></i></button></span>
                                        </div>

                                    </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            /*var i;
             for (i=0; i<3; i++){
             if(document.getElementsByClassName("d-flex justify-content-start mb-4")[i].id === "${logUser.username}"){
             console.log(i);
             document.getElementById("${logUser.username}").style.color = "green";
             }
             //console.log(document.getElementsByClassName("d-flex justify-content-start mb-4")[i].id);
             }*/
            //$(document).ready(function () {
            
                
            //});

             window.setInterval('wand()', 1000);
             function wand() {
             var xhttp = new XMLHttpRequest();
             xhttp.onreadystatechange = function () {
             if (this.readyState === 4 && this.status === 200) {
             document.getElementById("magic").innerHTML = this.responseText;
             }
             };
             xhttp.open("GET", "${pageContext.request.contextPath}/upToTable", true);
             xhttp.send();
             }


            setInterval('clearNotifications()', 500);
            function clearNotifications() {
                var xhttpr = new XMLHttpRequest();
                xhttpr.open("GET", "${pageContext.request.contextPath}/clearMessagesNotificationsFromSpecificUser", true);
                xhttpr.send();
            }
        </script>
    </body>
</html>
