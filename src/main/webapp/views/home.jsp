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
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="col-md-4">
        <div class="form-group">
            <h2 alsign="centre">Hello: ${loginedUser.user}</h2>
            <h2>Please choose service</h2>
        </div>
        <div class="form-group">
            <a href="${pageContext.request.contextPath}/CustomerController?action=balance">
                <button class="btn btn-primary">Balance</button>
            </a>
        </div>
        <div class="form-group">
            <a href="${pageContext.request.contextPath}/CustomerController?action=transfer">
                <button class="btn btn-primary">Transfer Money</button>
            </a>
        </div>
        <div class="form-group">
            <a href="${pageContext.request.contextPath}/CustomerController?action=transferThread">
                <button class="btn btn-primary">Transfer Money Use Thread</button>
            </a>
        </div>
        <div class="form-group">
            <a href="${pageContext.request.contextPath}/CustomerController?action=withdraw">
                <button class="btn btn-primary">Withdraw</button>
            </a>
        </div>
        <div class="form-group">
            <a href="${pageContext.request.contextPath}/CustomerController?action=deposit">
                <button class="btn btn-primary">Deposit</button>
            </a>
        </div>
    </div>
</div>
</div>
</body>
</html>

