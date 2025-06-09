<%@ page import="model.Utente" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Beerus</title>
</head>
<body>
<h1>Dati Utente</h1>
<% ArrayList<Utente> utenti = (ArrayList<Utente>) request.getAttribute("utenti"); %>
<% if (utenti != null) { %>
<table>
    <tr>
        <th>ID:</th>
        <th>Ruolo:</th>
        <th>Username:</th>
        <th>Email:</th>
        <th>Password:</th>
        <th>Nome:</th>
        <th>Cognome:</th>
        <th>Numero di Telefono:</th>
        <th>CAP:</th>
        <th>Civico:</th>
        <th>Via:</th>
    </tr>
    <% for (Utente utente : utenti) { %>
    <tr>
        <td><%= utente.getId() %></td>
        <td><%= utente.getRuolo() %></td>
        <td><%= utente.getUsername() %></td>
        <td><%= utente.getEmail() %></td>
        <td><%= utente.getPassword() %></td>
        <td><%= utente.getNome() %></td>
        <td><%= utente.getCognome() %></td>
        <td><%= utente.getNumTelefono() %></td>
        <td><%= utente.getCap() %></td>
        <td><%= utente.getCivico() %></td>
        <td><%= utente.getVia() %></td>
    </tr>
    <% } %>

</table>

<% } else { %>
<p>Utente non trovato.</p>
<% } %>
</body>
</html>
