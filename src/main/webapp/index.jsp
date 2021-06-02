<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="service.service" var="serviceService"/>
<fmt:message key="service.tariff" var="serviceTariff"/>
<fmt:message key="service.more" var="serviceMore"/>
<fmt:message key="service.download" var="serviceDownload"/>

<html>
<head>
    <title>Home page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>

<div class="container">
    <div class="services_list">
        <c:if test="${serviceList.size() > 0}">
            <table class="striped">
                <thead>
                <tr>
                    <th>${serviceService}</th>
                    <th>${serviceTariff}</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="service" items="${serviceList}">
                    <tr>
                        <td>${service.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/InternetProvider/getTariffListByService?id=${service.id}">${serviceMore}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="plan_load">
                <a href="${pageContext.request.contextPath}/InternetProvider/downloadTariffList">${serviceDownload}</a>
            </div>
        </c:if>
    </div>
</div>

<%@ include file="/WEB-INF/views/components/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
