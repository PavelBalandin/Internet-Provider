<fmt:message key="header.services" var="headerService"/>
<fmt:message key="header.login" var="headerLogin"/>
<fmt:message key="header.logout" var="headerLogout"/>

<header>
    <nav>
        <div class="nav-wrapper">
            <a href="${pageContext.request.contextPath}/InternetProvider/getServiceList" class="brand-logo">Internet
                Provider</a>
            <ul class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/InternetProvider/changeLocale?lang=ua">UA</a></li>
                <li><a href="${pageContext.request.contextPath}/InternetProvider/changeLocale?lang=en">EN</a></li>
                <li><a href="${pageContext.request.contextPath}/InternetProvider/getServiceList">${headerService}</a>
                </li>
                <li><a href="/InternetProvider/${sessionScope.role.equals("USER") ? "getUserOrderPage" : "createUser"}">
                    ${sessionScope.login != null ? sessionScope.login : ""}
                </a>
                </li>
                <li>
                    <a class="dropdown-trigger"
                       href="${sessionScope.login != null ? "/InternetProvider/logout" : "/login.jsp"}"
                       data-target="dropdown1">
                        ${sessionScope.login != null ? headerLogout : headerLogin}
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
