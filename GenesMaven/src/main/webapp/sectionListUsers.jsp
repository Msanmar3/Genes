<%-- 
    Document   : sectionListUsers
    Author     : Mónica Sánchez Martín
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="usersList" scope="session" value="${usersList}"/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Tabla de Usuarios
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-6">
                <a href="servletReturnDash" class="btn btn-success pull-left">
                    Volver
                </a>
            </div>
            <div class="col-xs-6">
                <a href="servletFormCreateUser" class="btn btn-primary pull-right">
                    Crear usuario
                </a>
            </div>

        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">
                        <table id="example2" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Nombre del usuario</th>
                                    <th>Usuario</th>
                                    <th>Email</th>
                                    <th>Rol</th>
                                    <th>Operaciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${fn:length(usersList) gt 0}">
                                    <c:forEach items="${usersList}" var="userL">
                                        <tr>
                                            <td>${userL.getNameUser()}</td>
                                            <td>${userL.getUser()}</td>
                                            <td>${userL.getEmail()}</td>
                                            <td>${userL.getIdRole().getNameRole()}</td>
                                            <td>
                                                <c:url value="servletFormUpdateUser" var="idUserUrlUpdate">
                                                    <c:param name="idUserUrlUpdate" value="${userL.getIdUser()}"/>
                                                </c:url>
                                                <a href="<c:out value="${idUserUrlUpdate}"/>">
                                                    <i class="fa fa-edit"></i>
                                                </a>
                                                <c:url value="servletDeleteUser" var="idUserUrlDelete">
                                                    <c:param name="idUserUrlDelete" value="${userL.getIdUser()}"/>
                                                </c:url>
                                                <a href="<c:out value="${idUserUrlDelete}"/>">
                                                    <i class="fa fa-trash-o"></i>                                      
                                                </a>
                                            </td>
                                        </c:forEach> 

                                    </tr>
                                </c:if>
                            <tfoot>
                                <tr>
                                    <th>Nombre del usuario</th>
                                    <th>Usuario</th>
                                    <th>Email</th>
                                    <th>Rol</th>
                                    <th>Operaciones</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</div>
