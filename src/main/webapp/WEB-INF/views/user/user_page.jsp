<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="tariff.name" var="tariffTariff"/>
<fmt:message key="tariff.description" var="tariffDescription"/>
<fmt:message key="tariff.price" var="tariffPrice"/>
<fmt:message key="tariff.currency" var="tariffCurrency"/>
<fmt:message key="tariff.days" var="tariffDays"/>
<fmt:message key="service.service" var="serviceService"/>

<fmt:message key="button.order" var="buttonOrder"/>
<fmt:message key="button.remove" var="buttonRemove"/>


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
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="tariff" items='${sessionScope.get("orderTariffList")}'>
                    <tr>
                        <td>${tariff.name}</td>
                        <td>${tariff.description}</td>
                        <td>${tariff.price} ${tariffCurrency} / ${tariff.duration} ${tariffDays}</td>
                        <td>${tariff.service.name}</td>
                        <td>
                            <form action="/InternetProvider/deleteTariffFromOrder">
                                <input name="id" value="${tariff.id}" hidden>
                                <button class="btn-small">${buttonRemove}</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="order-button">
            <form method="post" action="/InternetProvider/makeOrder">
                <button class="btn-small">${buttonOrder}</button>
            </form>
        </div>
        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
    </div>
</div>
</body>
</html>
