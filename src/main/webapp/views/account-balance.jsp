<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 10/14/19
  Time: 1:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check balance</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>


<div class="col-md-4">
    <h2> hello ${loginedUser.user}</h2>
    <h3>Your balance current: ${balanceCurrent}</h3>
</div>
<div class="col-md-4">
    <a href="${pageContext.request.contextPath}/LoginController">
        <button class="btn btn-primary">Back to home</button>
    </a>
</div>
</body>
</html>
