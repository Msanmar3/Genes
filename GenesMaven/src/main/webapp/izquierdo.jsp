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
            <li class="active treeview menu-open">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <c:choose> 
                    <c:when test="${fn:length(listRolesMenu) gt 0}">
                        <c:forEach items="${listRolesMenu}" var="menu">
                            <ul class="treeview-menu">
                          
                                <li>
                                    <a href="${menu.getIdMenu().getAction()}">
                                        <i class="${menu.getIdMenu().getIcon()}"></i> 
                                        ${menu.getIdMenu().getNameMenu()}
                                    </a>
                                </li>
                            </ul>
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