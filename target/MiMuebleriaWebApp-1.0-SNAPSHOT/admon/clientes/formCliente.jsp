<c:choose>
    <c:when test="${!empty(cliente)}">
        <div class="form-group">
            <label for="nit">Nit:</label>
            <input type="text" class="form-control" name="nit" id="nit" value="${cliente.nitCliente}" readonly>
        </div>
    </c:when>
    <c:otherwise>
        <div class="form-group">
            <label for="nit">Nit:</label>
            <input type="text" class="form-control" name="nit" id="nit" value="${cliente.nitCliente}">
        </div>
    </c:otherwise>
</c:choose>
<div class="form-group">
    <label for="nombre">*Nombre:</label>
    <input type="text" class="form-control" name="nombre" id="nombre" value="${cliente.nombre}">
</div>
<div class="form-group">
    <label for="direccion">*Direccion:</label>
    <input type="text" class="form-control" name="direccion" id="direccion" value="${cliente.direccion}">
</div><div class="form-group">
    <label for="municipio">Municipio:</label>
    <input type="text" class="form-control" name="municipio" id="municipio" value="${cliente.municipio}">
</div><div class="form-group">
    <label for="departamento">Departamento:</label>
    <input type="text" class="form-control" name="direccion" id="departamento" value="${cliente.departamento}">
</div>
</form>