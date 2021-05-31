<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="login.login" var="loginLogin"/>
<fmt:message key="login.password" var="loginPassword"/>
<fmt:message key="login.log.in" var="loginLogIn"/>
<fmt:message key="login.home" var="loginHome"/>

<html>
<head>
    <title>Login</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<div class="login">
    <form method="post" action="${pageContext.request.contextPath}/InternetProvider/login">
        <p class="center-align title">${loginLogin}</p>
        <div class="row">
            <div class="input-field col s12">
                <input id="login" type="text" class="validate" name="login">
                <label for="login">${loginLogin}</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="password" type="password" class="validate" name="password">
                <label for="password">${loginPassword}</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light" type="submit" name="action">${loginLogIn}
                <i class="material-icons right">send</i>
            </button>
        </div>
        <a href="${pageContext.request.contextPath}/InternetProvider/getServiceList">${loginHome}</a>
        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message"></p>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
