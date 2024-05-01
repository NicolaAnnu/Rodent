<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rodent | Gabbie e recinti</title>
</head>
<body>
<%
  request.setAttribute("active", "1");
  request.setAttribute("origin", "gabbie.jsp");
%>
<jsp:include page="navbar.jsp"></jsp:include>
<jsp:include page="banner.jsp"></jsp:include>
<jsp:include page="productContainer.jsp"></jsp:include>
</div>
</body>
</html>