<%--
  Created by IntelliJ IDEA.
  User: caseybement
  Date: 11/29/21
  Time: 12:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movies Search</title>
    <%@include file="/includes/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="hero-unit">
        <h1>Search</h1>
    </div>

    <%@include file="/includes/navigation.jsp"%>

    <div class="container">
        <form action="Search" method="get">
            <label for ="title">Search by title:</label>
            <input type="text" name="title" id="title">
            <input type="submit">
        </form>
    </div>
    <%@include file="/includes/footer.jsp"%>
</div>
</body>
</html>
