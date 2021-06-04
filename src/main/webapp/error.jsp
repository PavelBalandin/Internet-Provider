<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="error.return.main" var="errorReturnMain"/>
<fmt:message key="error.server" var="errorServer"/>
<fmt:message key="message.error" var="messageError"/>

<html>
<head>
    <title>Error page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="error_page">
        <h1>${errorServer}</h1>
        <p>${messageError}</p>
        <a href="/">${errorReturnMain}</a>
    </div>
</div>
</body>
</html>