<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.02.2020
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<%-- class="row col-md-6"--%>

<div>
    <table class="table table-striped table-bordered table-sm">
        <H3>Фильмы</H3>
        <tr>
            <th>Название фильма</th>
            <th>Дата релиза</th>
            <th>Жанр</th>
            <th>Режисёр</th>
            <th>Дата рождения</th>
        </tr>

        <c:forEach items="${page.views}" var="film">
            <tr>
                <td>${film.filmName}</td>
                <td>${film.filmReleaseDate}</td>
                <td>${film.filmGenre}</td>
                <td>${film.getDirectorFullName()}</td>
                <td>${film.directorBirthDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<nav aria-label="...">
    <ul class="pagination">
        <c:if test="${page.currentPage > 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/main/?currentPage=${page.currentPage-1}&search=${page.searchRequest}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${page.maxPages}" var="item">
            <c:choose>
                <c:when test="${page.currentPage == item}">
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" >${item}<span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/main/?currentPage=${item}&search=${page.searchRequest}">${item}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${page.currentPage < page.maxPages}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/main/?currentPage=${page.currentPage+1}&search=${page.searchRequest}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>
