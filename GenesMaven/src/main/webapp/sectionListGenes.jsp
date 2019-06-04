<%-- 
    Document   : sectionListGenes
    Author     : Mónica Sánchez Martín
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="filesList" scope="session" value="${filesList}"/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Tabla de Ficheros
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
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-body">
                        <table id="example2" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Usuario</th>
                                    <th>Nombre fichero</th>
                                    <th>Fecha</th>
                                    <th>Operaciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${fn:length(filesList) gt 0}">
                                    <c:forEach items="${filesList}" var="fileL">
                                        <tr>
                                            <td>${fileL.getIdUser().getNameUser()}</td>
                                            <td>${fileL.getNameFile()}</td>
                                            <td>${fileLfileL.getCreated()}</td>
                                            <td>
                                                
                                            </td>
                                        </c:forEach> 

                                    </tr>
                                </c:if>
                            <tfoot>
                                <tr>
                                     <th>Usuario</th>
                                    <th>Nombre fichero</th>
                                    <th>Fecha</th>
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
