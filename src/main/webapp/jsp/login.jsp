<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link rel="stylesheet" href="/static/css/app.css">
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.6/css/bootstrap.css">
    <link rel="stylesheet" href="/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
</head>

<body>
<i>
    <p><b>Pagina 1. Selectie ID client</b></p>
    <p>In prima pagina userul poate introduce un ID de client si un buton de submit. Cand se da click
        pe submit utilizatorului i se afiseaza a doua pagina.
    </p>
</i>
<div class="container">
    <c:url value="/login" var="loginUrl"/>
    <form:form commandName="loginForm" action="${loginUrl}" method="post" class="form-signin">
        <input type="text" class="form-control" id="identifier" name="identifier" placeholder="Enter ID" required>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>

        <%--<c:if test="${param.error != null}">--%>
        <%--<div class="alert alert-danger">--%>
        <%--<p>Invalid AccountNo</p>--%>
        <%--</div>--%>
        <%--</c:if>--%>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>You have been logged out successfully.</p>
            </div>
        </c:if>
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <font color="red">
                <br/><br/>
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
            </font>
        </c:if>

        <form:errors path="*" cssClass="error"></form:errors>
    </form:form>
</div>

</body>
</html>