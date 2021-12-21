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
    <title>Movies: Add new movie</title>
    <%@include file="/includes/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="hero-unit">
        <h1>Add New Movie</h1>
    </div>

    <%@include file="/includes/navigation.jsp"%>

    <p>${message}</p>
    <div class="container">
        <form action="AddNewMovie" method="post">
            <%--@declare id="thumbnail"--%><label for ="title">Title:</label>
            <input type="text" name="title" id="title">

            <label for ="director">Director:</label>
            <input type="text" name="director" id="director">

            <label for ="lengthInMinutes">Length In Minutes:</label>
            <input type="text" name="lengthInMinutes" id="lengthInMinutes">

            <label for ="IMDB">IMDB Link:</label>
            <input type="text" name="IMDB" id="IMDB">

            <label for ="thumbnail">Thumbnail:</label>
            <input type="text" name="thumbnail" id="thumnail">

            <input type="submit">
        </form>
    </div>
    <%@include file="/includes/footer.jsp"%>
</div>
</body>
</html>
