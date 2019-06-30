<%-- 
    Document   : sectionLoadData
    Created on : 22-ene-2019, 18:53:33
    Author     : M�nica S�nchez Mart�n
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
                    <form role="form" action="servletLoadData" method="post" name="formLoadGenes" enctype="multipart/form-data">

                        <div class="box-body">
                            <!--                        <div >
                                                        Configurar Cytoscape
                                                    </div>-->

                            <div class="form-group">
                                <label for="origin" class="col-sm-2 control-label">Especie</label>
                                <div class="col-sm-10">
                                    <select name="species" size="1" id="species" class="form-control">
                                        <c:choose> 
                                            <c:when test="${fn:length(listSpecies) gt 0}">
                                                <c:forEach items="${listSpecies}" var="specie">
                                                    <option value="${specie.getIdSpecie()}"> ${specie.getNameSpecie()} </option>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                    <div id="loading_spinner">
                                        <img src="images/squares.gif" alt=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="secondName" class="col-sm-2 control-label">Authors</label>
                                <div class="col-sm-10">
                                    <!--                                    <input type="text" class="form-control" id="secondName" name="secondName" placeholder="Segunda parte">-->
                                    <select name="authors" size="1" id="authors" class="form-control">

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="firstName" class="col-sm-2 control-label">Iterations</label>
                                <div class="col-sm-10">
                                    <!--                                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Primera parte">-->
                                    <select class="form-control" name="iterations" size="1" id="iterations">

                                    </select>
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

            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Quick Example</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form role="form" action="servletLoadData" method="post" name="formLoadGenes" enctype="multipart/form-data">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email address</label>
                            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputFile">File input</label>
                            <input type="file" id="exampleInputFile">

                            <p class="help-block">Example block-level help text here.</p>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Check me out
                            </label>
                        </div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>


        </div>
</div>
</section>
</div>