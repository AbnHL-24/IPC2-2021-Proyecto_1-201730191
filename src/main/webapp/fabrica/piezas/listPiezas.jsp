<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>MiMuebleria | Gestion de piezas</title>

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
    </head>
    <body>

        <!--Barra de navegacion-->
        <jsp:include page="/WEB-INF/fabrica/navBarFabrica.jsp" />

        <c:if test="${empty(pieza)}">
            <!--Boton agregar pieza-->
            <div class="container-fluid py-3 mb-4 bg-secondary">
                <div class="row">
                    <div class="col-xl-3">
                        <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#piezaModal"
                           data-controls-modal="piezaModal" data-backdrop="static" data-keyboard="false">
                            <i class="fas fa-plus"></i> Agregar Pieza
                        </a>
                    </div>
                </div>
            </div>

            <!--Listado de piezas-->
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
                                <h4>Listado de piezas</h4>
                            </div>
                            <div class="card-body">
                                <table id="listPiezas" class="table table-striped table-sm">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Costo</th>
                                            <th>Existencias</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="pieza" items="${piezas}">
                                            <tr>
                                                <td>${pieza.tipo}</td>
                                                <td>${pieza.costo}</td>
                                                <td>${pieza.existencia}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/getPieza?id=${pieza.idPieza}"
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

            <div class="modal" id="piezaModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">

                        <div class="modal-header bg-dark text-white">
                            <h5 class="modal-title">Agregar Pieza</h5>
                            <button class="close" data-dismiss="modal" onclick="$('#limpiar').click()">
                                <span>&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="form-pieza" action="${pageContext.request.contextPath}/agregarPieza" method="POST">
                                <%@include file="formPieza.jsp"%>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" form="form-pieza">Agregar</button>
                            <button id="limpiar" type="reset" class="btn btn-info" form="form-pieza">Limpiar</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${!empty(pieza)}">
            <div class="container-fluid mt-4">
                <div class="row d-flex justify-content-center">
                    <div class="col-xl-4">
                        <div class="card">
                            <div class="card-header">
                                <h5>Editar pieza</h5>
                            </div>
                            <div class="card-body">
                                <form id="form-pieza" action="${pageContext.request.contextPath}/actualizarPieza" method="POST">
                                    <%@include file="formPieza.jsp"%>
                            </div>
                            <div class="card-footer">
                                <button type="submit" class="btn btn-primary btn-block" form="form-pieza">Guardar cambios</button>
                                <a href="${pageContext.request.contextPath}/listarPiezas" class="btn btn-danger btn-block">Cancelar</a>
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
        <script src="${pageContext.request.contextPath}/js/validationFiles/formPieza.js"></script>
    </body>
</html>
