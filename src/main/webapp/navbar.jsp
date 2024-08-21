<%@ page import="javax.sound.midi.SysexMessage" %>
<%@ page import="model.Carrello" %>
<%@ page import="model.Utente" %><%--
<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Boolean checkUser = (request.getSession().getAttribute("utente") != null);

    Boolean checkCarrello = (request.getSession().getAttribute("carrello") != null);
    if (!checkCarrello) {
        Carrello carrello = new Carrello();
        request.getSession().setAttribute("carrello", carrello);
    }
%>
<html>
<head>
    <link rel="icon" href="favicon.ico" aria-describedby="Icona per poter aggiungere prodotto nei preferiti" type="image/x-icon">
    <link rel="shortcut icon" href="favicon.ico" aria-describedby="piccola icona preferiti" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link type="text/css" rel="stylesheet" href="landingstyle.css"></link>
    <script src="script.js"></script>
    <script src="dropdown.js"></script>
</head>
<body>
<button class="dropdown-button" aria-describedby="">+</button>
<nav class="large-navigation">
    <div id="search-profile-cart">
        <div class="wrapper1">
            <div class="search-container">
                <input type="search" placeholder="Search.." class="search-input">
                <button class="search-button" aria-label="Bottone di ricerca">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
            </div>
        </div>
    <div class="wrapper2">
            <a href="index.jsp"> <img src="icons/logo.svg" alt="Logo del Sito Web" width="100px" height="120px"> </a>
        </div>
        <div class="wrapper3">
            <a id="areaAdmin"
               href="areaAdmin.jsp" <%if (checkUser == false || ((Utente) request.getSession().getAttribute("utente")).getRuolo() != 0) {%><%="style='display: none' "%><%}%>><img
                    src="icons/gear.svg" alt="icona Impostazioni"
                    width="45px"
                    height="50px"></a>
            <a href="carrello.jsp"><img src="icons/cart.svg" alt="Icona carte di credito" width="50px" height="50px"></a>
            <a id="doLogin" href="login.jsp" <%if (checkUser == true) {%><%="style='display: none' "%><%}%>>Login</a>
            <a id="areaUtente"
               href="areaUtente.jsp" <%if (checkUser == false) {%><%="style='display: none' "%><%}%>><img
                    src="icons/profile.svg" alt="icona del profilo"
                    width="45px"
                    height="50px"></a>
        </div>
    </div>
    <div id="categorie">
        <ul>
            <li id="1" class="<% if((String) request.getAttribute("active") == "1"){%><%="active"%><%}%>"><a
                    href="./gabbie.jsp">Gabbie e Recinti</a></li>
            <li id="2" class="<% if((String) request.getAttribute("active") == "2"){%><%="active"%><%}%>"><a
                    href="./igiene.jsp">Igiene</a></li>
            <li id="0" class="<% if((String) request.getAttribute("active") == "0"){%><%="active"%><%}%>"><a
                    href="./index.jsp">Tutti i prodotti</a></li>
            <li id="3" class="<% if((String) request.getAttribute("active") == "3"){%><%="active"%><%}%>"><a
                    href="mangime.jsp">Fieno e Mangime</a></li>
            <li id="4" class="<% if((String) request.getAttribute("active") == "4"){%><%="active"%><%}%>"><a
                    href="./accessori.jsp">Accessori</a></li>
        </ul>
    </div>
</nav>
<nav class="small-navigation" style="display: none;">
    <ul class="dropdown">
        <li><a href="index.jsp"> <img src="icons/logo.svg" width="100px" height="120px"> </a></li>
        <li><a id="areaAdmin-small"
               href="areaAdmin.jsp" <%if (checkUser == false || ((Utente) request.getSession().getAttribute("utente")).getRuolo() != 0) {%><%="style='display: none' "%><%}%>>Area
            Admin</a></li>
        <li><a id="carrello" href="carrello.jsp">Carrello</a></li>
        <li><a id="doLogin-small"
               href="login.jsp" <%if (checkUser == true) {%><%="style='display: none' "%><%}%>>Login</a></li>
        <li><a id="areaUtente-small"
               href="areaUtente.jsp" <%if (checkUser == false) {%><%="style='display: none' "%><%}%>>Area utente</a>
        </li>
        <li id="1" class="<% if((String) request.getAttribute("active") == "1"){%><%="active"%><%}%>"><a
                href="./gabbie.jsp">Gabbie e Recinti</a></li>
        <li id="2" class="<% if((String) request.getAttribute("active") == "2"){%><%="active"%><%}%>"><a
                href="./igiene.jsp">Igiene</a></li>
        <li id="0" class="<% if((String) request.getAttribute("active") == "0"){%><%="active"%><%}%>"><a
                href="./index.jsp">Tutti i prodotti</a></li>
        <li id="3" class="<% if((String) request.getAttribute("active") == "3"){%><%="active"%><%}%>"><a
                href="./mangime.jsp">Fieno e Mangime</a></li>
        <li id="4" class="<% if((String) request.getAttribute("active") == "4"){%><%="active"%><%}%>"><a
                href="./accessori.jsp">Accessori</a></li>

    </ul>
</nav>
</body>
</html>
