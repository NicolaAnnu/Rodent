<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (request.getSession().getAttribute("utente") == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  Utente utente = (Utente) request.getSession().getAttribute("utente");
%>
<html>
<head>
  <title>Area Utente</title>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<script src="areautente.js"></script>
<div class="area-admin-container">
  <div class="menu">
    <ul class="menu-options">
      <li class="selected"><a href="#1">informazioni personali</a></li>
      <li><a>cambio password</a></li>
      <li><a>Preferiti</a></li>
      <li><a>Ordini</a></li>
      <li><a href="logOut">Logout</a></li>
    </ul>
    <div class="menu-item" id="informazioni-personali">
      <form id="modify-form" action="modifyUser" method="post">
        <input name="id" value="<%=utente.getId()%>" style="display: none">
        <label class="register-label" for="Username">Username :</label>
        <input class="input-field" id="Username" name="username" type="text" value="<%=utente.getUsername()%>"
               required>

        <label class="register-label" for="email">Email :</label>
        <input class="input-field" id="email" name="email" type="email" value="<%=utente.getEmail()%>" required>

        <label class="register-label" for="nome">Nome :</label>
        <input class="input-field" id="nome" name="nome" type="text" value="<%=utente.getNome()%>" required>

        <label class="register-label" for="cognome">Cognome :</label>
        <input class="input-field" id="cognome" name="cognome" type="text" value="<%=utente.getCognome()%>"
               required>

        <label class="register-label" for="telefono">Telefono :</label>
        <input class="input-field" id="telefono" name="telefono" type="text"
               value="<%=utente.getNumTelefono()%>" required pattern="^[0-9]{6,10}$">

        <label class="register-label" for="via">Via :</label>
        <input class="input-field" id="via" name="via" type="text" value="<%=utente.getVia()%>" required>

        <label class="register-label" for="civico">Civico :</label>
        <input class="input-field" id="civico" name="civico" type="text" pattern="[0-9]+"
               value="<%=utente.getCivico()%>" title="Inserisci un civico valido"
               required>

        <label class="register-label" for="cap">Cap :</label>
        <input class="input-field" id="cap" name="cap" type="text" value="<%=utente.getCap()%>" required
               pattern="\d{5}" title="Inserisci un cap valido">

        <input class="submit-button" type="submit">
      </form>
    </div>
    <div class="menu-item" id="cambio-password" style="display: none">
      <form id="new-pwdform" method="post" action="cambioPassword">
        <input name="id" value="<%=utente.getId()%>" style="display: none">
        <label class="register-label" for="password">Nuova password :</label>
        <input required class="input-field" id="password" name="password" type="text"
               placeholder="Nuova password ...">
        <input class="submit-button" type="submit">
      </form>
    </div>
    <div class="menu-item" id="preferiti">

    </div>
    <div class="menu-item" id="ordini">

    </div>

  </div>
</div>

</body>
</html>
