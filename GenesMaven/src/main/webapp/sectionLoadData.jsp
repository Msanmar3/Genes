<%-- 
    Document   : sectionLoadData
    Created on : 22-ene-2019, 18:53:33
    Author     : Mónica Sánchez Martín
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="usersList" scope="session" value="${usersList}"/>
<c:set var="user" scope="session" value="${user}"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Cargar Genes
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
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Herramienta de carga de Genes.</h3> 
                        <div id="loading_spinner">
                            <img src="images/squares.gif" alt=""/>
                        </div>
                    </div>
                    <!-- /.box-header -->
                    <!-- form start -->
                    <form role="form" action="servletLoadData" method="post" name="formLoadGenes" enctype="multipart/form-data">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="species" class="col-sm-2 control-label">Especie</label>
                                <select name="species" size="1" id="species" class="form-control">
                                    <option value=''>Seleccione una especie</option>
                                    <c:choose> 
                                        <c:when test="${fn:length(listSpecies) gt 0}">
                                            <c:forEach items="${listSpecies}" var="specie">
                                                <option value="${specie.getIdSpecie()}"> ${specie.getNameSpecie()} </option>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                                </select>
                                <p class="help-block">Seleccione la especie</p>
                            </div>
                            <div class="form-group">
                                <label for="authors" class="col-sm-2 control-label">Authors</label>
                                <!--                                    <input type="text" class="form-control" id="secondName" name="secondName" placeholder="Segunda parte">-->
                                <select name="authors" size="1" id="authors" class="form-control">

                                </select>
                                <p class="help-block">Seleccione el author</p>
                            </div>
                            <div class="form-group">
                                <label for="iterations" class="col-sm-2 control-label">Iterations</label>
                                <!--                                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Primera parte">-->
                                <select class="form-control" name="iterations" size="1" id="iterations">

                                </select>
                                <p class="help-block">Seleccione la iteraci&oacute;n</p>
                            </div>
                            <div class="form-group">
                                <label>Archivo:</label>
                                <input type="file" name="uploadFile"/>
                                <p class="help-block">Seleccione el fichero de datos</p>
                            </div>

                        </div>
                        <!-- /.box-body -->

                        <div class="box-footer">

                            <input type="hidden" name="opcion" value="${user.getIdUser()}">
                            <button type="submit" class="btn btn-primary" value="Upload" >Cargar genes</button>
                            <button type="reset" class="btn btn-warning">Reiniciar subida</button>
                        </div>
                    </form>
                </div>


            </div>
        </div>
    </section>
</div>
