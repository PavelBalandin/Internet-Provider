<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>
<html>
<head>
    <title>User registration page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<div class="registration">
    <form method="post" action="${pageContext.request.contextPath}/InternetProvider/createUser">
        <p class="center-align title">Registration</p>
        <div class="row">
            <div class="input-field col s12">
                <input id="login" type="text" class="validate" name="login">
                <label for="login">Login</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="firstname" type="text" class="validate" name="firstname">
                <label for="firstname">First name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="lastname" type="text" class="validate" name="lastname">
                <label for="lastname">Last name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="password" type="password" class="validate" name="password">
                <label for="password">Password</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light" type="submit" name="action">log in
                <i class="material-icons right">send</i>
            </button>
        </div>
        <a href="/index.jsp">Home</a>
        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message"></p>
    </form>
</div>
</body>
</html>
