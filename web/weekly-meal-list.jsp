<%-- 
    Document   : weekly-meal-list
    Created on : Jun 22, 2024, 8:57:24 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Weekly Meal List</title>
        <link rel="stylesheet" href="./css/weekly-meal-list.css">
        <script defer src="./javascript/weekly-meal-observe.js"></script>
        <script defer src="./javascript/action.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="route" value="search-weekly-by-name"/>
        </jsp:include>
        <div class="banner">
            <div class="banner-title">
                <c:choose>
                    <c:when test="${empty requestScope.weeklyMeals}">
                        <span>${requestScope.msg}</span>
                    </c:when>
                        
                    <c:otherwise>
                        <span>Weekly Meal Plans</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <main>
            <div class="action">
                <div class="search-bar">
                    <form action="main" method="get">
                        <input value="${requestScope.nameBack}" id="main-search-bar" name="search" placeholder="Search" type="search">
                        <button type="submit" name="route" value="search-weekly-by-name" tabindex="-1">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
                <!--                <div class="filter-bar">
                                    <select name="option" id="filter">
                                        <option selected disabled value="">Sort by ...</option>
                                        <option value="name">Name</option>
                                        <option value="date">Updated Date</option>
                                    </select>
                                </div>-->
            </div>

            <div class="weekly-plans">
                <c:forEach var="weeklyMeal" items="${requestScope.weeklyMeals}">
                    <div href="" class="card">
                        <img src="${weeklyMeal.getImage()}" alt="weekly-picture">
                        <h3 class="weekly-title">${weeklyMeal.getName()}</h3>
                        <h3 title="${weeklyMeal.getDescription()}" class="weekly-des">${weeklyMeal.getDescription()}</h3>
                        <div class="action-button">
                            <a href="main?route=customer-weekly-meal-detail-view&weeklyId=${weeklyMeal.getId()}" class="detail-btn btn">Details</a>
                            <form action="main" method="post">
                                <input name="weeklyId" value="${weeklyMeal.getId()}" hidden/>
                                <button type="submit" class="buy-btn btn" name="route" value="customer-fetch-weekly-meal">Add to personal meal plan</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
    </body>
</html>
