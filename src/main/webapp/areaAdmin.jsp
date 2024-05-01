<%@ page import="model.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.ProdottoDAO" %><%--
 Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession().getAttribute("utente") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente.getRuolo() != 0) {
        response.setStatus(404);
        request.getRequestDispatcher("404.jsp").forward(request, response);
    }
    ArrayList<Prodotto> prodotti = new ProdottoDAO().doRetrieveAll();
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="admin.css">
    <title>Area Amministrazione</title>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<script src="admin.js"></script>
<script src="areautente.js"></script>
<div class="area-admin-container">
    <div class="menu">
        <ul class="menu-options">
            <li class="selected"><a href="#1">Gestione Ordini</a></li>
            <li><a>Gestione Prodotti</a></li>
            <li><a>Aggiungi prodotto</a></li>
        </ul>
        <div class="menu-item" id="gestione-ordini">
            <jsp:include page="gestioneordini.jsp"></jsp:include>
        </div>
        <div class="menu-item" id="gestione-prodotti" style="display: none">
            <% for (int i = 0; i < prodotti.size(); i++) {%>
            <div id="prodotto-<%= prodotti.get(i).getId()%>" class="product-line">
                <p class="title"><%= prodotti.get(i).getNome()%>
                </p>
                <div class="product-toolbox">
                    <%boolean disponibile = prodotti.get(i).getDisponibilita();%>
                    <select name="disponibilita">
                        <option value="true" <%if (disponibile) {%><%="selected"%><%}%>>true</option>
                        <option value="false" <%if (!disponibile) {%><%="selected"%><%}%>>false</option>
                    </select>
                    <input type="number" name="prezzo" value="<%= prodotti.get(i).getPrezzo()%>">
                    <button class="Aggiorna">Aggiorna</button>
                </div>
            </div>
            <%}%>
        </div>
        <div class="menu-item" id="aggiungi-prodotto" style="display: none">
            <jsp:include page="nuovoprodotto.jsp"></jsp:include>
        </div>
    </div>
</div>
</body>
</html>
