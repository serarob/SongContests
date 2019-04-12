<%-- 
    Document   : table-chat
    Created on : 28-Mar-2019, 17:14:42
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Table Messages</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <table border="3" class="table" >
                    <thead>
                        <tr>
                                <th>Posted at</th>
                                <th>Text</th>
                                <th>Icon</th>
                                <th>Sender</th>
                                <th>Receiver</th>
                                <th>Edit/Delete</th>
                        </tr>
                    </thead>
                        <c:forEach items="${messageList}" var="message">

                                <tr>
                                        <td>${message.doc}</td>
                                        <td>${message.text}</td>
                                        <td><img class="img-circle"
                                                     src="getMessagePhoto/<c:out value='${message.mid}'/>" width="120" 
                                                     alt=" "></td>
                                        <td>${message.sender.firstname} ${message.sender.lastname}</td>
                                        <td>${message.receiver.firstname} ${message.receiver.lastname}</td>
                                        <td><a href="${pageContext.request.contextPath}/edit/${message.mid}/${receiver.uid}">Edit</a>/<a href="${pageContext.request.contextPath}/delete/${message.mid}/${receiver.uid}">Delete</a></td>
                                </tr>
                        </c:forEach>
                </table>
            </div>
    </body>
</html>
