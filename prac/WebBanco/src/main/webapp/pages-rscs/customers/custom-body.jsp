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



    <div class="d-flex justify-content-center">

        <ownTransfer> 
            <div class="modal fade" id="ownTransfer" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="transferModalTIttle">Transferencia a cuentas propias</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin"
                              action="${pageContext.request.contextPath}/Customer?action=ownTransfer">
                            <div class="modal-body">

                                <div class="row mb-4">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="amount">Cantidad:</label>
                                            <input type="number" id="amount" name="amount" class="form-control" required/>
                                        </div>
                                    </div>
                                    <div class="form-outline col-sm-6 mb-4 ">
                                        <label for="debAccount" class="form-label">Cuenta a debitar</label>
                                        <select type="button" class="btn form-outline form-control dropdown-toggle" data-toggle="dropdown" id="debAccount" name="debAccount" required>
                                            <option value="-1">- Selecciona una cuenta -</option>
                                            <c:forEach var="c" items="${userAccounts}">
                                                <option value="${c.code}">${c.code} - Q. ${c.amount}</option>
                                            </c:forEach>    
                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-4 ">
                                    <div class="form-outline">
                                        <label for="acAccount" class="form-label">Cuenta a acreditar</label>
                                        <select type="button" class="btn form-outline form-control dropdown-toggle" data-toggle="dropdown" id="acAccount" name="acAccount" required>
                                            <option value="-1">- Selecciona una cuenta -</option>
                                            <c:forEach var="c" items="${userAccounts}">
                                                <option value="${c.code}">${c.code} - Q. ${c.amount}</option>
                                            </c:forEach>    
                                        </select>
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
        </ownTransfer>
                            <thirdTransfer> 
            <div class="modal fade" id="thirdTransfer" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="transferModalTIttle">Transferencia a terceras personas</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin"
                              action="${pageContext.request.contextPath}/Customer?action=thirdTransfer">
                            <div class="modal-body">
                                <div class="row mb-4">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="amount">Cantidad:</label>
                                            <input type="number" id="amount" name="amount" class="form-control" required/>
                                        </div>
                                    </div>
                                    <div class="form-outline col-sm-6 mb-4 ">
                                        <label for="debAccount" class="form-label">Cuenta a debitar</label>
                                        <select type="button" class="btn form-outline form-control dropdown-toggle" data-toggle="dropdown" id="debAccount" name="debAccount" required>
                                            <option value="-1">- Selecciona una cuenta -</option>
                                            <c:forEach var="c" items="${userAccounts}">
                                                <option value="${c.code}">${c.code} - Q. ${c.amount}</option>
                                            </c:forEach>    
                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-4 ">
                                    <div class="form-outline">
                                        <label for="acAccount" class="form-label">Cuenta a acreditar</label>
                                        <select type="button" class="btn form-outline form-control dropdown-toggle" data-toggle="dropdown" id="acAccount" name="acAccount" required>
                                            <option value="-1">- Selecciona una cuenta -</option>
                                            <c:forEach var="c" items="${asociations}">
                                                <option value="${c.thirdPartyAccount.code}">${c.thirdPartyAccount.code} - ${c.thirdPartyAccount.customer.forename}</option>
                                            </c:forEach>    
                                        </select>
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
        </thirdTransfer>
                            
        <hr class="dropdown-divider">
        <assocAccount> 
            <div class="modal fade" id="assocAccount" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="transferModalTIttle">Transferencia a cuentas propias</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin"
                              action="${pageContext.request.contextPath}/Customer?action=requestAsociation">
                            <div class="modal-body">

                                <div class="row mb-4">
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="usrCode">Mi codigo</label>
                                        <input type="number" id="usrCode" name="usrCode" class="form-control" readonly value="${sessionScope.user.code}"/>
                                    </div>
                                </div>
                                <div class="row mb-4 ">
                                    <div class="form-outline">
                                        <label for="accountCode" class="form-label">Cuenta a asociar</label>
                                        <input type="number" id="accountCode" name="accountCode" class="form-control"required/>
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
        </assocAccount>
    </div>

    <showReports>
        <div  id="showReports" style="display: visible;" class="mb-4">
            <div class="row">
                <div class="col-md-8">
                    <UserAccounts class="mb-4">
                        <h3>Cuentas bajo codigo: ${sessionScope.user.code}</h3>
                        <c:forEach var="ac" items="${userAccounts}">
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
                    </UserAccounts>
                    <hr class="dropdown-divider">

                    <asociatedAccounts class="mb-4">
                        <h3>Cuentas Asociadas al codigo: ${sessionScope.user.code}</h3>
                        <c:forEach var="ac" items="${asociations}">
                            <div class="mb-4 card shadow">
                                <div class="card-body ">
                                    <div class="row">
                                        <div class="col mb-0">
                                            <h2 class="card-text mb-4">${ac.thirdPartyAccount.customer.forename} </h2>
                                        </div>
                                        <div class="col-6 mb-0">
                                            <h4 class="card-text mb-4">No. cuenta: ${ac.thirdPartyAccount.code}</h4>
                                        </div>
                                        <div class="col mb-0">
                                            <h2 class="card-text mb-4">$. ${ac.thirdPartyAccount.amount}</h2>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-between align-items-center">
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </asociatedAccounts>
                </div>
                <div class="col-6 col-md-4">
                    <h3>Solicitudes pendientes</h3>
                    <c:forEach var="pr" items="${pendingRequests}">
                        <div class="card shadow">
                            <div class="card">
                                <h5 class="card-header">Solicitud: ${pr.id}</h5>
                                <div class="card-body">
                                    <h5 class="card-title">No. Cuenta: ${pr.account.code}</h5>
                                    <p class="card-text">Usuario: ${pr.account.customer.forename} (${pr.account.customer.code})</p>
                                    <a href="${pageContext.request.contextPath}/Customer?action=aceptRequest&reqId=${pr.id}"
                                       class="btn  btn-outline-success">Aceptar</a>
                                    <a href="${pageContext.request.contextPath}/Customer?action=rejectRequest&reqId=${pr.id}"
                                       class="btn  btn-outline-danger">Rechazar</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>       
                </div>
            </div>
        </div>
    </showReports>


</div>