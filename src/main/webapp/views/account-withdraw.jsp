<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 10/14/19
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>With draw</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<hr/>
<div class="container">
    <div class="col-md-4">
        <p>${message}</p>
        <form action="${pageContext.request.contextPath}/CustomerController" method="post">
            <div class="form-group">
                <input type="text" name="amount" placeholder="amount money"
                       class="form-control"/>
            </div>
            <%
                System.out.println("todo is:");
                System.out.println(request.getAttribute("todo"));
                if (request.getAttribute("todo").equals("withdraw")) {
            %>
            <button class="btn btn-primary" type="submit" name="Action" value="withdraw">Withdraw</button>
            <% } else {
            %>
            <button class="btn btn-primary" type="submit" name="Action" value="deposit">Deposit</button>
            <% } %>

        </form>
    </div>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/LoginController">
            <button class="btn btn-primary" >Back to home</button>
        </a>
    </div>
</div>
</body>
</html>
