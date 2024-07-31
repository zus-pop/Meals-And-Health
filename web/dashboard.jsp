<%-- 
    Document   : dashboard
    Created on : Jun 13, 2024, 3:01:26 AM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="./css/dashboard.css">
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="searchBarHeader" value="force-unactive"/>
        </jsp:include>

        <div class="manage-container">
            <div class="manage-list">
                <a href="main?route=admin-get-weekly-meal-view" class="manage">
                    Manage Weekly Menu
                </a>
<!--                <a class="manage" href="">
                    Manage Meal
                </a>-->
                <a class="manage" href="main?route=admin-manage-order-view">
                    Manage Order
                </a>
                <a href="main?route=admin-manage-user-view" class="manage">
                    Manage User
                </a>
            </div>
        </div>
    </body>
</html>
