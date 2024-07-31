<%-- 
    Document   : meal-details
    Created on : Jul 1, 2024, 6:04:09 PM
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
        <title>${meal.getName()}</title>
        <link rel="stylesheet" href="./css/meal-detail.css">
        <script defer src="./javascript/meal-detail.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>

    <body>
        <jsp:include page="header.jsp"/>
        <fmt:setLocale value="vi_VN"/>
        <c:set var="meal" value="${requestScope.meal}"/>
        <c:set var="ingsOfMeal" value="${requestScope.ingsOfMeal}"/>
        <div class="meal-container">
            <div class="main-section">
                <img class="meal-image" src="${meal.getImage()}" alt="Meal-image">
                <div class="meal-content">
                    <h2 class="meal-name">${meal.getName()}</h2>
                    <p class="meal-des">${meal.getDescription()}</p>
                    <h4 class="meal-price"><fmt:formatNumber value="${meal.getPrice()}" currencySymbol="₫" type="currency"/></h4>
                    <c:if test="${sessionScope.user eq null || sessionScope.user.role eq Role.CUSTOMER}">
                        <div class="meal-action">
                            <div class="quantity">
                                <div class="quantity-changer">
                                    <div class="decrease btn">
                                        <i class="fa-solid fa-minus"></i>
                                    </div>
                                    <input id="quantity" name="quantity" class="quantity-value" type="text" value="1">
                                    <div class="increase btn">
                                        <i class="fa-solid fa-plus"></i>
                                    </div>
                                </div>
                            </div>
                            <button id="add-with-quantity" type="button" value="${meal.getId()}" class="add-to-cart"><i class="fa-solid fa-cart-shopping cart"></i> Add to cart</button>
                        </div>
                    </c:if>


                </div>
            </div>
            <div class="sub-section">
                <h2 class="meal-ing-info">Meal Ingredients</h2>
                <ul class="ings">
                    <c:forEach var="ing" items="${ingsOfMeal}">
                        <li class="ing">${ing.getIngredient().getName()}: ${ing.getQuantity()} ${ing.getUnit().getName()}</li>
                        </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>
