<%-- 
    Document   : customer-order-list
    Created on : Jul 1, 2024, 8:33:28 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.getUserName()}'s Orders</title>
        <link rel="stylesheet" href="./css/customer-order.css">

    </head>
    <body>
        <c:set var="user" value="${sessionScope.user}"/>
        <c:set var="customerOrders" value="${requestScope.customerOrders}"/>       
        <fmt:setLocale value="vi_VN"/>
        <jsp:include page="header.jsp"/>

        <div class="orders-container">
            <c:choose>
                <c:when test="${empty customerOrders}">
                    <div class="empty-msg">
                        <h2>You haven't had any order yet!</h2>
                    </div>
                </c:when>

                <c:otherwise>
                    <c:forEach var="order" items="${customerOrders}">
                        <div class="order">
                            <div class="order-info">
                                <a href="main?route=customer-get-order-detail&orderId=${order.getId()}" class="link-to-detail">
                                    <div class="order-meta">
                                        <div class="order-id">
                                            <div class="label">Order Id:</div>
                                            <div class="value">${order.getId()}</div>
                                        </div>
                                        <div class="order-status">
                                            <div class="label">Status:</div>
                                            <div class="value">${order.getStatus().getName()}</div>
                                        </div>
                                    </div>
                                    <c:forEach var="detail" items="${order.getDetails()}">
                                        <div class="order-row">
                                            <div class="meal-content">
                                                <img class="meal-image" src="${detail.getMeal().getImage()}" alt="Meal Image">
                                                <div class="meal-des">
                                                    <div class="meal-name">${detail.getMeal().getName()}</div>
                                                    <div class="meal-quantity">X ${detail.getQuantity()}</div>
                                                </div>
                                            </div>
                                            <div class="meal-unit-price">
                                                <fmt:formatNumber value="${detail.getUnitPrice()}" currencySymbol="₫" type="currency"/>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </a>
                            </div>
                            <div class="order-total-cost">Total cost: <fmt:formatNumber value="${order.getTotal()}" currencySymbol="₫" type="currency"/></div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
