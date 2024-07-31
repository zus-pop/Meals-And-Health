<%-- 
    Document   : edit-weekly-meal-admin
    Created on : Jun 14, 2024, 5:27:22 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${weeklyMeal.getName()}</title>
        <link rel="stylesheet" href="./css/weekly-meal-plan-detail.css">
        <link rel="stylesheet" href="./css/edit-weekly-meal-admin.css">
        <script defer src="./javascript/weekly-plan-meal-detail.js"></script>
        <script defer src="./javascript/edit-weekly-meal-admin.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:set var="meals" value="${requestScope.meals}"/>
        <c:set var="personalMeal" value="${requestScope.personalMeal}"/>
        <c:set var="detail" value="${personalMeal.getMeals()}"/>

        <div class="banner">
            <div class="banner-title">
                <span>${personalMeal.getName()}</span>
            </div>
        </div>

        <main>
            <div class="days-of-the-week">
                <c:forEach var="day_mapForADay" items="${detail}">
                    <p class="day ${(day_mapForADay.key.toString().equals('MONDAY') == true) ? 'active' : ''}">${day_mapForADay.key.toString()}</p>
                </c:forEach>
            </div>

            <form action="main" method="post">
                <input name="personalMealId" value="${personalMeal.getId()}" hidden/>
                <div class="weekly-content">
                    <div class="days">
                        <c:forEach var="day_mapForADay" items="${detail}">
                            <div id="${day_mapForADay.key.toString().toLowerCase()}"
                                 class="detail-section ${(day_mapForADay.key.toString().toLowerCase().equalsIgnoreCase('monday') == true) ? 'active' : ''}">
                                <c:forEach var="type_listForAType" items="${day_mapForADay.value}">
                                    <div class="meals-section ${type_listForAType.key.toString().toLowerCase()} ${(type_listForAType.key.toString().toLowerCase().equalsIgnoreCase('breakfast') == true) ? 'active' : ''}"> 
                                        <h2 class="type-name">
                                            ${type_listForAType.key.toString()} MEAL IN ${day_mapForADay.key.toString()}
                                        </h2>
                                        <div class="meal-list admin">
                                            <c:if test="${empty type_listForAType.value}">
                                                <div class="list">
                                                    <select name="${day_mapForADay.key.toString().toLowerCase()}-meal-${type_listForAType.key.toString().toLowerCase()}" class="input-meal">
                                                        <option value="" disabled selected>Choose meal</option>
                                                        <c:forEach var="meal" items="${meals}">
                                                            <option value="${meal.getId()}" >${meal.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="remove">Remove (X)</div>
                                                </div>
                                            </c:if>
                                            <c:forEach var="addedMeal" items="${type_listForAType.value}">
                                                <div class="list">
                                                    <select name="${day_mapForADay.key.toString().toLowerCase()}-meal-${type_listForAType.key.toString().toLowerCase()}" class="input-meal">
                                                        <option value="" disabled selected>Choose meal</option>
                                                        <c:forEach var="meal" items="${meals}">
                                                            <c:choose>

                                                                <c:when test="${addedMeal.getId() == meal.getId()}">
                                                                    <option selected value="${meal.getId()}" >${meal.getName()}</option>
                                                                </c:when>

                                                                <c:otherwise>
                                                                    <option value="${meal.getId()}" >${meal.getName()}</option>
                                                                </c:otherwise>

                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="remove">Remove (X)</div>
                                                </div>
                                            </c:forEach>
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
                    <button value="personal-update-meal-plan-detail" name="route" class="update" type="submit">
                        Update
                    </button>
                    <a class="cancel" href="main?route=personal-get-meal-plan-detail&personalMealId=${personalMeal.getId()}">
                        <span>Cancel</span>
                    </a>
                </div>
            </form>
        </main>

    </body>
</body>
</html>
