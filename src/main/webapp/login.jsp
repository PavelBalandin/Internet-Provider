<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="login">
    <form method="post" action="${pageContext.request.contextPath}/login">
        <p class="center-align title">Login</p>
        <div class="row">
            <div class="input-field col s12">
                <input id="login" type="text" class="validate" name="login">
                <label for="login">Login</label>
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
        <a href="/InternetProvider/index.jsp">Home</a>
        <p class="center-align red-text text-darken-2 warning__message">Error text*</p>
        <p class="center-align green-text text-darken-2 successful__message"></p>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
