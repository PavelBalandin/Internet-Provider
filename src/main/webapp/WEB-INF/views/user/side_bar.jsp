<fmt:message key="user.sidebar.orders" var="order"/>
<fmt:message key="user.sidebar.add.funds" var="addFunds"/>
<fmt:message key="user.sidebar.payments" var="payments"/>
<fmt:message key="user.sidebar.tariffs" var="tariffs"/>

<nav class="admin-side-bar">
    <ul class="right hide-on-med-and-down">
        <li><a href="${pageContext.request.contextPath}/InternetProvider/getUserOrderPage">${order}</a></li>
        <li><a href="${pageContext.request.contextPath}/InternetProvider/getAddFundsPage">${addFunds}</a></li>
        <li><a href="${pageContext.request.contextPath}/InternetProvider/getUserPaymentListPage">${payments}</a></li>
        <li><a href="${pageContext.request.contextPath}/InternetProvider/getUserTariffListPage">${tariffs}</a></li>
    </ul>
</nav>
