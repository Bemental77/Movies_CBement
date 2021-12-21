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
    <title>Movies Populate DB</title>
    <%@include file="/includes/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="hero-unit">
        <h1>Populate the DB</h1>
    </div>

    <%@include file="/includes/navigation.jsp"%>
    <p>Warning: This action will over-write the existing database.</p>

    <div class="container">
        <form action="Populate" method="post">

            <input type="submit" value="Populate DB">
        </form>
    </div>
    <p>${message}</p>
    <%@include file="/includes/footer.jsp"%>
</div>
</body>
</html>
