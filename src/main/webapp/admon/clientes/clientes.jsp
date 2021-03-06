<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MiMuebleria | Gestion de clientes</title>

    <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
</head>
<body>

    <jsp:include page="/WEB-INF/admon/navBarAdmon.jsp"/>

    <c:if test="${empty(cliente)}">
        <!--Boton agregar cliente-->
        <div class="container-fluid py-3 mb-4 bg-secondary">
            <div class="row">
                <div class="col-3">
                    <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#clientModal"
                       data-controls-modal="clientModal" data-backdrop="static" data-keyboard="false">
                        <i class="fas fa-plus"></i> Agregar Cliente
                    </a>
                </div>
            </div>
        </div>

        <!--Listado de clientes-->
        <div class="container-fluid mb-5">
            <c:if test="${!empty(success)}">
                <div class="row">
                    <div class="col-10">
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                                ${success}
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-10">
                    <div class="card">
                        <div class="card-header">
                            <h4>Listado de clientes</h4>
                        </div>
                        <c:choose>
                            <c:when test="${!empty(clientes)}">
                                <div class="card-body">
                                    <table id="listClientes" class="table table-striped table-sm" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Nit</th>
                                                <th>Nombre</th>
                                                <th>Direccion</th>
                                                <th>Municipio</th>
                                                <th>Departamento</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="cliente" items="${clientes}">
                                            <tr>
                                                <td>${cliente.nitCliente}</td>
                                                <td>${cliente.nombre}</td>
                                                <td>${cliente.direccion}</td>
                                                <td>${cliente.municipio}</td>
                                                <td>${cliente.departamento}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/getCliente?nit=${cliente.nitCliente}"
                                                       class="btn btn-info btn-sm">
                                                        <i class="fas fa-edit"></i> Editar
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <h4 class="pl-4">No hay clientes</h4>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal" id="clientModal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">

                    <div class="modal-header bg-dark text-white">
                        <h5 class="modal-title">Agregar Cliente</h5>
                        <button class="close" data-dismiss="modal" onclick="$('#limpiar').click()">
                            <span>&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="form-cliente" action="${pageContext.request.contextPath}/agregarCliente" method="POST">
                        <%@include file="formCliente.jsp"%>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" form="form-cliente">Agregar</button>
                        <button id="limpiar" type="reset" class="btn btn-info" form="form-cliente">Limpiar</button>
                    </div>

                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${!empty(cliente)}">
        <div class="container-fluid mt-4">
            <div class="row d-flex justify-content-center">
                <div class="col-xl-4">
                    <div class="card">
                        <div class="card-header">
                            <h5>Editar cliente</h5>
                        </div>
                        <div class="card-body">
                            <form id="form-cliente" action="${pageContext.request.contextPath}/editarCliente" method="POST">
                                <%@include file="formCliente.jsp"%>
                        </div>
                        <div class="card-footer">
                            <button type="submit" class="btn btn-primary btn-block" form="form-cliente">Guardar cambios</button>
                            <a href="${pageContext.request.contextPath}/listarClientes" class="btn btn-danger btn-block">Cancelar</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

    <!-- JQuery Validate -->
    <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/js/personalized-messages.js"></script>
    <script src="${pageContext.request.contextPath}/js/validationFiles/formCliente.js"></script>
</body>
</html>
