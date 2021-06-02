<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

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
        <table>
            <thead>
            <tr>
                <th>Payment</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="payment" items="${paymentList}">
                <tr>
                    <td>${payment.payment}</td>
                    <td><custom:LocalDateTag date="${payment.dateTime}"/></td>
                </tr>
            </c:forEach>
            </tbody>
            <tr>
                <td><b>Total:</b></td>
                <td><b>${total}</b></td>
            </tr>
        </table>
        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
    </div>
</div>
</body>
</html>
