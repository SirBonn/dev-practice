

<form class="formularioLogin needs-validation" id="formularioLogin" method="post" name="user-login" action="${pageContext.request.contextPath}/BankLogin"> 
    <div class="form-outline form-white mb-4">
        <input type="text" id="userCode" name="userCode"class="form-control form-control-lg" required />
        <label class="form-label" for="userCode">Codigo de Usuario</label>
    </div>

    <div class="form-outline form-white mb-4">
        <input type="password" id="userPassword" name="userPassword" class="form-control form-control-lg" required/>
        <label class="form-label" for="userPassword">Password</label>
    </div>

    <button class="btn btn-outline-light btn-lg px-5" type="submit" >Login</button>
    <input  class="btn btn-outline-light btn-lg px-5" type="button" value="Cancelar" id="Cancelar" class="btn btn-outline-dark" onclick='location.href = location = "index.jsp"'');>
</form>