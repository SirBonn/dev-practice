
<navBar>
    <div class="content">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Empleados W-B</a>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-4 mb-lg-0">

                    </ul>
                    <ul class="navbar-nav col-4 col-lg-2">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="sessionDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                ${sessionScope.user.forename}
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="sessionDropdown">
                                <li><a class="dropdown-item" type="button" href="${pageContext.request.contextPath}/BankLogin?action=signOut">
                                        Cerrar Sesion</a></li>
                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>
    </div>
</navBar>

<sideBar class="sidebar" id="sidebar">
    <div>
        <a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
            <svg class="bi me-2" width="30" height="24"></svg>
            <span class="fs-5 fw-semibold">W - B</span>
        </a>
        <ul class="list-unstyled ps-0">


            <li class="mb-1">   
                <button class="btn dropdown-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="true">
                    Otros
                </button>
                <div class="collapse show" id="orders-collapse">
                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                        <li><a class="dropdown-item" href="#">Solicitudes</a></li>

                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" onclick="mostrarFormulario('showReports')">Ver Reportes</a></li>
                    </ul>
                </div>
            </li>
            <li class="border-top my-3"></li>

        </ul>
    </div>
</sideBar>

