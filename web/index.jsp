<%-- 
    Document   : index
    Created on : Jun 13, 2024, 1:49:01 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meals and Health</title>
        <link rel="stylesheet" href="css/home-page.css">
        <script defer src="javascript/home-page-observe.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="searchBarHeader" value="force-unactive"/>
        </jsp:include>
        <fmt:setLocale value="vi_VN"/>

        <main class="main-content">
            <div class="main-banner">
                <div class="banner-content">
                    <h1 class="main-quote">Meals and Health</h1>
                    <h4 class="description">Providing consistent plan meals and selling meals as well</h4>
                </div>
            </div>
            <div id="meals-section" class="meals-section">
                <h2 class="meal-title underline">
                    Meals
                </h2>
                <div class="meals-grid">
                    <c:forEach var="meal" items="${requestScope.meals}">
                        <div class="meal-card observed">
                            <a href="main?route=customer-meal-detail-view&mealId=${meal.getId()}">
                                <img class="meal-image" src="${meal.getImage()}" alt="meal-picture">
                            </a>
                            <div class="description">
                                <h3 class="name">${meal.getName()}</h3>
                                <h4 title="${meal.getDescription()}" class="des">${meal.getDescription()}</h4>
                                <h4 class="price">
                                    <fmt:formatNumber value="${meal.getPrice()}" currencySymbol="₫" type="currency"/>
                                </h4>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="weekly-plans-section">
                <h2 class="weekly-plans-title underline">
                    Weekly Plans
                </h2>
                <c:choose>
                    <c:when test="${empty requestScope.weeklyMeals}">
                        <h3 class="empty-msg">Empty Weekly</h3>
                    </c:when>
                    <c:otherwise>
                        <div class="weekly-plans-grid">
                            <div class="weekly-plan-big weekly-card observed">
                                <a href="main?route=customer-weekly-meal-detail-view&weeklyId=${requestScope.weeklyMeals.get(0).getId()}">
                                    <img src="${requestScope.weeklyMeals.get(0).getImage()}" alt="weekly-plans-big-image"
                                         class="big-img">
                                </a>
                                <div class="description">
                                    <h3 class="name">${requestScope.weeklyMeals.get(0).getName()}</h3>
                                    <h4 title="${requestScope.weeklyMeals.get(0).getDescription()}" class="des">
                                        ${requestScope.weeklyMeals.get(0).getDescription()}
                                    </h4>
                                </div>
                            </div>

                            <div class="weekly-plans-list">
                                <div class="weekly-plan weekly-card observed">
                                    <a href="main?route=customer-weekly-meal-detail-view&weeklyId=${requestScope.weeklyMeals.get(1).getId()}">
                                        <img src="${requestScope.weeklyMeals.get(1).getImage()}" alt="weekly-plans-big-image"
                                             class="small-img">
                                    </a>
                                    <div class="description">
                                        <h3 class="name">${requestScope.weeklyMeals.get(1).getName()}</h3>
                                        <h4 title="${requestScope.weeklyMeals.get(1).getDescription()}" class="des">
                                            ${requestScope.weeklyMeals.get(1).getDescription()}
                                        </h4>
                                    </div>
                                </div>

                                <div class="weekly-plan weekly-card observed">
                                    <a href="main?route=customer-weekly-meal-detail-view&weeklyId=${requestScope.weeklyMeals.get(2).getId()}">
                                        <img src="${requestScope.weeklyMeals.get(2).getImage()}" alt="weekly-plans-big-image"
                                             class="small-img">
                                    </a>
                                    <div class="description">
                                        <h3 class="name">${requestScope.weeklyMeals.get(2).getName()}</h3>
                                        <h4 title="${requestScope.weeklyMeals.get(2).getDescription()}" class="des">
                                            ${requestScope.weeklyMeals.get(2).getDescription()}
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
    </body>
</html>
