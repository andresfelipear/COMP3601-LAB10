<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>COMP3601-Lab10</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <jsp:include page="Employees.jsp"/>
    <div class="crudEmployees">
        <jsp:include page="addEmployees.jsp"/>
        <jsp:include page="findEmployees.jsp"/>
        <jsp:include page="deleteEmployees.jsp"/>
    </div>
</body>
</html>
