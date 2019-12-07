<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 10/8/19
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

<link href="https://unpkg.com/browse/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container">
    <p>${message}</p>

    <button class="btn btn-primary" onclick="window.location.href='views/employee-add.jsp'">Add Employee</button>
    <table border="1" class="table table-striped table-bordered">
        <tr class="thead-dark">
            <th>Name</th>
            <th>Date of birth</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${employees}" var="employee">

            <tr>
                <td>${employee.name}</td>
                <td>${employee.dob}</td>
                <td>${employee.department}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/EmployeeController?action=edit&id=${employee.id}">Edit</a>
                    |
                    <a href="${pageContext.request.contextPath}/EmployeeController?action=delete&id=${employee.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
