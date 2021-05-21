<header>
    <ul id="dropdown1" class="dropdown-content">
        <li><a href="#!">one</a></li>
        <li><a href="#!">two</a></li>
        <li class="divider"></li>
        <li><a href="#!">three</a></li>
    </ul>
    <nav>
        <div class="nav-wrapper">
            <a href="#!" class="brand-logo">Internet Provider</a>
            <ul class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/InternetProvider/service">Services</a></li>
                <li>
                    <a class="dropdown-trigger" href="${pageContext.request.contextPath}/login.jsp"
                       data-target="dropdown1">
                        Log in
                        <i class="material-icons right">
                            arrow_drop_down
                        </i>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
