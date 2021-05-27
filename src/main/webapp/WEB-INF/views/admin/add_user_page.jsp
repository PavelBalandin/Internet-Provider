<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<html>
<head>
    <title>Add user</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<div class="content">
    <%@ include file="/WEB-INF/views/admin/side_bar.jsp" %>
    <div class="main-content">
        <form method="post" action="${pageContext.request.contextPath}/InternetProvider/createUser">
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
                <button class="btn waves-effect waves-light" type="submit" name="action">sign up
                    <i class="material-icons right">send</i>
                </button>
            </div>
            <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
            <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
        </form>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>