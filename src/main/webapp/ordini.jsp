<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Utente" %>
<%@ page import="model.OrdineDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Ordine" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null) {
        response.setStatus(404);
        request.getRequestDispatcher("404.jsp").forward(request, response);
    }
    OrdineDAO ordineDAO = new OrdineDAO();
    ArrayList<Ordine> ordini = ordineDAO.doRetrieveByUser(utente.getId());
%>
<body>
<table class="tabella-ordini">
    <th>ID ORDINE</th>
    <th>STATO ORDINE</th>
    <th>VIA</th>
    <th>CIVICO</th>
    <th>CAP</th>
    <th>TOTALE</th>
    <%for (Ordine o : ordini) {%>
    <tr class="">
        <td><%=o.getId()%>
        </td>
        <td><%=o.getStato()%>
        </td>
        <td><%=o.getVia()%>
        </td>
        <td><%=o.getCivico()%>
        </td>
        <td><%=o.getCap()%>
        </td>
        <td><%=o.getTotale()%> <span class="euro" style="text-align: end">€</span></td>
    </tr>
    <%}%>
</table>
</body>
</html>

