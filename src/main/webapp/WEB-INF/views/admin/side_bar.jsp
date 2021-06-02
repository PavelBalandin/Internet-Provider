<fmt:message key="admin.sidebar.add.user" var="addUser"/>
<fmt:message key="admin.sidebar.update.user" var="updateUser"/>
<fmt:message key="admin.sidebar.edit.tariff" var="editTariff"/>

<nav class="admin-side-bar">
    <ul class="right hide-on-med-and-down">
        <li><a href="${pageContext.request.contextPath}/InternetProvider/createUser">${addUser}</a></li>
        <li><a href="${pageContext.request.contextPath}/InternetProvider/updateUser">${updateUser}</a></li>
        <li><a href="${pageContext.request.contextPath}/InternetProvider/getTariffList">${editTariff}</a></li>
    </ul>
</nav>
