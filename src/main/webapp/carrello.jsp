<!DOCTYPE html>
<%@ page import="model.Carrello" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ page import="model.ProdottiInCarrello" %>
<%@ page import="model.ProdottoDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carrello</title>
    <link type="text/css" rel="stylesheet" href="carrello.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<script src="gestisciquantita.js"></script>
<%
    Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
    ArrayList<ProdottiInCarrello> prodotti = carrello.getProdotti();
    ProdottoDAO prodottoDAO = new ProdottoDAO();
%>
<div class="CarrelloContainer">

    <div class="CartContainer">
        <%
            if (prodotti != null && !prodotti.isEmpty()) {
                for (int i = 0; i < prodotti.size(); i++) {
                    Prodotto pCheck = prodottoDAO.doRetrieveByid(prodotti.get(i).getIdProdotto());
                    if (pCheck == null) {
                        prodotti.remove(i);
                        i--; // fondamentale per non saltare elementi dopo una rimozione
                    }
                }

                if (!prodotti.isEmpty()) {
                    int numprodotti = 0;
                    float totale = 0;
                    for (int i = 0; i < prodotti.size(); i++) {
                        Prodotto prodotto = prodottoDAO.doRetrieveByid(prodotti.get(i).getIdProdotto());
                        numprodotti++;
                        totale += prodotti.get(i).getQuantita() * prodotto.getPrezzo();
        %>
        <div class="Cart-Items">
            <div class="image-box">
                <img src="./images/<%=prodotto.getId()%>/1.jpg" alt="Immagine del prodotto" height="120px"/>
            </div>
            <div class="about">
                <h1 class="title"><%=prodotto.getNome()%></h1>
            </div>
            <div class="counter" data-product="<%=prodotto.getId()%>">
                <div class="btn plus">+</div>
                <div class="count"><%=prodotti.get(i).getQuantita()%></div>
                <div class="btn minus">-</div>
            </div>
            <div class="prices">
                <div class="amount"><%=prodotto.getPrezzo()%></div>
            </div>
        </div>
        <% } %>
        <hr>
        <div class="checkout">
            <div class="total">
                <div>
                    <div class="Subtotal">Sub-Totale</div>
                    <div class="items"><%=numprodotti%> oggetti</div>
                </div>
                <div class="total-amount"><%=String.format("%.2f", totale).replace(",", ".")%><span class="euro">€</span></div>
            </div>
            <button id="submit-checkout" class="button">Checkout</button>

            <script>
                document.getElementById("submit-checkout").addEventListener("click", function () {
                    fetch("checkout.jsp")
                        .then(response => {
                            if (!response.ok) throw new Error("Errore durante il caricamento di checkout.jsp");
                            return response.text();
                        })
                        .then(html => {
                            document.getElementById("checkout-container").innerHTML = html;
                            const carrello = document.querySelector(".CarrelloContainer");
                            if (carrello) carrello.style.display = "none";
                        })
                        .catch(error => {
                            console.error("Errore nel caricamento:", error);
                        });
                });
            </script>
        </div>
        <%
        } else {
        %>
        <h1 style="text-align: center; font-family: 'Open Sans'">Non ci sono prodotti nel carrello</h1>
        <%
            }
        } else {
        %>
        <h1 style="text-align: center; font-family: 'Open Sans'">Non ci sono prodotti nel carrello</h1>
        <%
            }
        %>
    </div>
</div>
<div id="checkout-container"></div>
</body>
</html>
