<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: broth
  Date: 29/04/2024
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Rodent | Igiene</title>
</head>
<body>
<%
  request.setAttribute("active", "2");
  request.setAttribute("origin", "igiene.jsp");
%>

<jsp:include page="navbar.jsp"></jsp:include>
<jsp:include page="banner.jsp"></jsp:include>
<jsp:include page="productContainer.jsp"></jsp:include>
</body>
</html>
