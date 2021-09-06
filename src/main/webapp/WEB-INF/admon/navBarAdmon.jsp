<div>
    <nav class="navbar navbar-expand-xl fixed-top bg-dark navbar-dark">
        <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-brand">
            <i class="fas fa-dice-d6"></i>
            MiMuebleria
        </a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/listarUsuarios">Usuarios</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/listarClientes">Clientes</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/listarMuebles">Muebles</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Reportes
                    </a>

                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/#">R1</a>
                    </div>
                </li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown dropleft">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                        ${user.usuario}
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar sesion</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="py-4 mb-2"></div>
