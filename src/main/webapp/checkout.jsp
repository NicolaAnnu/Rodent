<%@ page import="model.Utente" %>
<html>
<body>
<script src="./checkout.js"></script>

<% Utente utente = (Utente) request.getSession().getAttribute("utente");%>
<%if (utente == null) {%>
<div style="display: flex; align-items: center; justify-content: center; flex-direction: column">
    <h1 style="color: white; font-weight: bold;">Devi essere registrato per poter effettuare un ordine</h1>
    <p style="color: white; font-weight: bold;">Vai al <a style="color: #ffa31a; font-weight: bold;" href="login.jsp">login</a>
    </p>
</div>
<%} else {%>
<form id="checkout-form"
action="checkout"
method="POST">
    <label class="register-label" for="email">Email :</label>
    <input class="input-field" id="email" name="email" aria-describedby="Inserimento Email" type="email" required value="<%=utente.getEmail()%>">

    <label class="register-label" for="nome">Nome :</label>
    <input class="input-field" id="nome" name="nome" aria-describedby="Inserimento Nome" type="text" required value="<%=utente.getNome()%>">

    <label class="register-label" for="cognome">Cognome :</label>
    <input class="input-field" id="cognome" name="cognome" aria-describedby="Inserimento Cognome" type="text" required value="<%=utente.getCognome()%>">

    <label class="register-label" for="telefono">Telefono :</label>
    <input class="input-field" id="telefono" name="telefono" aria-describedby="Inserimento telefono"  type="text" required value="<%=utente.getNumTelefono()%>"
           pattern="^[0-9]{6,10}$">

    <label class="register-label" for="via">Via :</label>
    <input class="input-field" id="via" name="via" aria-describedby="Inserimento Via"  type="text" required value="<%=utente.getVia()%>">

    <label class="register-label" for="civico">Civico :</label>
    <input class="input-field" id="civico" name="civico" aria-describedby="Inserimento Civico" type="text" required pattern="[0-9]+"
           value="<%=utente.getCivico()%>">

    <label class="register-label" for="cap">Cap :</label>
    <input class="input-field" id="cap" name="cap" aria-describedby="Inserimento CAP"  type="text" required value="<%=utente.getCap()%>" pattern="\d{5}"
           title="Inserisci un cap valido">

    <fieldset>
        <legend class="register-label">Metodo di pagamento</legend>
        <label class="register-label" for="carta-di-credito"> Carta di credito</label>
        <input class="payment-input" id="carta-di-credito" name="metodo-di-pagamento" aria-describedby="Inserimento Carta di Credito" type="radio"
               value="Carta di credito" checked>
        <label class="register-label" for="paypal"> PayPal </label>
        <input class="payment-input" id="paypal" name="metodo-di-pagamento" type="radio" value="PayPal">
        <label class="register-label" for="bonifico"> Bonifico </label>
        <input class="payment-input" id="bonifico" name="metodo-di-pagamento" aria-describedby="Effettuare metodo di Pagamento" type="radio" value="Bonifico">
    </fieldset>
    <input id="submit-form-button" class="submit-button" type="submit" >
</form>
<script>
    document.getElementById("submit-form-button").addEventListener("click",  (event) =>{
        console.log(event)
    })
</script>
<%}%>
</body>
</html>
