<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>MiMuebleria | Gestion de usuarios</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/admon/navBarAdmon.jsp" />

        <c:if test="${empty(usuario)}">
            <!--Boton agregar usuario-->
            <div class="container-fluid py-3 mb-4 bg-secondary">
                <div class="row">
                    <div class="col-xl-3">
                        <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#usuarioModal"
                           data-controls-modal="usuarioModal" data-backdrop="static" data-keyboard="false">
                            <i class="fas fa-plus"></i> Agregar Usuario
                        </a>
                    </div>
                </div>
            </div>

            <!--Listado de usuarios-->
            <div class="container-fluid mb-5">
                <c:if test="${!empty(success)}">
                    <div class="row">
                        <div class="col-3">
                            <div class="alert alert-success alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                ${success}
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-xl-10">
                        <div class="card">
                            <div class="card-header">
                                <h4>Listado de usuarios</h4>
                            </div>
                            <div class="card-body">
                                <table id="listUsuarios" class="table table-striped table-sm">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Nombre de usuario</th>
                                            <th>Tipo usuario</th>
                                            <th>Estado</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="usuario" items="${usuarios}">
                                            <tr>
                                                <td>${usuario.usuario}</td>
                                                <c:choose>
                                                    <c:when test="${usuario.tipoUsuario eq 1}">
                                                        <td>Administracion</td>
                                                    </c:when>
                                                    <c:when test="${usuario.tipoUsuario eq 3}">
                                                        <td>Fabrica</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>Ventas</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${usuario.estadoUsuario eq 1}">
                                                        <td>Activo</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>Deshabilitado</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/getUsuario?username=${usuario.usuario}"
                                                       class="btn btn-info btn-sm">
                                                        <i class="fas fa-edit"></i> Editar
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal" id="usuarioModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <div class="modal-header bg-dark text-white">
                            <h5 class="modal-title">Agregar Usuario</h5>
                            <button class="close" data-dismiss="modal" onclick="$('#limpiar').click()">
                                <span>&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="form-usuario" action="${pageContext.request.contextPath}/agregarUsuario" method="POST">
                                <%@include file="formUsuario.jsp"%>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" form="form-usuario">Agregar</button>
                            <button id="limpiar" type="reset" class="btn btn-info" form="form-usuario">Limpiar</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty(usuario)}">
            <div class="container-fluid mt-4">
                <div class="row d-flex justify-content-center">
                    <div class="col-xl-4">
                        <div class="card">
                            <div class="card-header">
                                <h5>Editar usuario</h5>
                            </div>
                            <div class="card-body">
                                <form id="form-usuario" action="${pageContext.request.contextPath}/actualizarUsuario" method="POST">
                                    <%@include file="formUsuario.jsp"%>
                            </div>
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btn-block" form="form-usuario">Guardar cambios</button>
                                <a href="${pageContext.request.contextPath}/listarUsuarios" class="btn btn-danger btn-block">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

        <!-- JQuery Validate -->
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script src="${pageContext.request.contextPath}/js/personalized-messages.js"></script>
        <script src="${pageContext.request.contextPath}/js/validationFiles/formUsuario.js"></script>
    </body>
</html>
