<%-- 
    Document   : sectionCompareGenes
    Created on : 22-ene-2019, 19:22:35
    Author     : winlatop
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="usersList" scope="session" value="${usersList}"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Comparar Genes
        </h1>
    </section>
    <br>
    <!-- Input addon -->
    <div class="box box-info">
        <div class="box-body">
            <form role="form" action="servletCompareGenes" method="post" name="servletCompareGenes">
                <div class="form-group">
                    <label>Organismo</label>
                    <select name="specie" class="form-control">
                        <option value="-1" selected="selected" disabled="disabled">Selecciona un organismo</option>
                        <c:if test="${fn:length(speciesList) gt 0}">
                            <c:forEach items="${speciesList}" var="specieL">
                                <option value="${specieL.getNameSpecie()}">${specieL.getNameSpecie()}</option>
                            </c:forEach> 
                        </c:if>
                    </select>
                </div>
                <div class="form-group">
                    <label>Origin</label>
                    <select name="specie" class="form-control">
                        <option value="-1" selected="selected" disabled="disabled">Selecciona un orgin</option>
                        <c:if test="${fn:length(originList) gt 0}">
                            <c:forEach items="${originList}" var="originL">
                                <option value="${originL.getNameOrigin()}">${originL.getNameSpecie()}</option>
                            </c:forEach> 
                        </c:if>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">Submit Button</button>
                <button type="reset" class="btn btn-default">Reset Button</button>
            </form>            
            <!-- /input-group -->
        </div>
        <!-- /.box-body -->
    </div>
    <!-- /.box -->

</div>


<!--<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h1 class="page-header">
                    Section Comparar Genes
                </h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Configurar Cytoscape
                    </div>
                    <div class="panel-body">
                        <form role="form" action="servletCompareGenes" method="post" name="servletCompareGenes">
                            <div class="form-group">
                                <label>Organismo</label>
                                <select name="specie" class="form-control">
                                    <option value="-1" selected="selected" disabled="disabled">Selecciona un organismo</option>
                                    <c:if test="${fn:length(speciesList) gt 0}">
                                        <c:forEach items="${speciesList}" var="specieL">
                                            <option value="${specieL.getNameSpecie()}">${specieL.getNameSpecie()}</option>
                                        </c:forEach> 
                                    </c:if>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Origin</label>
                                <select name="specie" class="form-control">
                                    <option value="-1" selected="selected" disabled="disabled">Selecciona un orgin</option>
                                    <c:if test="${fn:length(originList) gt 0}">
                                        <c:forEach items="${originList}" var="originL">
                                            <option value="${originL.getNameOrigin()}">${originL.getNameSpecie()}</option>
                                        </c:forEach> 
                                    </c:if>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-default">Submit Button</button>
                            <button type="reset" class="btn btn-default">Reset Button</button>
                        </form>                   
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Viewport Cytoscape
                    </div>
                    <div id="cy" style="height: 300px;"class="panel-body">

                    </div>
                </div></div>
        </div>




        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Salida
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Specie</th>
                                        <th>Origin</th>
                                        <th>File</th>
                                        <th>Gen A</th>
                                        <th>Gen B</th>
                                        <th>Weight</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${fn:length(speciesListFilter) gt 0}">
                                        <c:forEach items="${speciesListFilter}" var="sp">
                                            <tr>
                                                <td>${sp.getId()}</td>
                                                <td>${sp.getSpecie()}</td>
                                                <td>${sp.getOrigin()}</td>
                                                <td>${sp.getFile()}</td>
                                                <td>${sp.getGene_A()}</td>
                                                <td>${sp.getGene_B()}</td>
                                                <td>${sp.getWeight()} </td>

                                            </tr>
                                        </c:forEach> 
                                    </c:if>

                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>-->
