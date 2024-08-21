<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registrazione Utente</title>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<form id="register-form" action="signup" method="post">

  <label class="register-label" for="Username">Username :</label>
  <input required class="input-field" id="Username" name="username" aria-describedby="" type="text">

  <label class="register-label" for="email">Email :</label>
  <input required class="input-field" id="email" name="email" aria-describedby="" type="email">

  <label class="register-label" for="password">Password :</label>
  <input required class="input-field" id="password" name="password" aria-describedby=""  type="password">

  <label class="register-label" for="nome">Nome :</label>
  <input required class="input-field" id="nome" name="nome" aria-describedby=""  type="text">

  <label class="register-label" for="cognome">Cognome :</label>
  <input required class="input-field" id="cognome" name="cognome" aria-describedby="" type="text">

  <label class="register-label" for="telefono">Telefono :</label>
  <input required class="input-field" id="telefono" name="telefono" aria-describedby="" type="text" pattern="^[0-9]{6,10}$">

  <label class="register-label" for="via">Via :</label>
  <input required class="input-field" id="via" name="via" aria-describedby=""  type="text">

  <label class="register-label" for="civico">Civico :</label>
  <input required pattern="[0-9]+" class="input-field" id="civico" name="civico" aria-describedby="" type="text">

  <label class="register-label" for="cap">Cap :</label>
  <input required class="input-field" id="cap" name="cap" aria-describedby="" type="text" pattern="\d{5}" title="Inserisci un cap valido">

  <input class="submit-button" type="submit" aria-describedby="Submit Button">

</form>
</body>
</html>

