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
            Crear Usuario Index
        </h1>
    </section>
    <br>
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

                <c:if test="${!empty(errorCreateUser)}">
                    <c:set var="errorCreateUser" scope="session" value="${errorCreateUser}"/>
                    <div class="alert alert-danger">
                        <strong>Error:</strong> <c:out value="${errorCreateUser}" />
                    </div>
                </c:if>
                <button type="submit" class="btn btn-default">Crear Usuario</button>
                <button type="reset" class="btn btn-default">Resetear</button>
            </form> 
            <!-- /input-group -->
        </div>
        <!-- /.box-body -->
    </div>
    <!-- /.box -->

</div>
