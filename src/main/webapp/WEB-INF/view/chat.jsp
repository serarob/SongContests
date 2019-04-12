<%-- 
    Document   : chat
    Created on : 17-Mar-2019, 12:50:43
    Author     : andre
--%>

<%@page import="model.entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chat Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body style="background:linear-gradient(90deg, #feac5e, #c779d0, #4bc0c8);">
        <% User user = (User) session.getAttribute("loguser"); %>
        <h3>Your chat <img class="img-circle"
                             src="getUserPhoto/<c:out value='${sender.uid}'/>" width="60"
                             alt="">  ${sender.firstname} with your friend  <img class="img-circle"
                             src="getUserPhoto/<c:out value='${receiver.uid}'/>" width="60"
                             alt="">  ${receiver.firstname}</h3>
        <br><br>
        <div id="magic">
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
        </div>
        <br><br>
        <form:form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/save" commandName="message">
            <form:hidden path="mid"></form:hidden>
            <label for="file">(Send a Photo)</label>
            <input type="file" name="photo" size="50">
            <form:textarea path="text"></form:textarea>
            <input type="submit" value="Send">
	</form:form>
        <br><br>
        <a href="messages">Back</a>
        
        <script type="text/javascript">
            window.setInterval('wand()',1000);
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
           
            
            setInterval('clearNotifications()',500);
            function clearNotifications(){
                var xhttpr = new XMLHttpRequest();
                xhttpr.open("GET", "${pageContext.request.contextPath}/clearMessagesNotificationsFromSpecificUser", true);
                xhttpr.send();
            }
        </script>
        
    </body>
</html>
