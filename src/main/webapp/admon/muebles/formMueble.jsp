    <c:if test="${!empty(mueble)}">
        <div class="form-group">
            <label for="nombre">*Nombre mueble:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${mueble.mueble}" autofocus readonly>
        </div>
    </c:if>
    <c:if test="${empty(mueble)}">
        <div class="form-group">
            <label for="nombre">*Nombre mueble:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${mueble.mueble}" autofocus>
        </div>
    </c:if>
    <div class="form-group">
        <label for="precio">*Precio de venta:</label>
        <input type="text" class="form-control" name="precio" id="precio" value="${mueble.precio}">
    </div>
</form>

