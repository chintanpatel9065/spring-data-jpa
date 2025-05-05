<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chintanpatel
  Date: 05/05/25
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>'>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-grid.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-utilities.rtl.css"/>">
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>
<div class="container-fluid mt-3">
    <c:url value="/departments/listDepartment" var="departmentUrl"/>
    <a href="${departmentUrl}" class="link-primary fs-4">Department</a>
    &nbsp;|&nbsp;
    <c:url value="/employees/listEmployee" var="employeeUrl"/>
    <a href="${employeeUrl}" class="link-primary fs-4">Employee</a>
</div>
</body>
</html>
