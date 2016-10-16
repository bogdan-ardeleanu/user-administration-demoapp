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
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-card">
            <div class="login-form">
                <c:url value="/login" var="loginUrl"/>
                <form:form commandName="loginForm" action="${loginUrl}" method="post">
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="accountNo" name="accountNo"
                               placeholder="Enter Username" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-actions">
                        <input type="submit"
                               class="btn btn-block btn-primary btn-default" value="Log in">
                    </div>

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
                            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                        </font>
                    </c:if>

                    <form:errors path="*" cssClass="error"></form:errors>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>