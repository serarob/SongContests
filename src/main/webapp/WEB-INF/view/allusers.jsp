<%-- 
    Document   : allusers
    Created on : 20-Mar-2019, 11:25:00
    Author     : andre
--%>

<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users Page</title>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        
        <script>
            $(document).ready(function () {
                $('#myTable').DataTable({
                    "lengthMenu": [[5], [5]]
                });
            });
        </script>
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
        <% User admin = (User) session.getAttribute("admin"); %>
        <div class="container-fluid">
        <h1 class="text-center text-white">All users</h1>
        <table border="1" style="border-color: white;" id="myTable" class="table table-hover table-responsive-md">
            <thead class="text-white" style="background-color: #665566;">
                <tr style="border-color: white;">
                    <th style="border-color: white;">Id</th>
                    <th style="border-color: white;">Picture</th>
                    <th style="border-color: white;">Username</th>
                    <th style="border-color: white;">Password</th>
                    <th style="border-color: white;">Firstname</th>
                    <th style="border-color: white;">Lastname</th>
                    <th style="border-color: white;">Email</th>
                    <th style="border-color: white;">Date of Birth</th>
                    <th style="border-color: white;">Role</th>
                    <th style="border-color: white;">Change Role</th>
                    <th style="border-color: white;">Registration Date</th>
                    <th style="border-color: white;">Delete User</th>
                </tr>
            </thead>
            <c:forEach items="${roleUserList}" var="roleuser">

                <tr class="table-dark" style="border-color: white;">
                    <td style="border-color: white;">${roleuser.user.uid}</td>
                    <td style="border-color: white;"><img class="img-circle"
                             src="getUserPhoto/<c:out value='${roleuser.user.uid}'/>" width="60"
                             alt="Pic"></td>
                    <td style="border-color: white;">${roleuser.user.username}</td>
                    <td style="border-color: white;">${roleuser.user.password}</td>
                    <td style="border-color: white;">${roleuser.user.firstname}</td>
                    <td style="border-color: white;">${roleuser.user.lastname}</td>
                    <td style="border-color: white;">${roleuser.user.email}</td>
                    <td style="border-color: white;">${roleuser.user.dob}</td>
                    <td style="border-color: white;">${roleuser.role.name}</td>
                    <td style="border-color: white;"><a href="${pageContext.request.contextPath}/changeRole/${roleuser.user.uid}">Change Role</a></td>
                    <td style="border-color: white;">${roleuser.user.doc}</td>      
                    <td style="border-color: white;"><a href="${pageContext.request.contextPath}/delete/${roleuser.user.uid}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        </div>
        <div style="padding:10px; margin:0 auto;">
                <button  class="btn btn-outline-secondary text-white" type="button" onclick="window.location.href = 'upToAdminPage'" >Back</button>
        </div>
        
    </body>
</html>
