<%@ page import="model.OrdineDAO" %>
<%@ page import="model.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Ordine" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<script src="gestioneordini.js"></script>
<%
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente.getRuolo() != 0) {
        response.sendError(403, "Accesso negato");
        return;
    }
    OrdineDAO ordineDAO = new OrdineDAO();
    ArrayList<Ordine> ordini = ordineDAO.doRetrieveAll();
%>
<table class="tabella-ordini">
    <th>ID ORDINE</th>
    <th>ID UTENTE</th>
    <th>TELEFONO UTENTE</th>
    <th>STATO ORDINE</th>
    <th>VIA</th>
    <th>CIVICO</th>
    <th>CAP</th>
    <th>TOTALE</th>
    <th></th>
    <%for (Ordine o : ordini) {%>
    <tr class="riga">
        <td class="id-ordine"><%=o.getId()%>
        </td>
        <td><%=o.getIdUtente()%>
        </td>
        <td><%=o.getNumTelefono()%>
        </td>
        <td>
            <select name="status">
                <option value="Annullato" <%if (o.getStato().equalsIgnoreCase("Annullato")) {%><%="selected"%><%}%> >
                    annullato
                </option>
                <option value="Inviato" <%if (o.getStato().equalsIgnoreCase("inviato")) {%><%="selected"%><%}%> >
                    inviato
                </option>
                <option value="Spedito" <%if (o.getStato().equalsIgnoreCase("spedito")) {%><%="selected"%><%}%> >
                    spedito
                </option>
                <option value="Consegnato" <%if (o.getStato().equalsIgnoreCase("consegnato")) {%><%="selected"%><%}%> >
                    consegnato
                </option>
            </select>
        </td>
        <td><%=o.getVia()%>
        </td>
        <td><%=o.getCivico()%>
        </td>
        <td><%=o.getCap()%>
        </td>
        <td><%=o.getTotale()%> <span class="euro" style="text-align: end">€</span></td>
        <td>
            <button class="aggiorna-ordine-btn" aria-describedby="Aggiornamento di un Ordine">Aggiorna</button>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>

