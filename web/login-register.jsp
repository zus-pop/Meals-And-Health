<%-- 
    Document   : login-register
    Created on : Jun 13, 2024, 3:07:14 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.type}</title>
        <link rel="stylesheet" href="./css/login-register.css">
        <script defer src="./javascript/login-register.js"></script>
    </head>

    <body>
        <div class="container">
            <div class="login section ${requestScope.type == "Login" ? "active" : ""}">
                <h2 class="title">Login</h2>
                <form class="form" action="main" method="post">
                    <div class="email-field">
                        <!-- <label for="username">Username</label> -->
                        <input required placeholder="Email" name="email" 
                               type="email" value="${requestScope.emailBack}">
                    </div>
                    <div class="password-field">
                        <!-- <label for="password">Password</label> -->
                        <input required placeholder="Password" name="password" 
                               type="password" value="${requestScope.passwordBack}">
                    </div>
                    <c:if test="${requestScope.invalidUser != null}">
                        <span class="invalid">${requestScope.invalidUser}</span>
                    </c:if> 
                    <c:if test="${requestScope.created != null}">
                        <span class="created">${requestScope.created}</span>
                    </c:if> 
                    <button class="button" name="route" value="login" type="submit">Login</button>
                </form>
                <p>DON'T HAVE AN ACCOUNT? <span id="to-register">REGISTER HERE</span></p>
            </div>
            <div class="register section ${requestScope.type == "Register" ? "active" : ""}">
                <h2 class="title">Register</h2>
                <form class="form" action="main" method="post">
                    <div class="email-field">
                        <!-- <label for="email">Email</label> -->
                        <input id="email" placeholder="Email" name="email" 
                               type="email" value="${requestScope.registerEmailBack}">
                        <span class="error">Wrong email format!</span>
                    </div>
                    <div class="username-field">
                        <!-- <label for="username">Username</label> -->
                        <input required placeholder="Username" name="username" 
                               type="text" value="${requestScope.registerUsernameBack}">
                    </div>
                    <div class="password-field">
                        <!-- <label for="password">Password</label> -->
                        <input required id="password" placeholder="Password" name="password" 
                               type="password" value="${requestScope.registerPasswordBack}">
                        <span class="error not-match">Password is not match</span>
                    </div>
                    <div class="re-password-field">
                        <!-- <label for="password">Password</label> -->
                        <input required id="re-password" placeholder="Password Again" name="re-password"
                               type="password" value="${requestScope.registerRePasswordBack}">
                        <span class="error not-match">Password is not match</span>
                    </div>
                    <c:if test="${requestScope.userExisted != null}">
                        <span class="invalid">${requestScope.userExisted}</span>
                    </c:if> 
                    <button id="register-btn" class="button" name="route" value="register" type="submit">Register</button>
                </form>
                <p>ALREADY HAVE AN ACCOUNT? <span id="to-login">LOGIN HERE</span></p>
            </div>
        </div>
    </body>
</html>
