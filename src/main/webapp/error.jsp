<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>
<html>
<head>
    <title>Error page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="error_page">
        <h1>Not found</h1>
        <p>${errorMessage}</p>
        <a href="/">Return to main</a>
    </div>
</div>
</body>
</html>
