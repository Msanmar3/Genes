<%-- 
    Document   : inicioAdmin
    Created on : 18-feb-2017, 20:38:31
    Author     : Mónica Sánchez Martín
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%     if ((session.getAttribute("user") == null) || (session.getAttribute("user") == "")) {
    response.sendRedirect("/index.jsp");
}%>
<c:set var="user" scope="session" value="${user}"/>
<!DOCTYPE html>
<html >
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Administrador</title>
        <!-- Bootstrap Styles-->
        <c:import url="librerias.jsp"></c:import>
    
    </head>

    <body>
        <div id="wrapper">

            <c:import url="superior.jsp"></c:import>
            <c:import url="izquierda.jsp"></c:import>

            <c:set var="section" scope="session" value="${section}"/>
            <c:import url="${section}"></c:import>

            <c:import url="footer.jsp"></c:import>

        </div>
    </body>
    <c:import url="script.jsp"></c:import>
</html>