<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<html>
<head>
    <title>Update user</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<div class="content">
    <%@ include file="/WEB-INF/views/admin/side_bar.jsp" %>
    <div class="main-content">
        <form class="find-user" method="get" action="${pageContext.request.contextPath}/InternetProvider/getUser">
            <div class="row">
                <div class="input-field col s12">
                    <input id="login" type="text" class="validate" name="login">
                    <label for="login">Login</label>
                </div>
            </div>
            <button class="btn" type="submit">Find</button>
        </form>
        <c:if test="${not empty user}">
            <div class="user_table">
                <table>
                    <thead>
                    <tr>
                        <th>Login</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <form method="post" action="${pageContext.request.contextPath}/InternetProvider/updateUser">
                        <input type="text" name="id" value="${user.id}" hidden>
                        <tr>
                            <td>${user.login}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role}</td>
                            <td>
                                <select name="status">
                                    <option value="1" ${user.status.name.equals("ENABLED") ? "selected" : ""}>
                                        ENABLED
                                    </option>
                                    <option value="2" ${user.status.name.equals("DISABLED") ? "selected" : ""}>
                                        DISABLED
                                    </option>
                                </select></td>
                            <td>
                                <button class="btn-small">update</button>
                            </td>
                        </tr>
                    </form>
                    </tbody>
                </table>
            </div>
        </c:if>
        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>