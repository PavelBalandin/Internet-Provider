<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 20.05.2021
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<div class="container">
    <form class="sort_tariff_from" method="get" action="/">
        <div class="input-field col s12">
            <select>
                <option value="name asc" selected>a-z</option>
                <option value="name desc">z-a</option>
                <option value="price asc">$-$$$</option>
                <option value="price desc">$$$-$</option>
            </select>
            <label>Sort order</label>
        </div>
        <input class="btn" type="submit" value='sort'>
    </form>
    <div class="tariff_list">
        <table class="striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Order</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>SuperNet Turbo</td>
                <td>Безліміт на всіх операторів, 4000 мб інтернету</td>
                <td>300 грн/місяця</td>
                <td><a href="index.jsp">Придбати</a></td>
            </tr>
            <tr>
                <td>SuperNet Turbo</td>
                <td>Безліміт на всіх операторів, 4000 мб інтернету</td>
                <td>300 грн/місяця</td>
                <td><a href="index.jsp">Придбати</a></td>
            </tr>
            <tr>
                <td>SuperNet Turbo</td>
                <td>Безліміт на всіх операторів, 4000 мб інтернету</td>
                <td>300 грн/місяця</td>
                <td><a href="index.jsp">Придбати</a></td>
            </tr>
            <tr>
                <td>SuperNet Turbo</td>
                <td>Безліміт на всіх операторів, 4000 мб інтернету</td>
                <td>300 грн/місяця</td>
                <td><a href="index.jsp">Придбати</a></td>
            </tr>
            <tr>
                <td>SuperNet Turbo</td>
                <td>Безліміт на всіх операторів, 4000 мб інтернету</td>
                <td>300 грн/місяця</td>
                <td><a href="index.jsp">Придбати</a></td>
            </tr>
            <tr>
                <td>SuperNet Turbo</td>
                <td>Безліміт на всіх операторів, 4000 мб інтернету</td>
                <td>300 грн/місяця</td>
                <td><a href="index.jsp">Придбати</a></td>
            </tr>
            <tr>
                <td>SuperNet Turbo</td>
                <td>Безліміт на всіх операторів, 4000 мб інтернету</td>
                <td>300 грн/місяця</td>
                <td><a href="index.jsp">Придбати</a></td>
            </tr>
            </tbody>
        </table>
        <div class="plan_load">
            <a href="">Завантажити перелік тарифних планів</a>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/components/footer.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('select').formSelect();
    });
</script>
</body>
</html>
