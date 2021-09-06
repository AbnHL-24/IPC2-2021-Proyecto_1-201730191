    <c:if test="${!empty(usuario)}">
        <div class="form-group">
            <label for="username">*Nombre de usuario:</label>
            <input type="text" class="form-control" name="username" id="username" value="${usuario.usuario}" autofocus readonly>
        </div>
    </c:if>
    <c:if test="${empty(usuario)}">
    <div class="form-group">
        <label for="username">*Nombre de usuario:</label>
        <input type="text" class="form-control" name="username" id="username" value="${usuario.usuario}" autofocus>
    </div>
    </c:if>

    <div class="form-group">
        <label for="tipo">*Tipo de usuario:</label>
        <select class="form-control" name="tipo" id="tipo">
            <c:choose>
                <c:when test="${usuario.tipoUsuario == 1}">
                    <option value="1">Administrador</option>
                    <option value="2">Ventas</option>
                    <option value="3">Fabrica</option>
                </c:when>
                <c:when test="${usuario.tipoUsuario == 2}">
                    <option value="2">Ventas</option>
                    <option value="3">Fabrica</option>
                    <option value="1">Administrador</option>
                </c:when>
                <c:otherwise>
                    <option value="3">Fabrica</option>
                    <option value="1">Administrador</option>
                    <option value="2">Ventas</option>
                </c:otherwise>
            </c:choose>
        </select>
    </div>
            
    <div class="form-group">
        <label for="password">*Password:</label>
        <input type="password" class="form-control" name="password" id="password" value="${usuario.password}">
    </div>
    <c:if test="${!empty(usuario)}">
        <div class="form-group">
            <label for="estado">*Estado del usuario:</label>
            <select class="form-control" name="estado" id="estado">
                <c:choose>
                    <c:when test="${usuario.estadoUsuario == 1}">
                        <option value="1">Activo</option>
                        <option value="2">Deshabilitado</option>
                    </c:when>
                    <c:otherwise>
                        <option value="2">Deshabilitado</option>
                        <option value="1">Activo</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
    </c:if>
</form>

