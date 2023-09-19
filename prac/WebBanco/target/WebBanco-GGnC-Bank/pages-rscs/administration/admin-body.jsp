<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>

    input[type="number"]::-webkit-inner-spin-button,
    input[type="number"]::-webkit-outer-spin-button {
        -webkit-appearance: none;
        appearance: none;
        margin: 0;
    }

</style>



<div class="bodyContent ">

    <systemMessages>
        <c:if test="${sessionScope.message != null}">
            <div class="alert alert-warning d-flex align-items-center alert-dismissible fade show" role="alert">
                <strong>Holy guacamole! </strong> ${sessionScope.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${sessionScope.errorMessage != null}">
            <div class="alert alert-danger d-flex align-items-center alert-dismissible fade show" role="alert">
                <div>
                    ${sessionScope.errorMessage}
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
    </systemMessages>

    <editUser>
        <c:if test="${editUser != null}">
            <form action="${pageContext.request.contextPath}/Administration?action=updateUser&userType=${editUser.userType}" 
                  method="POST">

                <secion id="formEdit" >
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="card ">
                                    <div class="card-header">
                                        <c:if test="${editUser.userType == 1}">
                                            <h4>Editar Empleado</h4>
                                        </c:if>
                                        <c:if test="${editUser.userType == 2}">
                                            <h4>Editar Cliente</h4>
                                        </c:if>
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="number" id="code" name="code" class="form-control" readonly value="${editUser.code}"/>
                                                    <label class="form-label" for="code">Codigo</label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <input type="text" id="forename" name="forename" class="form-control" required value="${editUser.forename}"/>
                                                    <label class="form-label" for="forename">Forename</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline mb-4">
                                                    <input type="email" id="direction" name="direction" readonly class="form-control" required value="${editUser.direction}"/>
                                                    <label class="form-label" for="direction">Direccion</label>
                                                </div>
                                            </div>
                                            <div class="col-sm-6 mb-4 ">
                                                <label class="form-label" for="genre">Sexo</label>
                                                <select type="button" class="btn form-outline dropdown-toggle" data-toggle="dropdown" id="genre" name="genre" required>
                                                    <option value="masculino">Masculino</option>
                                                    <option value="femenino">Femenino</option>
                                                    <option value="indefinido">Indefinido</option>
                                                </select>
                                            </div>
                                        </div>
                                        <c:if test="${editUser.userType == 1}">
                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <select type="button" class="btn form-outline dropdown-toggle" data-toggle="dropdown" id="turn" name="turn" required>
                                                            <c:forEach var="tr" items="${turns}">
                                                                <tr>
                                                                <option value ="${tr.id}">${tr.alias}</option>
                                                                </tr>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${editUser.userType == 2}">
                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <div class="form-outline">
                                                        <input type="date" id="birthday" name="birthday" class="form-control" value="${editUser.birthDate}"/>
                                                        <label class="form-label" for="birthday">Fecha de nacimiento</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>

                                    </div>
                                    <hr class="dropdown-divider">

                                    <section id="actions  ">
                                        <div class="container col-md-6 mb-4">
                                            <div class="row">
                                                <div class="col-xl-4">
                                                    <a href="${pageContext.request.contextPath}/Administration" class="btn btn-light btn-block">
                                                        Cancelar
                                                    </a>
                                                </div>
                                                <div class="col-xl-4">
                                                    <button type="submit" class="btn btn-success btn-block">
                                                        Guardar Cambios
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                            </div>
                        </div>
                    </div>
                </secion>
            </form>
            <hr class="dropdown-divider">

        </c:if>
    </editUser>

    <div class="d-flex justify-content-center">

        <addCustomer>
            <!--Formulario agregar usuarios-->
            <div id="AddCustomer" style="display: none;">
                <div class="container-fluid row">
                    <div class="">
                        <form class="formularioSignin needs-validation" id="formularioSignin" enctype="multipart/form-data" method="post" 
                              name="user-singin" action="${pageContext.request.contextPath}/Administration?action=addCustomer">
                            <div>
                                <h2 class="mb-4">Agregar nuevo Cliente</h2>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <input type="text" id="forename" name="forename" class="form-control" required/>
                                            <label class="form-label" for="forename">Forename</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <input type="number" id="noID" name="noID" class="form-control" required/>
                                            <label class="form-label" for="noID">DPI</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-outline mb-4">
                                    <input type="text" id="direction" name="direction" class="form-control" required/>
                                    <label class="form-label" for="direction">Direccion</label>
                                </div>
                                <div class="form-outline mb-4">
                                    <input type="password" id="userpassword" name="userpassword" class="form-control" required minlength="8"/>
                                    <label class="form-label" for="userpassword">Password</label>
                                </div>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-sm-6 ">
                                            <label class="form-label" for="genre">Sexo</label>
                                            <select type="button" class="btn form-outline dropdown-toggle" data-toggle="dropdown" id="genre" name="genre" required>
                                                <option value="masculino">Masculino</option>
                                                <option value="femenino">Femenino</option>
                                                <option value="indefinido">Indefinido</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input type="date" id="birthday" name="birthday" class="form-control" />
                                                <label class="form-label" for="birthday">Fecha de nacimiento</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input type="file" class="form-control-file border" id="JSONfile" name="JSONfile">
                                        <label class="form-label" for="JSONfile">DPI adjunto</label>
                                    </div>
                                </div>            
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary"> Agregar </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- fin form-->
            <hr class="dropdown-divider">
        </addCustomer>

        <addEmployee>
            <!--Formulario agregar empleados-->
            <div id="addEmployee" style="display: none;">
                <div class="container-fluid row">
                    <div class="">
                        <form class="formularioSignin needs-validation" id="formularioSignin" enctype="multipart/form-data" method="post" 
                              name="user-singin" action="${pageContext.request.contextPath}/Administration?action=addEmployee">
                            <div>
                                <h2 class="mb-4">Registrar nuevo empleado</h2>
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <input type="text" id="forename" name="forename" class="form-control" required/>
                                            <label class="form-label" for="forename">Forename</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <input type="number" id="noID" name="noID" class="form-control" required/>
                                            <label class="form-label" for="noID">DPI</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-outline mb-4">
                                    <input type="text" id="direction" name="direction" class="form-control" required/>
                                    <label class="form-label" for="direction">Direccion</label>
                                </div>
                                <div class="form-outline mb-4">
                                    <input type="password" id="userpassword" name="userpassword" class="form-control" required minlength="8"/>
                                    <label class="form-label" for="userpassword">Password</label>
                                </div>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-sm-6 ">
                                            <label class="form-label" for="genre">Sexo</label>
                                            <select type="button" class="btn form-outline dropdown-toggle" data-toggle="dropdown" id="genre" name="genre" required>
                                                <option value="masculino">Masculino</option>
                                                <option value="femenino">Femenino</option>
                                                <option value="indefinido">Indefinido</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <select type="button" class="btn form-outline dropdown-toggle" data-toggle="dropdown" id="turn" name="turn" required>
                                                    <c:forEach var="tr" items="${turns}">
                                                        <tr>
                                                        <option value ="${tr.id}">${tr.alias}</option>
                                                        </tr>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                </div>            
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary"> Agregar </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- fin form-->
            <hr class="dropdown-divider">
        </addEmployee>

        <newAccount> 
            <div class="modal fade" id="createAccount" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Agregar cuenta</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin" action="${pageContext.request.contextPath}/Administration?action=createAccount">
                            <div class="modal-body">

                                <div class="row mb-4">
                                    <div class="form-outline">
                                        <label for="usrCode" class="form-label">Codigo usuario</label>
                                        <input class="form-control" list="datalistOptions" id="usrCode" name="usrCode" placeholder="Codigo - nombre">
                                        <datalist id="datalistOptions">
                                            <c:forEach var="c" items="${customers}">
                                                <option value="${c.code}">${c.forename}</option>
                                            </c:forEach>
                                        </datalist>
                                    </div>
                                </div>
                                <div class="row mb-4 ">
                                    <div class="form-outline">
                                        <label class="form-label" for="amount">Costo</label>
                                        <input type="number" step="0.01"  id="amount" name="amount" class="form-control" required/>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary"> Agregar </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </newAccount>
        <hr class="dropdown-divider">

    </div>

    <showAccounts>
        <div id="showAccounts" style="display: none;" class="mb-4">
            <h1>Cuentas</h1>

            <c:forEach var="ac" items="${accounts}">
                <div class="card shadow">
                    <div class="card-body ">
                        <div class="row">
                            <div class="col mb-0">
                                <svg class="bd-placeholder-img card-img" width="100%" height="50%" role="img" focusable="false">
                                <title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="25%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
                            </div>
                            <div class="col-6 mb-0">
                                <h2 class="card-text mb-4">${ac.customer.forename} - No. ${ac.code}</h2>
                            </div>
                            <div class="col mb-0">
                                <h2 class="card-text mb-4">$. ${ac.amount}</h2>
                            </div>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <button type="button" class="btn  btn-outline-secondary">View</button>
                            <button type="button" class="btn  btn-outline-secondary">Edit</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </showAccounts>

    <showUsers>
        <div id="showUsers" style="display: none;" class="mb-4">
            <div class="row">
                <div class="col">
                    <h1>Clientes</h1>

                    <c:forEach var="cu" items="${customers}">
                        <div class="card shadow">
                            <div class="card-body ">
                                <div class="row">
                                    <div class="col-6 mb-0">
                                        <h2 class="card-text mb-4">${cu.forename} - ${cu.code}</h2>
                                    </div>
                                    <div class="col mb-0">
                                        <h2 class="card-text mb-4">Nacimiento</h2>
                                        <h5>  ${cu.birthDate}</h5>

                                    </div>
                                </div>
                                <div class="d-flex justify-content-between align-items-center">
                                    <button type="button" class="btn  btn-outline-secondary">View</button>
                                    <a href="${pageContext.request.contextPath}/Administration?action=editUser&code=${cu.code}&userType=${cu.userType}"
                                       class="btn  btn-outline-secondary">Editar</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>                
                </div>
                <div class="col">
                    <h1> Empleados</h1>

                    <c:forEach var="cu" items="${employees}">
                        <div class="card shadow">
                            <div class="card-body ">
                                <div class="row">
                                    <div class="col-6 mb-0">
                                        <h2 class="card-text mb-4">${cu.forename} - ${cu.code}</h2>
                                    </div>
                                    <div class="col mb-0">
                                        <h3 class="card-text mb-4">${cu.turno.alias}: </h3>
                                        <h5>  ${cu.turno.entryTime} - ${cu.turno.leaveTime}</h5>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between align-items-center">
                                    <button type="button" class="btn  btn-outline-secondary">View</button>         
                                    <a href="${pageContext.request.contextPath}/Administration?action=editUser&code=${cu.code}&userType=${cu.userType}"
                                       class="btn  btn-outline-secondary">Editar</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>   
                </div>
            </div>
        </div>
    </showUsers>

    <showReports>
        <div  id="showReports" style="display: visible;" class="mb-4">

            <changesRep>
                <div class="row mb-4">
                    <h3>Reporte de cambios</h3>
                    <div class="form-outline">
                        <form class="row g-3" method="post" action="${pageContext.request.contextPath}/Administration?action=searchChanges">
                            <div class="col-auto">
                                <input class="form-control" list="datalistOptions" id="usrCode" name="usrCode" placeholder="Codigo - nombre" value="0">
                                <datalist id="datalistOptions">
                                    <c:forEach var="c" items="${customers}">
                                        <option value="${c.code}">${c.forename}</option> 
                                    </c:forEach>
                                </datalist>    
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-secondary mb-3">Buscar</button>
                            </div>
                        </form>
                    </div>
                    <c:if test="${changesReq != null}">
                        <c:forEach var="ch" items="${changesReq}">
                            <div class="card shadow">
                                <div class="card-body ">
                                    <div class="row">
                                        <div class="col-6 mb-0">
                                            <h2 class="card-text mb-4">${ch.admin.forename}</h2>
                                            <h3>cambio: ${ch.id}</h3>
                                        </div>
                                        <div class="col mb-0">
                                            <h3 class="card-text mb-4">Usuario cambiado: ${ch.user.forename} (${ch.user.code}) </h3>
                                            <h5>  Realizado: ${ch.changeDate} - ${ch.changeTime}</h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>   
                    </c:if>
                </div>
            </changesRep>

            <topAccounts>
                <h3>Usuarios con mas saldo</h3>
                <c:forEach var="ac" items="${topAccounts}">
                    <div class="card shadow">
                        <div class="card-body ">
                            <div class="row">
                                <div class="col mb-0">
                                    <h2 class="card-text mb-4">${ac.customer.forename} </h2>
                                </div>
                                <div class="col-6 mb-0">
                                    <h4 class="card-text mb-4">No. cuenta: ${ac.code}</h4>
                                </div>
                                <div class="col mb-0">
                                    <h2 class="card-text mb-4">$. ${ac.amount}</h2>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </topAccounts>

        </div>
    </showReports>


</div>