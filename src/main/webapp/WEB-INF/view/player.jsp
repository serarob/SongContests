<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <!-- Website Design By: www.happyworm.com -->
        <title>audio player</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
        <h1 class="text-center text-white">Let's play ${user.firstname} vs ${opponent.firstname}</h1>
        <br>
        <h2><div style="color: #665566;" class="text-center" id="count"></div></h2>
        <br>
        
        
        <div class="row">
                <div class="col col-lg-1">
                </div>
            <div class="col col-lg-10 text-center">
        <div id="forNextSong">
            <form id="nextSongForm" method="get" action="getAnotherSong">
                <input type="hidden" id="songId" name="songId" value="${song.sid}">
                <input type="submit" onclick="start()" value="Start Game" class="my-5 text-white btn btn-lg btn-outline-secondary">
            </form>
            
            
        </div>
        <br>
        <button type="button" class="text-white btn btn-lg btn-outline-secondary my-5" onclick="window.location.href = 'exitGame'">Exit Game</button>
        </div>
            <div class="col col-lg-1">
                </div>
        </div>
        
        <script>


            /*setInterval(function () {
             window.location = "gameScore";
             }, 10000);*/

            function start() {

                countdownId = setInterval("countdown()", 1000);
                var aud =  document.getElementById("play");
                aud.autoplay=true;
                aud.load();
                aud.ontimeupdate = function () {
                    if (aud.currentTime >= 15) {
                        aud.pause();
                    }
                };

            };

            var clock = 50;
            var countdownId = 0;
            function countdown() {
                if (clock > 0) {
                    clock = clock - 1;
                } else {
                    clock = 0;
                    window.location = "gameScore";
                }
                document.getElementById('count').innerHTML = "seconds left: " + clock;
            }

            /*function help() {
             alert("Thank you for visiting W3Schools!");
             }*/
            /*function closeIt()
             {
             window.location = "gameScore";
             }
             if(clock > 0){
             window.onbeforeunload = closeIt; 
             }*/

            $(document).ready(function () {
                $('#nextSongForm').submit(
                        function (event) {
                            var songId = $('#songId').val();
                            var data = 'songId='
                                    + encodeURIComponent(songId);
                            $.ajax({
                                url: "getAnotherSong?songId=" + encodeURIComponent(songId),
                                data: data,
                                type: "GET",

                                success: function (response) {
                                    $("#forNextSong").html(response);
                                },
                                error: function (xhr, status, error) {
                                    console.log(xhr.responseText);
                                }
                            });
                            return false;
                        });
            });

        </script>
    </body>

</html>
