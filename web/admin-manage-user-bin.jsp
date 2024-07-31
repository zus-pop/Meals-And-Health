<%-- 
    Document   : bin
    Created on : Jul 7, 2024, 10:34:27 PM
    Author     : HUUPHUOC
--%>

<%@page import="utils.Role"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage User Bin</title>
        <link rel="stylesheet" href="./css/manage-user.css">
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="searchBarHeader" value="force-unactive"/>
        </jsp:include>
        <div class="manage-user-container">
            <c:set var="users" value="${requestScope.binUsers}"/>
            <c:choose>
                <c:when test="${empty binUsers}">
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
                                    <form action="main" method="post" onsubmit="return window.confirm('Do you want to restore this user?')">
                                        <input type="hidden" name="id" value="${user.getId()}">
                                        <button class="remove-btn" type="submit" name="route" value="admin-restore-user">Restore</button>
                                    </form>
                                    <form action="main" method="post" onsubmit="return window.confirm('Do you want to permanently delete user have id: ${user.getId()} ?')">
                                        <input type="hidden" name="id" value="${user.getId()}">
                                        <input type="hidden" name="deleteType" value="hard"/>
                                        <button class="remove-btn" type="submit" name="route" value="admin-remove-user">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <a class="bin-btn" href="main?route=admin-manage-user-view">
            <div style="--expand: 260px" class="btn-content">
                <span class="more-text">Back to user list</span>
                <i class="arrow-left fa-solid fa-arrow-left"></i>
            </div>
        </a>
    </body>
</html>
