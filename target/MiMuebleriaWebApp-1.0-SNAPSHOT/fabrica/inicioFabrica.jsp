<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MiMuebleria | Incio Fabrica</title>
    <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
</head>
<body>

    <jsp:include page="/WEB-INF/fabrica/navBarFabrica.jsp"/>

    <!--Listado de piezas a agotarse-->
    <div class="container-fluid mb-5">
        <div class="row">
            <div class="col-xl-10">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de piezas a agotarse</h4>
                    </div>
                    <div class="card-body">
                        <table id="listPiezas" class="table table-striped table-sm">
                            <thead class="thead-dark">
                            <tr>
                                <th>Nombre</th>
                                <th>Costo</th>
                                <th>Existencias</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="pieza" items="${piezas}">
                                <tr>
                                    <td>${pieza.tipo}</td>
                                    <td>${pieza.costo}</td>
                                    <td>${pieza.existencia}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
</body>
</html>