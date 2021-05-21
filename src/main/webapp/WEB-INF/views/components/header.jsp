<header>
    <nav>
        <div class="nav-wrapper">
            <a href="#!" class="brand-logo">Internet Provider</a>
            <ul class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/InternetProvider/service">Services</a></li>
                <li> ${sessionScope.login != null ? sessionScope.login : ""}</li>
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
