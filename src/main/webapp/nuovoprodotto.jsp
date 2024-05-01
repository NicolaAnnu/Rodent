<%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Carica immagini</title>
</head>
<body>
<h1 style="color: white; text-align: center">Nuovo prodotto </h1>

<form id="aggiungi-prodotto" action="nuovoProdotto" method="post" enctype="multipart/form-data">

  <label class="new-product-label" for="Nome">Nome :</label>
  <input required class="input-field" id="Nome" name="nome" type="text">

  <label class="new-product-label" for="prezzo">Prezzo :</label>
  <input required class="input-field" id="prezzo" name="prezzo" type="text" pattern="\d+"
         title="Inserisci un prezzo valido">

  <label class="new-product-label" for="descrizione">Descrizione :</label>
  <input class="input-field" id="descrizione" name="descrizione" type="text">

  <label class="new-product-label" for="disponibilita">Disponibile :</label>
  <select id="disponibilita" name="disponibilita">
    <option value="true">Vero</option>
    <option value="false">Falso</option>
  </select><br><br>

  <label class="new-product-label" for="categoria">Categoria :</label>
  <select id="categoria" name="categoria">
    <option value="1">Gabbie</option>
    <option value="2">Igiene</option>
    <option value="3">Mangime</option>
    <option value="4">Accessori</option>
  </select><br><br>

  <label class="new-product-label" for="image">Carica immagini:</label>
  <input type="file" id="image" name="image" multiple="true" required><br><br>

  <input class="submit-button" type="submit">
</form>
</body>
</html>


