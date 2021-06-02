<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="tariff.name" var="tariffTariff"/>
<fmt:message key="tariff.description" var="tariffDescription"/>
<fmt:message key="tariff.price" var="tariffPrice"/>
<fmt:message key="tariff.currency" var="tariffCurrency"/>
<fmt:message key="tariff.days" var="tariffDays"/>
<fmt:message key="tariff.start" var="tariffStart"/>
<fmt:message key="tariff.end" var="tariffEnd"/>
<fmt:message key="service.service" var="serviceService"/>

<html>
<head>
    <title>User page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<div class="content">
    <%@ include file="/WEB-INF/views/user/side_bar.jsp" %>
    <div class="main-content">
        <div class="tariff_list">
            <table class="striped">
                <thead>
                <tr>
                    <th>${tariffTariff}</th>
                    <th>${tariffDescription}</th>
                    <th>${tariffPrice}</th>
                    <th>${serviceService}</th>
                    <th>${tariffStart}</th>
                    <th>${tariffEnd}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tariff" items='${tariffList}'>
                    <tr>
                        <td>${tariff.name}</td>
                        <td>${tariff.description}</td>
                        <td>${tariff.price} ${tariffCurrency} / ${tariff.duration} ${tariffDays}</td>
                        <td>${tariff.service.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
    </div>
</div>
</body>
</html>
