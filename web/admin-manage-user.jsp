<%-- 
    Document   : manage-user
    Created on : Jul 1, 2024, 9:33:26 AM
    Author     : HUUPHUOC
--%>

<%@page import="utils.Role"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <link rel="stylesheet" href="./css/manage-user.css">
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="route" value="admin-search-user"/>
        </jsp:include>
        <div class="manage-user-container">
            <form class="search-form" action="main" method="get">
                <input class="search-user" type="search" name="search" placeholder="Enter Username or Email" value=${requestScope.searchBack}>
                <button name="route" value="admin-search-user" tabindex="-1">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
            <c:set var="users" value="${requestScope.users}"/>
            <c:choose>
                <c:when test="${empty users}">
                    <h1 class="empty-msg">${requestScope.msg}</h1>
                </c:when>

                <c:otherwise>
                    <div class="table">
                        <div class="title-row row">
                            <h3 class="col id">ID</h3>
                            <h3 class="col username">UserName</h3>
                            <h3 class="col phone">PhoneNumber</h3>
                            <h3 class="col email">Email</h3>
                            <h3 class="col role">Role</h3>
                            <h3 class="col action">Action</h3>
                        </div>
                        <c:forEach var="user" items="${users}">
                            <div class="content-row row">
                                <h4 class="col id">${user.getId()}</h4>
                                <h4 class="col username">${user.getUserName()}</h4>
                                <h4 class="col phone">${user.getPhone() != null ? user.getPhone() : 'None'}</h4>
                                <h4 class="col email">${user.getEmail()}</h4>
                                <h4 class="col role">${Role.getRole(user.getRole().getId())}</h4>
                                <div class="col action">
                                    <form action="main" method="post" onsubmit="return window.confirm('Sure?')">
                                        <input type="hidden" name="id" value="${user.getId()}">
                                        <input type="hidden" name="search" value="${requestScope.searchBack}">
                                        <input type="hidden" name="deleteType" value="soft">
                                        <button ${sessionScope.user.getId() == user.getId() ? 'disabled' : ''} class="remove-btn" type="submit" name="route" value="admin-remove-user">Remove</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <a class="bin-btn" href="main?route=admin-manage-user-bin-view">
            <div style="--expand: 170px" class="btn-content">
                <span class="more-text">User bin</span>
                <i class="trash fa-solid fa-trash"></i>
            </div>
        </a>
    </body>
</html>
