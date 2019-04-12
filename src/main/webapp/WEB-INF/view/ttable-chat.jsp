<%-- 
    Document   : ttable-chat
    Created on : 10-Apr-2019, 19:54:09
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <body>
    
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
        <script>
            /*var i;
            for (i=0; i<3; i++){
                if(document.getElementsByClassName("d-flex justify-content-start mb-4")[i].id === "${logUser.username}"){
                    console.log(i);
                    document.getElementById("${logUser.username}").style.color = "green";
                }
                //console.log(document.getElementsByClassName("d-flex justify-content-start mb-4")[i].id);
            }*/
        </script>
    </body>
</html>
