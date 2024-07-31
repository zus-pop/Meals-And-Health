<%-- 
    Document   : customer-order-detail
    Created on : Jul 2, 2024, 1:09:10 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link rel="stylesheet" href="./css/customer-order-detail.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <fmt:setLocale value="vi_VN"/>
        <c:set var="order" value="${requestScope.order}"/>
        <c:set var="details" value="${requestScope.order.getDetails()}"/>


        <div class="order-detail-container">
            <div class="order-tracking">
                <h4 class="order-id">Order Id: ${order.getId()}</h4>
                <h6 class="order-items">${details.size()} ${details.size() > 0 ? 'Meals' : 'Meal'}</h6>
                <div class="order-progress">
                    <a class="track-detail" href="">Track Order</a>
                    <div class="progress-bar">
                        <div class="bar-load ${order.getStatus().getName().toLowerCase()}">
                            <i class="fa-solid fa-truck-fast package"></i>
                        </div>
                    </div>
                    <div class="order-status">${order.getStatus().getName()}</div>
                </div>
            </div>
            <div class="order-details">
                <div class="ordered-meals">
                    <div class="meal-title title">
                        <h5>Meals</h5>
                    </div>
                    <div class="meal-list">
                        <c:forEach var="detail" items="${details}">
                            <div class="meal-row">
                                <div class="meal">
                                    <img class="meal-image" src="${detail.getMeal().getImage()}" alt="Meal Image">
                                    <div class="meal-info">
                                        <div class="meal-name">${detail.getMeal().getName()}</div>
                                        <div class="meal-unit-quantity">
                                            ${detail.getQuantity()} X <fmt:formatNumber value="${detail.getUnitPrice()}" currencySymbol="₫" type="currency"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="meal-total-price">
                                    <fmt:formatNumber value="${detail.getQuantity() * detail.getUnitPrice()}" currencySymbol="₫" type="currency"/>
                                </div>
                            </div> 
                        </c:forEach>
                        <div class="total">
                            <div class="sub-total">
                                <p class="label">Shipping Cost (+):</p>
                                <p class="value">...đ</p>
                            </div>
                            <div class="sub-total">
                                <p class="label">Discount (-):</p>
                                <p class="value">...đ</p>
                            </div>
                        </div>
                        <div class="payable">
                            <p class="label">Total:</p>
                            <p class="value">
                                <fmt:formatNumber value="${order.getTotal()}" currencySymbol="₫" type="currency"/>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="order-infos">
                    <div class="more-order-info">
                        <div class="title">
                            <h5>More Informations</h5>
                        </div>
                        <div class="date-row row">
                            <div class="label">Date:</div>
                            <div class="value">${order.getOrderDate()}</div>
                        </div>
                        <div class="address-row row">
                            <div class="label">Delivery Address:</div>
                            <div class="value">${order.getDeliveryAddress()}</div>
                        </div>
                    </div>
                    <div class="customer">
                        <div class="customer-title title">
                            <h5>Customer details</h5>
                        </div>
                        <div class="customer-details">
                            <div class="customer-detail-row row">
                                <div class="label">Name:</div>
                                <div class="value">${order.getCustomer().getUserName()}</div>
                            </div>
                            <div class="customer-detail-row row">
                                <div class="label">Email:</div>
                                <div class="value">${order.getCustomer().getEmail()}</div>
                            </div>
                            <div class="customer-detail-row row">
                                <div class="label">Phone:</div>
                                <div class="value">${order.getCustomer().getPhone() != null ? order.getCustomer().getPhone() : 'None'}</div>
                            </div>
                        </div>
                    </div>
                    <div class="ship-company">
                        <div class="ship-title title">
                            <h5>Ship Via</h5>
                        </div>
                        <div class="ship">
                            <div class="ship-detail-row row">
                                <div class="label">Company:</div>
                                <div class="vale">${order.getShipVia().getName()}</div>
                            </div>
                            <div class="ship-detail-row row">
                                <div class="label">Phone:</div>
                                <div class="vale">${order.getShipVia().getPhone()}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
