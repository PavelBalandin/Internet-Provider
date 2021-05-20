<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>

<div class="container">
    <div class="services_list">
        <table class="striped">
            <thead>
            <tr>
                <th>Service Name</th>
                <th>Tariffs</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>Internet</td>
                <td><a href="">More</a></td>
            </tr>
            <tr>
                <td>TV</td>
                <td><a href="">More</a></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td><a href="">More</a></td>
            </tr>
            <tr>
                <td>IP_TV</td>
                <td><a href="">More</a></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/views/components/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
