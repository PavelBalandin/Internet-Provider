<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<html>
<head>
    <title>Home page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
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
                    <td>
                        <a href="${pageContext.request.contextPath}/InternetProvider/getTariffListByService?id=${service.id}">More</a>
                    </td>
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
