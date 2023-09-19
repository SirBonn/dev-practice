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



    <div class="">
        <legitBody>
            <div class="modal-content mb-4">
                <div class="modal-header">
                    <h5 class="modal-title" id="transferModalTIttle">Acreditar a cuenta</h5>
                </div>

                <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin"
                      action="${pageContext.request.contextPath}/Employee?action=acTransfer">
                    <div class="modal-body">
                        <div class="row mb-4">
                            <div class="col-md-6 mb-4">
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="amount">Cantidad:</label>
                                    <input type="number" id="amount" name="amount" class="form-control" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-4 ">
                            <div class="form-outline">
                                <label for="acAccount" class="form-label">Cuenta a acreditar</label>
                                <select type="button" class="btn form-outline form-control dropdown-toggle" data-toggle="dropdown" id="acAccount" name="acAccount" required>
                                    <option value="-1">- Selecciona una cuenta -</option>
                                    <c:forEach var="c" items="${accounts}">
                                        <option value="${c.code}">${c.code} - Q. ${c.amount}</option>
                                    </c:forEach>    
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary"> Agregar </button>
                        </div>
                    </div>
                </form>
            </div>
            <hr class="dropdown-divider">

            <div class="modal-content mb-4">
                <div class="modal-header">
                    <h5 class="modal-title" id="transferModalTIttle">Debitar a cuenta</h5>
                </div>

                <form class="formularioSignin needs-validation" id="formularioSignin" method="post" name="user-singin"
                      action="${pageContext.request.contextPath}/Employee?action=debTransfer">
                    <div class="modal-body">
                        <div class="row mb-4">
                            <div class="col-md-6 mb-4">
                                <div class="form-outline mb-4">
                                    <label class="form-label" for="amount">Cantidad:</label>
                                    <input type="number" id="amount" name="amount" class="form-control" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-4 ">
                            <div class="form-outline">
                                <label for="debAccount" class="form-label">Cuenta a acreditar</label>
                                <select type="button" class="btn form-outline form-control dropdown-toggle" data-toggle="dropdown" id="debAccount" name="debAccount" required>
                                    <option value="-1">- Selecciona una cuenta -</option>
                                    <c:forEach var="c" items="${accounts}">
                                        <option value="${c.code}">${c.code} - Q. ${c.amount}</option>
                                    </c:forEach>    
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary"> Agregar </button>
                        </div>
                    </div>
                </form>
            </div>
        </legitBody>


    </div>