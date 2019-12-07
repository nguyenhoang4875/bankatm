<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 10/11/19
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="col-md-4">
        <form action="${pageContext.request.contextPath}/LoginController" method="post">
            <div class="form-group">
                <label><b>Username</b></label>

                <input type="text" name="username" required
                       class="form-control"/>
            </div>

            <div class="form-group">
                <label><b>Password</b></label>
                <input type="password" name="password" required
                       class="form-control"/>
            </div>
         <p>${message}</p>

            <button class="btn btn-primary" type="submit">Login</button>
        </form>
    </div>
</div>

</body>
</html>
