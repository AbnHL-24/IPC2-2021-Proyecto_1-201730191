    <c:if test="${!empty(pieza)}">
        <div class="form-group">
            <label for="nombre">*Nombre pieza:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${pieza.tipo}" readonly>
        </div>
        <div class="form-group">
            <label for="costo">*Costo:</label>
            <input type="text" class="form-control" name="costo" id="costo" value="${pieza.costo}" readonly>
            <input type="text" class="form-control d-none" name="id" id="id" value="${pieza.idPieza}" readonly>
        </div>
    </c:if>

    <c:if test="${empty(pieza)}">
        <div class="form-group">
            <label for="nombre">*Nombre pieza:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" autofocus>
        </div>
        <div class="form-group">
            <label for="costo">*Costo:</label>
            <input type="text" class="form-control" name="costo" id="costo">
        </div>
    </c:if>
    <div class="form-group">
        <label for="stock">*Existencias iniciales:</label>
        <input type="text" class="form-control" name="stock" id="stock" value="${pieza.existencia}">
    </div>
</form>

