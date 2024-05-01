<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rodent | Accessori</title>
</head>
<body>
<%
    request.setAttribute("active", "4");
    request.setAttribute("origin", "accessori.jsp");
%>
<jsp:include page="navbar.jsp"></jsp:include>
<jsp:include page="banner.jsp"></jsp:include>
<jsp:include page="productContainer.jsp"></jsp:include>
</body>
</html>
