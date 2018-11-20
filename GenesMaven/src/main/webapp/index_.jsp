<%-- 
    Document   : index
    Created on : 16-feb-2017, 11:59:23
    Author     : Mónica Sánchez Martín
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gene Validator</title>
        <meta charset="UTF-8">
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="Portada/css/style.css">
    </head>
    <body>
        <div class="login-form">
            <h1>Gene-Validator</h1>
            <form name="formLogin" id="formLogin" action="servletLogin" method="post" >
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Username" id="user" name="user">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group log-status">
                    <input type="password" class="form-control" placeholder="Password" id="password" name="password">
                    <i class="fa fa-lock"></i>
                </div>
                <c:if test="${sessionScope.errorLogin != null}">
                    <div class="error-server">
                        <p> <c:out value="${errorLogin}" /></p>  
                    </div>
                    <c:remove var="errorLogin" scope="session"/>    
                </c:if>
                <c:url value="servletFormCreateUserIndex" var="creatUser">
                </c:url>
                <a href="<c:out value="${creatUser}"/>">
                    Regístrate                                   
                </a>
                <a class="link" href="#">Has olvidado el password?</a>
                <button type="submit" class="log-btn" name="login" id="login">Entrar</button>
            </form>

        </div>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="lib/jquery-validation/dist/jquery.validate.js" type="text/javascript"></script>
        <script src="Portada/js/index.js"></script>
    </body>
</html>
