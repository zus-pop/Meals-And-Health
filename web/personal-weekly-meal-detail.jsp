<%-- 
    Document   : weekly-meal-detail-admin
    Created on : Jun 18, 2024, 5:27:01 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${personalMeal.getName()}</title>
        <link rel="stylesheet" href="./css/weekly-meal-plan-detail.css">
        <link rel="stylesheet" href="./css/weekly-meal-plan-detail-admin.css">
        <script defer src="./javascript/weekly-plan-meal-detail.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="searchBarHeader" value="force-unactive"/>
        </jsp:include>
        <c:set var="personalMeal" value="${requestScope.personalMeal}"/>
        <c:set var="detail" value="${personalMeal.getMeals()}"/>

        <div class="banner">
            <div class="banner-title">
                <span>${personalMeal.getName()}</span>
            </div>
        </div>
        <main>
            <div class="days-of-the-week">
                <c:forEach var="day" items="${detail}">
                    <p class="day ${(day.key.toString().equals('MONDAY') == true) ? 'active' : ''}">${day.key.toString()}</p>
                </c:forEach>
            </div>
            <div class="weekly-content">
                <div class="days">
                    <c:forEach var="day_mapForADay" items="${detail}">
                        <div id="${day_mapForADay.key.toString().toLowerCase()}"
                             class="detail-section ${(day_mapForADay.key.toString().toLowerCase().equalsIgnoreCase('monday') == true) ? 'active' : ""}">
                            <c:forEach var="type_listForAType" items="${day_mapForADay.value}">
                                <div class="meals-section ${type_listForAType.key.toString().toLowerCase()} ${(type_listForAType.key.toString().toLowerCase().equalsIgnoreCase('breakfast') == true) ? 'active' : ''}">
                                    <h2 class="type-name">
                                        ${type_listForAType.key.toString()} MEAL IN ${day_mapForADay.key.toString()}
                                    </h2>
                                    <div class="meal-list">
                                        <c:choose>
                                            <c:when test="${empty type_listForAType.value}">
                                                <h3 style="text-align: center">The list of this type is empty!</h3>
                                            </c:when>

                                            <c:otherwise>
                                                <c:forEach var="meal" items="${type_listForAType.value}">
                                                    <div class="meal">
                                                        <img class="meal-image" src="${meal.getImage()}" alt="meal-image">
                                                        <div class="content">
                                                            <h3 class="meal-name">
                                                                ${meal.getName()}
                                                            </h3>
                                                            <p class="des">
                                                                ${meal.getDescription()}
                                                            </p>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
                <div class="meal-type">
                    <p class="type active">breakfast</p>
                    <p class="type">lunch</p>
                    <p class="type">dinner</p>
                    <p class="type">snack</p>
                </div>
            </div>

            <div class="action-crud">
                <a href="main?route=personal-get-update-meal-plan-detail-view&personalMealId=${personalMeal.getId()}">
                    <span>EDIT THE DETAILS OF THIS WEEKLY MEAL ${requestScope.msg}</span>
                </a>
            </div>
        </main>
    </body>
</html>
