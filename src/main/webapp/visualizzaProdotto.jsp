<%@ page import="model.ProdottoDAO" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.PreferitiDAO" %>
<%@ page import="model.Utente" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Utente utente = (Utente) request.getSession().getAttribute("utente");
  boolean isPreferito = false;
  int id = 0;
  try {
    id = Integer.parseInt((String) request.getParameter("id"));
  } catch (Exception e) {
    response.setStatus(400);
    request.getRequestDispatcher("400.jsp").forward(request, response);
  }
  if (utente != null) {
    PreferitiDAO preferitiDAO = new PreferitiDAO();
    isPreferito = preferitiDAO.isPreferito(utente.getId(), id);
  }
  ProdottoDAO prodottoDAO = new ProdottoDAO();
  Prodotto p = prodottoDAO.doRetrieveByid(id);
  if (p == null) {
    response.setStatus(404);
    request.getRequestDispatcher("404.jsp").forward(request, response);
  }
%>

<html>
<head>
  <title>Rodent | <%=p.getNome()%>
  </title>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<script src="slideshow.js"></script>
<script src="cart.js"></script>
<div class="product">
  <div class="slideshow-container">
    <%for (int i = 1; i <= 4; i++) {%>
    <div class="slide">
      <img src="./images/<%=p.getId()%>/<%=i%>.jpg"  class="p-image" alt="Image"
           onerror="this.parentNode.remove()">
      <button class="prev-button"><img src="./icons/left.svg" alt="Indietro" width="40px" height="40px"></button>
      <button class="next-button"><img src="./icons/right.svg" alt="Avanti" width="40px" height="40px"></button>
    </div>
    <%}%>
  </div>
  <div class="product-details">
    <h1 class="product-title"><%=p.getNome()%>
    </h1>
    <p class="description">
      <%=p.getDescrizione()%>
    </p>
    <div class="price-container">
            <span class="product-price"><%=p.getPrezzo()%>
            </span>
      <span class="product-price euro">€</span>
    </div>
    <br>
    <div class="atc-container">
      <br><br>
      <button class="heart-button no-background" aria-label="Aggiungi ai Preferiti">
        <img class="hearth" src="icons/heartIcon.svg" alt="" style="<% if (isPreferito) { %><%= "display:none" %><% } %>">
        <img class="hearth" src="icons/heartIconClicked.svg" alt="Elemento aggiunto ai preferiti"
             style="<% if (!isPreferito) { %><%= "display:none" %><% } %>">
      </button>

      <br><br>
      <label id="qlabel" for="quantita">Quantita :</label>
      <select id="quantita" type="number" name="quantita">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
      </select><br><br>
      <button class="product-buy-button" data-product="<%=p.getId()%>">Aggiungi al carrello</button>
    </div>
  </div>
</div>
</body>
</html>

