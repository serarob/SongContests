<%-- 
    Document   : rankings
    Created on : 06-Apr-2019, 23:34:01
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rankings Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
               
        <h1 class="text-white text-center">Ranking Table</h1>
        <br>
        <br>
        <div class="container">
            <table border="1" style="border-color: white;" id="myTable" class="table table-hover table-responsive-sm">
                    <thead class="text-white" style="background-color: #665566;">
                        <tr style="border-color: white;">
                                <th style="border-color: white; width: 300px;">Player</th>
                                <th style="border-color: white;">W</th>
                                <th style="border-color: white;">D</th>
                                <th style="border-color: white;">L</th>
                                <th style="border-color: white;">Pts</th>
                        </tr>
                    </thead>
                        <c:forEach items="${rankings}" var="result">

                                <tr class="table-dark" style="border-color: white;">
                                    <td style="border-color: white;">
                                        <div class="row">
                                            <div class="col">
                                                <img class="img-rounded"
                                                     src="getUserPhoto/<c:out value='${result.user.uid}'/>" width="60" 
                                                     alt=" "></div>
                                                     <div class="col">
                                                         <h3> ${result.user.firstname} ${result.user.lastname}</h3>
                                                     </div>
                                        </div>
                                    </td>
                                        <td class="text-center" style="font-size: 20px; border-color: white;">${result.win}</td>    
                                        <td class="text-center" style="font-size: 20px; border-color: white;">${result.draw}</td>    
                                        <td class="text-center" style="font-size: 20px; border-color: white;">${result.lose}</td>    
                                        <td class="text-center" style="font-size: 20px; border-color: white;">${result.points}</td>    
                                </tr>
                        </c:forEach>
                </table>
            </div>
        <button onclick="window.location.href='upToAdminPage'" class="btn btn-outline-secondary btn-lg">Back</button>
    </body>
</html>

