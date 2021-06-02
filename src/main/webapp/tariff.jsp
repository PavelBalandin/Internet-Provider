<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="tariff.name" var="tariffTariff"/>
<fmt:message key="tariff.description" var="tariffDescription"/>
<fmt:message key="tariff.price" var="tariffPrice"/>
<fmt:message key="tariff.currency" var="tariffCurrency"/>
<fmt:message key="tariff.order" var="tariffOrder"/>
<fmt:message key="tariff.add.order" var="tariffAddOrder"/>
<fmt:message key="tariff.days" var="tariffDays"/>
<fmt:message key="tariff.sort" var="tariffSort"/>


<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<div class="container">
    <form class="sort_tariff_from" method="get"
          action="${pageContext.request.contextPath}/InternetProvider/getTariffListByService">
        <div class="input-field col s12">
            <input type="hidden" name="id" value="${serviceId}">
            <select name="sort">
                <option value="name asc" ${sessionScope.sort.equals("name asc") ? "selected" : ""}>a-z</option>
                <option value="name desc" ${sessionScope.sort.equals("name desc") ? "selected" : ""}>z-a</option>
                <option value="price asc" ${sessionScope.sort.equals("price asc") ? "selected" : ""}>$-$$$</option>
                <option value="price desc" ${sessionScope.sort.equals("price desc") ? "selected" : ""}>$$$-$</option>
            </select>
        </div>
        <button class="btn" type="submit">${tariffSort}</button>
    </form>
    <div class="tariff_list">
        <table class="striped">
            <thead>
            <tr>
                <th>${tariffTariff}</th>
                <th>${tariffDescription}</th>
                <th>${tariffPrice}</th>
                <th>${tariffOrder}</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="tariff" items="${tariffList}">
                <tr>
                    <td>${tariff.name}</td>
                    <td>${tariff.description}</td>
                    <td>${tariff.price} ${tariffCurrency} / ${tariff.duration} ${tariffDays}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/InternetProvider/addTariffToOrder?id=${tariff.id}">${tariffAddOrder}</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/views/components/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var elems = document.querySelectorAll('select');
        var instances = M.FormSelect.init(elems, {constraint: true});
    });
</script>
</body>
</html>
