<%-- 
    Document   : edit-weekly-meal-admin
    Created on : Jun 14, 2024, 5:27:22 PM
    Author     : hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Weekly Meal</title>
        <link rel="stylesheet" href="./css/header.css">
        <link rel="stylesheet" href="./css/weekly-meal-plan-detail.css">
        <link rel="stylesheet" href="./css/edit-weekly-meal-admin.css">
        <script defer src="./javascript/weekly-plan-meal-detail.js"></script>
        <script defer src="./javascript/edit-weekly-meal-admin.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="searchBarHeader" value="force-unactive"/>
        </jsp:include>
        <c:set var="types" value="${['Breakfast', 'Lunch', 'Dinner', 'Snack']}"/>
        <c:set var="days" value="${['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']}"/>
        <c:set var="meals" value="${requestScope.meals}"/>
        <c:set var="weeklyId" value="${requestScope.weeklyId}"/>

        <div class="banner">
            <div class="banner-title">
                <span>Weekly Meal Plan Detail</span>
            </div>
        </div>

        <main>
            <div class="days-of-the-week">
                <p class="day active">Monday</p>
                <p class="day">Tuesday</p>
                <p class="day">Wednesday</p>
                <p class="day">Thursday</p>
                <p class="day">Friday</p>
                <p class="day">Saturday</p>
            </div>
            <form action="main" method="post">
                <input name="weeklyId" value="${weeklyId}" hidden/>
                <div class="weekly-content">
                    <div class="days">
                        <c:forEach var="day" items="${days}">
                            <div id="${day.toLowerCase()}"
                                 class="detail-section ${(day.toLowerCase().equalsIgnoreCase("monday") == true) ? "active" : ""}">
                                <c:forEach var="type" items="${types}">
                                    <div class="meals-section ${type.toLowerCase()} ${(type.toLowerCase().equalsIgnoreCase("breakfast") == true) ? "active" : ""}">
                                        <h2 class="type-name">
                                            ${type} Meal In ${day}
                                        </h2>
                                        <div class="meal-list admin">
                                            <div class="list">
                                                <select name="${day.toLowerCase()}-meal-${type.toLowerCase()}" class="input-meal">
                                                    <option value="" disabled selected>Choose meal</option>
                                                    <c:forEach var="meal" items="${meals}">
                                                        <option value="${meal.getId()}" >${meal.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="remove">Remove (X)</div>
                                            </div>
                                            <div class="add-meal">Add more meal (+)</div>
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
                    <button value="admin-add-weekly-meal-detail" name="route" class="save" type="submit">
                        Save
                    </button>
                    <a class="cancel" href="main?route=admin-get-weekly-meal-view">
                        <span>Cancel</span>
                    </a>
                </div>
            </form>
        </main>

    </body>
</body>
</html>
