<%-- 
    Document   : weekly-meal-list
    Created on : Jun 17, 2024, 3:30:34 AM
    Author     : hoang
--%>

<%@page import="dto.UserAccount"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weekly Meal List</title>
        <link rel="stylesheet" href="./css/weekly-meal-list-admin.css">
        <script defer src="./javascript/action.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="route" value="admin-search-weekly-by-name"/>
        </jsp:include>
        <c:set var="weeklyMeals" value="${requestScope.weeklyMeals}"/>
        <div class="list-container">
            <h1 class="list-title">Weekly Meal List ${requestScope.msg}</h1>
            <div class="action">
                <div class="search-bar">
                    <form action="main" method="get">
                        <input value="${requestScope.nameBack}" id="main-search-bar" name="search" placeholder="Search" type="search">
                        <button type="submit" name="route" value="admin-search-weekly-by-name" tabindex="-1">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </div>
            <c:if test="${empty weeklyMeals}">
                <h2 class="empty-msg">The List is empty!</h2>
            </c:if>
            <c:forEach var="weeklyMeal" items="${weeklyMeals}">
                <div class="table-container">
                    <div class="title-row row">
                        <h3 class="img-col col">Image</h3>
                        <h3 class="name-col col">Weekly Meal Name</h3>
                        <h3 class="des-col col">Description</h3>
                        <h3 class="act-col col">Action</h3>
                    </div>
                    <div class="content-row row">
                        <h4 class="img-col col"><img src="${weeklyMeal.getImage()}" alt="Weekly Meal Image"></h4>
                        <h4 class="name-col col">${weeklyMeal.getName()}</h4>
                        <h4 class="des-col col">${weeklyMeal.getDescription()}</h4>
                        <div class="act-col col content">
                            <a class="detail-button action-btn" 
                               href="main?route=admin-get-weekly-detail&weeklyId=${weeklyMeal.getId()}">
                                Details
                            </a>
                            <a class="edit-button action-btn" 
                               href="main?route=admin-get-update-weekly-view&weeklyId=${weeklyMeal.getId()}">Edit</a>
                            <a class="remove-button action-btn" 
                               href="main?route=admin-remove-weekly-meal&weeklyId=${weeklyMeal.getId()}"
                               onclick="return window.confirm('Are you sure?')">Remove</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <a class="add-more-btn" href="main?route=admin-get-create-weekly-meal-view">
                <div class="btn-content">
                    <i class="plus fa-solid fa-plus"></i><span class="more-text">Add more weekly meal</span>
                </div>
            </a>
    </body>
</html>
