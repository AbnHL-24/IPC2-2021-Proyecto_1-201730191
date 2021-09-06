<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MiMuebleria - Carga de archivo</title>

    <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>
</head>
<body>

    <jsp:include page="/WEB-INF/all/navBar.jsp"/>

    <div class="container-fluid mt-5">
        <div class="row py-2 bg-light mt-5">
            <div class="col-xl-3">
                <h3 class="mt-2 ml-3">Seleccion de archivos</h3>
            </div>
        </div>

        <form id="form-archivo" action="${pageContext.request.contextPath}/lecturaArchivo" method="POST" enctype="multipart/form-data">
            <div class="row pl-5">
                <div class="col-xl-6">
                    <div class="my-2">
                        <label for="archivoEntrada" class="font-weight-bold">Seleccione el archivo de entrada:</label>
                        <input type="file" class="form-control-file border" name="archivoEntrada" id="archivoEntrada" accept=".txt">
                    </div>
                </div>
            </div>
            <div class="row pl-5">
                <div class="col-xl-4"></div>
                <div class="col-xl-2">
                    <button type="submit" class="btn btn-primary btn-block mt-2">Continuar</button>
                </div>
            </div>
        </form>
    </div>

    <!--JS-->
    <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>

    <!-- JQuery Validate -->
    <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/js/validationFiles/archivoEntrada.js"></script>

</body>
</html>
