<%--
  Created by IntelliJ IDEA.
  User: caseybement
  Date: 11/20/21
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Casey Bement Movies List</title>
    <%@include file="/includes/header.jsp"%>
</head>
<body>
<div class="container">
    <div class="hero-unit">
        <h1>Movies List</h1>
    </div>

    <%@include file="/includes/navigation.jsp"%>

    <div class="container">
        <c:choose>
            <c:when test="${empty movies}">
                <p>Sorry the list of movies is empty</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="movie" items="${movies}">
                    <h2>${movie.title}</h2>
                    <p>${movie.title} ${movie.director} is ${movie.lengthInMinutes} minutes long.</p>
                    <p><img src="${movie.thumbnail}"> <a href="${movie.IMDB}"> IMDB Link for ${movie.title}</a></p>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <%@include file="/includes/footer.jsp"%>

</div>

</body>
</html>
