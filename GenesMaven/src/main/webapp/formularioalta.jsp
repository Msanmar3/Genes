<%-- 
    Document   : inicioAdmin
    Created on : 18-feb-2017, 20:38:31
    Author     : Mónica Sánchez Martín
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Genes Validator</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="app/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="app/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="app/bower_components/Ionicons/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="app/dist/css/AdminLTE.min.css">
        <!-- iCheck -->
        <link rel="stylesheet" href="app/plugins/iCheck/square/blue.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition register-page">
        <div class="register-box">
            <div class="register-logo">
                <a href="index.jsp"><b>Gene</b>Validator</a>
            </div>

            <div class="register-box-body">
                <p class="login-box-msg">Regístrese</p>

                <form  name="formCreateUser" action="servletCreateUserIndex" method="post">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" placeholder="Nombre" name="name" required>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="email" class="form-control" placeholder="Email" name="email" required>
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>

                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" placeholder="Password" name="password" required>
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" placeholder="Repite password" required>
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <c:if test="${!empty(errorCreateUser)}">
                                <c:set var="errorCreateUser" scope="session" value="${errorCreateUser}"/>
                                <div class="alert alert-danger">
                                    <strong>Error:</strong> <c:out value="${errorCreateUser}" />
                                </div>
                            </c:if>
                        </div>
                        <!-- /.col -->
                    </div>

                    <div class="row">
                        <div class="col-xs-6">
                            <button type="reset" class="btn btn-default btn-block btn-flat">Resetear</button>
                        </div>
                        <!-- /.col -->
                        <div class="col-xs-6">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Crear Usuario</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>
                <hr>
                <a href="index.jsp" class="text-center">Ya estoy registrado</a>
            </div>
            <!-- /.form-box -->
        </div>
        <c:import url="script.jsp"></c:import>
    </body>
</html>



