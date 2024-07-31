<%-- 
    Document   : meal-list
    Created on : Jun 24, 2024, 3:47:06 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="utils.Role"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meal List</title>
        <link rel="stylesheet" href="./css/meal-list.css">
        <script defer src="./javascript/weekly-meal-observe.js"></script>
        <script defer src="./javascript/action.js"></script>
        <script defer src="./javascript/meal-list.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="route" value="search-meal-by-name"/>
        </jsp:include>
        <c:set var="meals" value="${requestScope.meals}"/>
        <fmt:setLocale value="vi_VN"/>
        <div class="banner">
            <div class="banner-title">
                <span>Meals ${requestScope.notFound}</span>
            </div>
        </div>
        <main>
            <div class="action">
                <div class="search-bar">
                    <form action="main" method="get">
                        <input id="main-search-bar" name="search" placeholder="Search" type="search" value="${requestScope.nameBack}">
                        <button type="submit" name="route" value="search-meal-by-name" tabindex="-1">
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
            <div class="meals">
                <c:forEach var="meal" items="${meals}">
                    <div class="card">
                        <img src="${meal.getImage()}" alt="weekly-picture">
                        <h3 class="meal-title">${meal.getName()}</h3>
                        <h3 class="meal-price"><fmt:formatNumber value="${meal.getPrice()}" currencySymbol="₫" type="currency"/></h3>
                        <div class="action-button">
                            <a href="main?route=customer-meal-detail-view&mealId=${meal.getId()}" class="detail-btn btn">Details</a>
                            <c:if test="${sessionScope.user eq null || sessionScope.user.role eq Role.CUSTOMER}">
                                <button type="button" value="${meal.getId()}" class="buy-btn btn">Add to cart</button>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
    </body>
</html>
