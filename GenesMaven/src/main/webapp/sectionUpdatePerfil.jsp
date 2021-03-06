<%-- 
    Document   : sectionUpdatePerfil
    Author     : M�nica S�nchez Mart�n
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="userUpdatePerfil" scope="session" value="${userUpdatePerfil}"/>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Mi Perfil 
        </h1>
    </section>
    <br>
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <a href="servletReturnDash" class="btn btn-success pull-left">
                    Volver
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <!-- Input addon -->
                <div class="box box-info">
                    <div class="box-body">
                        <form role="form" name="formUpdateUser" action="servletUpdateUser" method="post">
                            <input type="hidden" name="idUser" value="${userUpdatePerfil.getIdUser()}"/>
                            <div class="input-group">                
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input type="text" class="form-control" placeholder="Username" name="name" value="${userUpdatePerfil.getNameUser()}">
                            </div>
                            <br>
                            <div class="input-group">                
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="text" class="form-control" placeholder="Email" name="email" value="${userUpdatePerfil.getEmail()}">
                            </div>
                            <br>
                            <div class="input-group col-xs-12">
                                <select name="rol" class="form-control">
                                    <option value=""></option>
                                    <c:if test="${fn:length(rolesList) gt 0}">
                                        <c:forEach items="${rolesList}" var="rolesList">
                                            <c:set var="selected" scope="session" value=""/>
                                            <c:choose>
                                                <c:when test="${rolesList.getIdRole() eq userUpdatePerfil.getIdRole().getIdRole()}">
                                                    <c:set var="selected" scope="session" value="selected"/>
                                                    <option value="${rolesList.getIdRole()}" selected="${selected}">${rolesList.getNameRole()}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${rolesList.getIdRole()}" >${rolesList.getNameRole()}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach> 
                                    </c:if>
                                </select>
                            </div>
                            <br>
                            <c:if test="${!empty(errorUpdatePerfil)}">
                                <c:set var="errorUpdatePerfil" scope="session" value="${errorUpdatePerfil}"/>
                                <div class="alert alert-danger">
                                    <strong>Error:</strong> <c:out value="${errorUpdatePerfil}" />
                                </div>
                            </c:if>
                            <button type="submit" class="btn btn-primary">Actualizar Perfil</button>
                            <button type="reset" class="btn btn-warning">Resetear</button>
                        </form> 
                        <!-- /input-group -->
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
        </div>
    </section>
</div>
