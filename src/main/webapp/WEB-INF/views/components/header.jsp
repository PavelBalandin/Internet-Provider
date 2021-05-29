<header>
    <nav>
        <div class="nav-wrapper">
            <a href="${pageContext.request.contextPath}/InternetProvider/getServiceList" class="brand-logo">Internet Provider</a>
            <ul class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/InternetProvider/getServiceList">Services</a></li>
                <li><a href="/InternetProvider/${sessionScope.role.equals("USER") ? "getUserOrderPage" : "createUser"}">
                    ${sessionScope.login != null ? sessionScope.login : ""}
                </a>
                </li>
                <li>
                    <a class="dropdown-trigger"
                       href="${sessionScope.login != null ? "/InternetProvider/logout" : "/login.jsp"}"
                       data-target="dropdown1">
                        ${sessionScope.login != null ? "Log out" : "Log in"}
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
