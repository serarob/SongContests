<%-- 
    Document   : score
    Created on : 31-Mar-2019, 22:49:03
    Author     : andre
--%>
<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Score Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
    <% User user = (User) session.getAttribute("loguser"); %>
    <h1 class="text-center text-white"><button class="disabled btn btn-outline-secondary btn-lg text-white">Score of the game</button></h1>
        <br>
        <div class="container">
        <div class="row">
            <div class="col">
            </div>
            <div class="col text-center">
        <table border="3" style="border-color: white;" id="myTable" class="table table-hover table-responsive-sm">
            <thead class="text-white" style="background-color: #665566;">
                <tr>
                    <th colspan="2">Score</th>
                </tr>
            </thead>
            <tbody>
                <tr class="table-dark">
                    <td style="border-color: white;" class="align-items-center">${score.userOne.firstname}</td>
                    <td style="border-color: white;" class="align-items-center">${score.pointsOne}</td>
                </tr>
                <tr class="table-dark">
                    <td style="border-color: white;">${score.userTwo.firstname}</td>
                    <td style="border-color: white;">waiting for ${score.userTwo.firstname} to play</td>
                </tr>
            </tbody>
        </table>
                </div>
                <div class="col">
            </div>
        </div>
        </div>
        <br>
        <button class="btn btn-outline-secondary" onclick="window.location.href = 'chooseYourOpponent'">Next</button>
    </body>
</html>
