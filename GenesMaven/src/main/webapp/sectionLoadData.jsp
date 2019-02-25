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
                <!-- Input addon -->
                <div class="box box-info">
                    <div class="box-body">
<!--                        <div >
                            Configurar Cytoscape
                        </div>-->
                        <form role="form" action="servletLoadData" method="post" name="formLoadGenes" enctype="multipart/form-data">

                            <div class="form-group">
                                <label for="origin" class="col-sm-2 control-label">Especie</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="origin" name="origin" placeholder="Especie">
                                </div>
                            </div>
                            <!--  Preguntar a Fran que nombre le pongo -->
                            <div class="form-group">
                                <label for="firstName" class="col-sm-2 control-label">Primera parte</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Primera parte">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="secondName" class="col-sm-2 control-label">Segunda parte</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="secondName" name="secondName" placeholder="Segunda parte">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Archivo:</label>
                                <input type="file" name="uploadFile"/>
                            </div>
                           <input type="hidden" name="opcion" value="${user.getIdUser()}">
                            <button type="submit" class="btn btn-primary" value="Upload" >Cargar genes</button>
                            <button type="reset" class="btn btn-warning">Reiniciar subida</button>
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
