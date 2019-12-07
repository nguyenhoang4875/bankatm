<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 10/11/19
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h4 align="centre">Hello: ${loginedUser.user}</h4>
    <h2>Please choose service</h2>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/CustomerController?action=balance">Balance</a>
    </div>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/CustomerController?action=transfer">Transfer Money</a>
    </div>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/CustomerController?action=withdraw">Withdraw</a>
    </div>
</div>

</body>
</html>

