<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MiMuebleria | Muebles para ensamblar</title>

    <!--CSS-->
    <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
</head>
<body>

    <!--Barra de navegacion-->
    <jsp:include page="/WEB-INF/fabrica/navBarFabrica.jsp" />

        <!--Listado de muebles para ensamblar-->
        <div class="container-fluid mb-5">
            <div class="row">
                <div class="col-xl-10">
                    <div class="card">
                        <div class="card-header">
                            <h4>Muebles para ensamble</h4>
                        </div>
                        <div class="card-body">
                            <table id="listMuebles" class="table table-striped table-sm">
                                <thead class="thead-dark">
                                <tr>
                                    <th>Tipo mueble</th>
                                    <th>Precio de venta</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="mueble" items="${muebles}">
                                    <tr>
                                        <td>${mueble.mueble}</td>
                                        <td>${mueble.precio}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/#"
                                               class="btn btn-warning btn-sm">
                                                <i class="fas fa-tools"></i> Ensamblar
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

    <!--JS-->
    <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
</body>
</html>
