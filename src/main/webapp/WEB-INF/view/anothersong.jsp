<%-- 
    Document   : anothersong
    Created on : 02-Apr-2019, 10:50:44
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Another Song</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    </head>
    <body>
        
        <audio id="play" autoplay controls>
            <source src="<c:url value="songs/${song.title}.mp3"/>" type="audio/mpeg" />
        </audio>
        <form id="nextSongForm" method="get" action="getAnotherSong" style="margin-top: 15px;">
            <input type="hidden" id="songId" name="songId" value="${song.sid}">
            <input type="submit" class="btn btn-outline-secondary text-white" value="Next Song">
        </form>
            
        <br>
        <h3 class="text-center text-white" style=" margin-top:10px">${song.question}</h3>

        <div class="container margin-top:50px">
            <div class="row" style="margin: auto; height: 290; width: 60%; border: 3px solid green; padding: 10px; margin-top:85px; background-color:blueviolet">
                <div class="row text-center" style="margin-bottom:18px; margin-top:18px;">
                    <div class="card" style="width: 18rem; margin-right: 25px; margin-left: 30px;">
                        <div class="card-body">
                            <h5 class="card-title" style="margin-top:15px">${song.answerone}</h5>
                            <form id="sampleForm" method="get" action="checkAnswer">
                                <input type="hidden" class="songId" name="songId" value="${song.sid}">
                                <input type="hidden" id="songAnswer" name="songAnswer" value="${song.answerone}">
                                <input type="submit" class="btn btn-outline-secondary" value="select">
                            </form>
                        </div>
                    </div>
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h5 class="card-title" style="margin-top:15px">${song.answertwo}</h5>
                            <form id="sampleForm2" method="get" action="checkAnswer">
                                <input type="hidden" class="songId" name="songId" value="${song.sid}">
                                <input type="hidden" id="songAnswer2" name="songAnswer" value="${song.answertwo}">
                                <input type="submit" class="btn btn-outline-secondary" value="select">
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row text-center" style="margin-bottom:18px; margin-top:18px;">
                    <div class="card" style="width: 18rem; margin-right: 25px; margin-left: 30px;">
                        <div class="card-body">
                            <h5 class="card-title" style="margin-top:15px">${song.answerthree}</h5>
                            <form id="sampleForm3" method="get" action="checkAnswer">
                                <input type="hidden" class="songId" name="songId" value="${song.sid}">
                                <input type="hidden" id="songAnswer3" name="songAnswer" value="${song.answerthree}">
                                <input type="submit" class="btn btn-outline-secondary" value="select">
                            </form>
                        </div>
                    </div>
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <h5 class="card-title" style="margin-top:15px">${song.answerfour}</h5>
                            <form id="sampleForm4" method="get" action="checkAnswer">
                                <input type="hidden" class="songId" name="songId" value="${song.sid}">
                                <input type="hidden" id="songAnswer4" name="songAnswer" value="${song.answerfour}">
                                <input type="submit" class="btn btn-outline-secondary" value="select">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var aud = document.getElementById("play");
            aud.autoplay = true;
            aud.load();
            aud.ontimeupdate = function () {
                if (aud.currentTime >= 20) {
                    aud.pause();
                }
            };

            $(document).ready(function () {
                $('#sampleForm').submit(
                        function (event) {
                            var songId = $('.songId').val();
                            var songAnswer = $('#songAnswer').val();
                            console.log(songAnswer);
                            var data = 'songId='
                                    + encodeURIComponent(songId)
                                    + '&amp;songAnswer='
                                    + encodeURIComponent(songAnswer);
                            $.ajax({
                                url: "checkAnswer?songId=" + encodeURIComponent(songId) + "&songAnswer=" + encodeURIComponent(songAnswer),
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
            //2
            $(document).ready(function () {
                $('#sampleForm2').submit(
                        function (event) {
                            var songId = $('.songId').val();
                            var songAnswer = $('#songAnswer2').val();
                            console.log(songAnswer);
                            var data = 'songId='
                                    + encodeURIComponent(songId)
                                    + '&amp;songAnswer='
                                    + encodeURIComponent(songAnswer);
                            $.ajax({
                                url: "checkAnswer?songId=" + encodeURIComponent(songId) + "&songAnswer=" + encodeURIComponent(songAnswer),
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
            //3
            $(document).ready(function () {
                $('#sampleForm3').submit(
                        function (event) {
                            var songId = $('.songId').val();
                            var songAnswer = $('#songAnswer3').val();
                            console.log(songAnswer);
                            var data = 'songId='
                                    + encodeURIComponent(songId)
                                    + '&amp;songAnswer='
                                    + encodeURIComponent(songAnswer);
                            $.ajax({
                                url: "checkAnswer?songId=" + encodeURIComponent(songId) + "&songAnswer=" + encodeURIComponent(songAnswer),
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
            //4
            $(document).ready(function () {
                $('#sampleForm4').submit(
                        function (event) {
                            var songId = $('.songId').val();
                            var songAnswer = $('#songAnswer4').val();
                            console.log(songAnswer);
                            var data = 'songId='
                                    + encodeURIComponent(songId)
                                    + '&amp;songAnswer='
                                    + encodeURIComponent(songAnswer);
                            $.ajax({
                                url: "checkAnswer?songId=" + encodeURIComponent(songId) + "&songAnswer=" + encodeURIComponent(songAnswer),
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
