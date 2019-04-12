<%-- 
    Document   : concertfeed
    Created on : 09-Apr-2019, 19:09:49
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
    <title>Concert Feed</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
        integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
        integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);
        }
        

        .center {


            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 100%;
            max-width: 300%;
            height: auto;


        }

        #myImg {
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }

        #myImg:hover {
            opacity: 0.7;
        }

        /* The Modal */
        .modal {
            display: none;
            /* Hidden by default */
            position: fixed;
            /* Stay in place */
            z-index: 1;
            /* Sit on top */
            padding-top: 100px;
            /* Location of the box */
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            /* Enable scroll if needed */
            background-color: rgb(0, 0, 0);
            /* Fallback color */
            background-color: rgba(0, 0, 0, 0.9);
            /* Black w/ opacity */
        }

        /* Modal Content (image) */
        .modal-content {
            margin: auto;
            display: block;
            width: 80%;
            max-width: 700px;
        }

        /* Caption of Modal Image */
        #caption {
            margin: auto;
            display: block;
            width: 80%;
            max-width: 700px;
            text-align: center;
            color: #ccc;
            padding: 10px 0;
            height: 150px;
        }

        /* Animation */
        .modal-content,
        #caption {
            -webkit-animation-name: zoom;
            -webkit-animation-duration: 0.6s;
            animation-name: zoom;
            animation-duration: 0.6s;
        }

        @-webkit-keyframes zoom {
            from {
                -webkit-transform: scale(0)
            }

            to {
                -webkit-transform: scale(1)
            }
        }

        @keyframes zoom {
            from {
                transform: scale(0)
            }

            to {
                transform: scale(1)
            }
        }

        /* The Close Button */
        .close {
            position: absolute;
            top: 15px;
            right: 35px;
            color: #f1f1f1;
            font-size: 40px;
            font-weight: bold;
            transition: 0.3s;
        }

        .close:hover,
        .close:focus {
            color: #bbb;
            text-decoration: none;
            cursor: pointer;
        }

        /* 100% Image Width on Smaller Screens */
        @media only screen and (max-width: 700px) {
            .modal-content {
                width: 100%;
            }
        }

        ::-webkit-scrollbar {
            width: 5px;
            height: 3px !important;

        }

        /* Track */
        ::-webkit-scrollbar-track {
            background: #888;
        }

        /* Handle */
        ::-webkit-scrollbar-thumb {
            background: #e51837;
        }

        /* Handle on hover */
        ::-webkit-scrollbar-thumb:hover {
            background: #e51837;
        }

        /* Style the buttons that are used to open and close the accordion panel */
        .accordion {
            background-color: #eee;
            color: #444;
            cursor: pointer;
            padding: 18px;
            width: 100%;
            text-align: left;
            border: none;
            outline: none;
            transition: 0.4s;
        }

        /* Add a background color to the button if it is clicked on (add the .active class with JS), and when you move the mouse over it (hover) */
        .active,
        .accordion:hover {
            background-color: rgb(204, 204, 204);
        }

        /* Style the accordion panel. Note: hidden by default */
        .panel {
            padding: 0 18px;
            background-color: rgb(206, 204, 216);
            display: none;
            overflow: hidden;
        }

        .accordion {
            padding: 15px 25px;
            font-size: 24px;
            text-align: center;
            cursor: pointer;
            outline: none;
            color: #fff;
            background-color: rgb(161, 175, 201);
            border: none;
            border-radius: 15px;
            box-shadow: 0 9px #999;
        }

        .accordion:hover {
            background-color: #817ba7;
        }

        .accordion:active {
            background-color: rgb(161, 175, 201);
            box-shadow: 0 5px rgb(233, 143, 162);
            transform: translateY(4px);
        }

        .btn {
            background-color: #ddd;
            border: none;
            color: black;
            padding: 10px 20px;
            text-align: center;
            font-size: 16px;
            margin: 4px 2px;
            transition: 0.3s;
            border-radius: 16px;


        }

        .btn:hover {
            background-color: #899eca;
            color: white;
        }
    </style>
</head>

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
                <div class="col">
                </div>
                <div class="col text-center">

    <img id="myImg" src="/SongContests/pic/musician-664432__340.jpg" alt="Concerts" class="center">


    <button class="accordion">SongKick</button>
    <div class="panel">
        <br>
        <p>Songkick is totally free. Sign up and track bands via website, or download our iPhone and Android apps for
            concert alerts on the go..
        </p>
        <button class="btn" onclick="window.open('https://www.songkick.com/artists/1325067-feed-me')">Take me to SongKick.com</button>
    </div>



    <button class="accordion">LiveNation</button>
    <div class="panel">
        <br>
        <p>LiveNation.co.uk is growing to become the world's largest concert search engine. Dedicated to helping live
            music fans more easily and quickly find concert tickets and information about their favourite artists and
            venues.</p>
        <button class="btn" onclick="window.open('https://www.livenation.co.uk/event/allevents')">Take me to LiveNation.co.uk</button>
    </div>
    


    <button class="accordion">StubHub</button>
    <div class="panel">
        <br>
        <p>StubHub is the world’s largest ticket marketplace with tickets available for over 10 million live sports,
            music and theatre events in more than 40 countries.</p>
        <button class="btn" onclick="window.open('https://www.stubhub.com/concert-tickets/category/1/?awc=4307_1554815122_799a5c0a9596b986bf95b6fb26d6d09b&gcid=chAFF-_-geoUK-_-genAllTix-_-cmp103504-_-partAFFW&affid=AF-103504')">Take me to StubHub.com</button>
    </div>



    <button class="accordion">LiveList</button>
    <div class="panel">
        <br>
        <p>Guide to live streamed music experiences for free.</p>
        <button class="btn" onclick="window.open('https://www.livelist.com/')">Take me to LiveList.com</button>
    </div>

                </div>
             <div class="col">
                </div>
        </div>
        </div>

    <!-- The Modal -->
    <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="img01">
        <div id="caption"></div>
    </div>


    <script>
        var modal = document.getElementById('myModal');

        // Get the image and insert it inside the modal - use its "alt" text as a caption
        var img = document.getElementById('myImg');
        var modalImg = document.getElementById("img01");
        var captionText = document.getElementById("caption");
        img.onclick = function () {
            modal.style.display = "block";
            modalImg.src = this.src;
            captionText.innerHTML = this.alt;
        }

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks on <span> (x), close the modal
        span.onclick = function () {
            modal.style.display = "none";
        }
    </script>
    <script>
        var acc = document.getElementsByClassName("accordion");
        var i;

        for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener("click", function () {
                /* Toggle between adding and removing the "active" class,
                to highlight the button that controls the panel */
                this.classList.toggle("active");

                /* Toggle between hiding and showing the active panel */
                var panel = this.nextElementSibling;
                if (panel.style.display === "block") {
                    panel.style.display = "none";
                } else {
                    panel.style.display = "block";
                }
            });
        }
    </script>

</body>

</html>