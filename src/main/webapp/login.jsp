<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (request.getSession().getAttribute("utente") != null) {
    response.sendRedirect("areaUtente.jsp");
  }
%>
<html>
<head>
  <title>Login</title>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<script rel="script" src="login.js"></script>
<form id="login-form" method="post">
  <label class="login-label" for="email">Email :</label>
  <input class="input-field" id="email" name="email" aria-describedby="Inserimento email"  type="email" required>
  <label class="login-label" for="password">Password :</label>
  <input class="input-field" id="password" name="password"  aria-describedby="Inserimento Password" type="password" required>
  <p class="messaggio-errore" style="display: none">I dati inseriti non sono corretti !</p>
  <input class="submit-button" type="submit" value="Accedi">
  <p id="registrati"><a href="register.jsp">Registrati</a></p>
</form>
</body>
</html>

