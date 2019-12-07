<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 10/9/19
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">

<div class="container">
    <h1> Employee Directory</h1>
    <hr/>
    <div class="container">
        <div class="col-md-4">
            <form action="${pageContext.request.contextPath}/EmployeeController" method="post">
                <div class="form-group">

                    <input type="text" name="name" value="${employee.name}" placeholder="Enter Name"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <input type="date" name="dob" value="${employee.dob}" placeholder="Enter Date of Birth"
                           class="form-control"/>
                </div>
                <div class="form-group">
                    <input type="text" name="department" value="${employee.department}" placeholder="Enter Department"
                           class="form-control"/>
                </div>
                <input type="hidden" value="${employee.id}" name="id">
                <button class="btn btn-primary" type="submit"> Save Employee</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
