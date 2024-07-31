<%-- 
    Document   : manage-order-detail
    Created on : Jul 2, 2024, 10:25:35 AM
    Author     : HUUPHUOC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="./css/admin-manage-order-detail.css">
    </head>
    
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="searchBarHeader" value="force-unactive"/>
        </jsp:include>
        <fmt:setLocale value="vi_VN"/>

        <div class="order-detail-container">
            <h1 class="detail-title">Order Detail</h1>
            <c:set var="orderDetails" value="${requestScope.orderDetails}" />
            <c:choose>
                <c:when test="${not empty orderDetails}">
                    <div class="table">
                        <div class="title-row row">
                            <p class="img-col col">Image</p>
                            <p class="meal-name-col col">Meal</p>
                            <p class="unit-price-col col">Unit Price</p>
                            <p class="quantity-col col">Quantity</p>
                        </div>
                        <c:forEach var="orderDetail" items="${orderDetails}">
                            <div class="content-row row">
                                <img class="img-col col content" src="${orderDetail.getMeal().getImage()}" alt="Meal Image">
                                <p class="meal-name-col col">${orderDetail.getMeal().getName()}</p>
                                <p class="unit-price-col col"><fmt:formatNumber value="${orderDetail.getUnitPrice()}" currencySymbol="₫" type="currency"/></p>
                                <p class="quantity-col col">${orderDetail.getQuantity()}</p>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <h1 class="empty-msg">${requestScope.msg}</h1>
                </c:otherwise>
            </c:choose>
        </div>

    </body>
</html>
