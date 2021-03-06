<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="user" scope="session" value="${user}"/>
<c:set var="listRolesMenu" scope="session" value="${listRolesMenu}"/>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="app/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${user.getNameUser()}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- search form -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <!--<li class="header">MENÚ</li>-->
            <a href = "<c:url value = "/data/prueba.txt"/>">TEST</a>
            <li class="treeview">
                <c:choose> 
                    <c:when test="${fn:length(listRolesMenu) gt 0}">
                        <c:forEach items="${listRolesMenu}" var="menu">
                            <!--Si es padre (0) se pinta con el desplegable-->
                            <c:if test="${menu.getIdMenu().getIdParent() < 1}">
                                <a href="">
                                    <i class="${menu.getIdMenu().getIcon()}"></i> 
                                    <span> ${menu.getIdMenu().getNameMenu()}</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <!--Pintamos los hijos-->
                                <ul class="treeview-menu">
                                    <c:forEach items="${listRolesMenu}" var="menuHijo">
                                        <!--el id del padre del hijo coincide con el id del padre-->
                                        <c:if test = "${(menuHijo.getIdMenu().getPosition()>0) && (menuHijo.getIdMenu().getIdParent()== menu.getIdMenu().getIdMenu())}">

                                            <li>
                                                <a href="${menuHijo.getIdMenu().getAction()}">
                                                    <i class="${menuHijo.getIdMenu().getIcon()}"></i> 
                                                    ${menuHijo.getIdMenu().getNameMenu()}
                                                </a>
                                            </li>

                                        </c:if>
                                    </c:forEach> 
                                </ul>
                            </c:if>
                        </c:forEach> 
                    </c:when>  
                    <c:otherwise>  
                        <ul class="treeview-menu">
                            <li><a href="index.html"><i class="fa fa-circle-o"></i> No tiene permisos.</a></li>
                        </ul>
                    </c:otherwise>  
                </c:choose>  
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>