<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rodent</title>

</head>
<body>
<%
    request.setAttribute("active", "0");
    request.setAttribute("origin", "index.jsp");
%>
<jsp:include page="navbar.jsp"></jsp:include>
<jsp:include page="banner.jsp"></jsp:include>
<jsp:include page="productContainer.jsp"></jsp:include>
</body>
</html>
