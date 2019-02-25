<%-- 
    Document   : sectionCreateUser
    Created on : 11-dic-2018, 19:53:59
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
            Crear Usuario
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
                        <form role="form" name="formCreateUser" action="servletCreateUser" method="post">
                            <div class="input-group">                
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input type="text" class="form-control" placeholder="Username" name="name">
                            </div>
                            <div class="input-group">                
                                <span class="input-group-addon"><i class="fa fa-key" ></i></span>
                                <input type="text" class="form-control" placeholder="Password" name="password">
                            </div>
                            <div class="input-group">                
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="text" class="form-control" placeholder="Email" name="email">
                            </div>
                            <div class="form-group">
                                <select name="rol" class="form-control">
                                    <option value="-1" selected="selected" disabled="disabled">Selecciona un rol</option>
                                    <c:if test="${fn:length(rolesList) gt 0}">
                                        <c:forEach items="${rolesList}" var="rolesList">
                                            <option value="${rolesList.getIdRole()}">${rolesList.getNameRole()}</option>
                                        </c:forEach> 
                                    </c:if>
                                </select>
                            </div>
                            <c:if test="${!empty(errorCreateUser)}">
                                <c:set var="errorCreateUser" scope="session" value="${errorCreateUser}"/>
                                <div class="alert alert-danger">
                                    <strong>Error:</strong> <c:out value="${errorCreateUser}" />
                                </div>
                            </c:if>
                            <button type="submit" class="btn btn-primary">Crear Usuario</button>
                            <button type="reset" class="btn btn-default">Resetear</button>
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
