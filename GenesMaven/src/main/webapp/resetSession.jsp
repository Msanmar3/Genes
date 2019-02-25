<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="errorCreateUser" scope="session" value=""/>
<!--Como es un enlace borramos la variable de sesión así-->
<c:redirect url="formularioalta.jsp"/>