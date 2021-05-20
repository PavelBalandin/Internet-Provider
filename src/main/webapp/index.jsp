<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Home page</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>

<div class="container">
    <div class="services_list">
        <table class="striped">
            <thead>
            <tr>
                <th>Service Name</th>
                <th>Tariffs</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="service" items="${serviceList}">
                <tr>
                    <td>${service.name}</td>
                    <td><a href="${pageContext.request.contextPath}/tariff?id=${service.id}">More</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/components/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
