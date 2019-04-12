<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Sign Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/assets/img/apple-icon.png"/>">
        <link rel="icon" type="image/png" href="<c:url value="/assets/img/favicon.png"/>">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>Sign up </title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />

        <!--     Fonts and icons     -->
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.css" rel="stylesheet">

        <!-- CSS Files -->
        <link href="<c:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet" />
        <link href="<c:url value="/assets/css/gsdk-bootstrap-wizard.css"/>" rel="stylesheet" />

        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="<c:url value="assets/css/demo.css"/>" rel="stylesheet" />
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
        
            <form action="registerForm" method="post" enctype="multipart/form-data">
            <div class="image-container set-full-height">
                <!--   Creative Tim Branding   -->
                <a href="#">
                    <div class="logo-container">
                        <div class="logo">
                            <img  src="<c:url value="/pic/vinyl-2241789__340.png"/>" width="70" height="70">
                        </div>
                        <div class="brand">
                            Song Contest
                        </div>
                    </div>
                </a>

                <!--  Made With Get Shit Done Kit  -->


                <!--   Big container   -->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2">

                            <!--      Wizard container        -->
                            <div class="wizard-container">

                                <div class="card wizard-card" data-color="orange" id="wizardProfile">
                                        <!--        You can switch ' data-color="orange" '  with one of the next bright colors: "blue", "green", "orange", "red"          -->

                                        <div class="wizard-header">
                                            <h3>
                                                <b>BUILD</b> YOUR PROFILE <br>
                                                <small>This information will let us know more about you.</small>
                                            </h3>
                                        </div>

                                        <div class="wizard-navigation">
                                            <ul>
                                                <li><a href="#about" data-toggle="tab">About</a></li>
                                                <li><a href="#address" data-toggle="tab">Account</a></li>
                                            </ul>

                                        </div>

                                        <div class="tab-content">
                                            <div class="tab-pane" id="about">
                                                <div class="row">
                                                    <h4 class="info-text" style="margin-left: 125px;">Let's start with the basic information (with validation)</h4>
                                                    <div class="col-sm-4 col-sm-offset-1">
                                                        <div class="picture-container">
                                                            <div class="picture">
                                                                <img src="<c:url value="assets/img/default-avatar.png"/>" class="picture-src" id="wizardPicturePreview" title=""/>
                                                                <input type="file" name="photo" id="wizard-picture">
                                                            </div>
                                                            <h6>Choose Picture</h6>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>First Name <small>(required)</small></label>
                                                            <input name="fname"  type="text" class="form-control" placeholder="Andreas..." >
                                                            <span style="color: red">${requiredValid}</span>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Last Name <small>(required)</small></label>
                                                            <input name="lname" type="text" class="form-control" placeholder="Patsimas..." >
                                                            <span style="color: red">${requiredValid}</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-10 col-sm-offset-1">
                                                        <div class="form-group">
                                                            <label>Email <small>(required)</small></label>
                                                            <input name="email" type="email" class="form-control" placeholder="andreas-patsim@hotmail.com" >
                                                            <span style="color: red">${requiredValid}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="address">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <h4 class="info-text"> Some more information about you </h4>
                                                    </div>
                                                    <div class="col-sm-7 col-sm-offset-1">
                                                        <div class="form-group">
                                                            <label>Username</label>
                                                            <input type="text" name="username" class="form-control" placeholder="username" required>
                                                            <span style="color: red">${sameUsername}</span>
                                                            <span style="color: red">${charactersValid}</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <div class="form-group">
                                                            <label>Password</label>
                                                            <input type="password" name="password1" class="form-control" required>
                                                            <span style="color: red">${characters3Valid}</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-7 col-sm-offset-1">
                                                        <div class="form-group">
                                                            <label>Date of Birth</label>
                                                            <p><input type="date" name="dob"></p>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-3">
                                                        <div class="form-group">
                                                            <label>Confirm Password</label>
                                                            <input type="password" name="password2" class="form-control" required>
                                                            <span style="color: red">${characters3Valid}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wizard-footer height-wizard">
                                            <div class="pull-right">
                                                <input type='submit' class='btn btn-finish btn-fill btn-warning btn-wd btn-sm' name='finish' value='Finish' />

                                            </div>

                                            <div class="pull-left">
                                                <input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm' name='previous' value='Previous' />
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>

                                </div>
                            </div> <!-- wizard container -->
                        </div>
                    </div><!-- end row -->
                </div> <!--  big container -->

                <div class="footer">
                    <div class="container">
                        <button class="btn btn-outline-secondary text-white" onclick="window.location.href = 'loginForm'">Back to home</button> 
                    </div>
                </div>

            </div>
        </form>
        
    </body>
        <!--   Core JS Files   -->
        <script src="<c:url value="/assets/js/jquery-2.2.4.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/assets/js/jquery.bootstrap.wizard.js"/>" type="text/javascript"></script>

    <!--  Plugin for the Wizard -->
    <script src="<c:url value="assets/js/gsdk-bootstrap-wizard.js"/>"></script>

    <script src="<c:url value="assets/js/jquery.validate.min.js"/>"></script>
</html>



