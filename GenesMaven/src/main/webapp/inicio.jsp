<%-- 
    Document   : inicio
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
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>GeneValidator</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <c:import url="librerias.jsp"></c:import>
        </head>
        <body class="hold-transition skin-blue sidebar-mini">
            <div class="wrapper">
            <c:import url="superior.jsp"></c:import>

            <c:import url="izquierdo.jsp"></c:import>

            <c:set var="section" scope="session" value="${section}"/>
            <c:import url="${section}"></c:import>

            <c:import url="pie.jsp"></c:import>

            <c:import url="derecha.jsp"></c:import>

                <!-- /.control-sidebar -->
                <!-- Add the sidebar's background. This div must be placed
                     immediately after the control sidebar -->
                <div class="control-sidebar-bg"></div>
            </div>
            <!-- ./wrapper -->
        <c:import url="script.jsp"></c:import>
        <c:if test="${not empty funcionesJS}">
            <script type="text/javascript">
                <c:import url="js/${funcionesJS}"></c:import>
            </script>  
        </c:if>
    </body>
</html>
