<%@ taglib prefix="xlink" uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs" %>
<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if (request.getAttribute("prodotti") == null) {
    RequestDispatcher dispatcher = request.getRequestDispatcher("./getProdotti");
    dispatcher.forward(request, response);
    return;
  }
%>
<div class="card-container">
  <% for (Prodotto p : (ArrayList<Prodotto>) request.getAttribute("prodotti")) {%>
  <div class="product-card" id="<%=p.getId()%>">
    <a href="visualizzaProdotto.jsp?id=<%=p.getId()%>">
      <img src="./images/<%=p.getId()%>/1.jpg">
      <h4><%=p.getNome()%>
    </a>
    </h4>
    <div class="internal-div">
      <span><%=String.format("%.2f", p.getPrezzo()).replace(",", ".")%>€</span>
      <button class="heart-button">
        <img src="icons/heartIcon.svg">
        <img src="icons/heartIconClicked.svg" style="display: none">
      </button>
    </div>
  </div>
  <%}%>
</div>

