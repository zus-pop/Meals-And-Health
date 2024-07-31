<%-- 
    Document   : cart.jsp
    Created on : Jun 26, 2024, 4:00:36 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="utils.ShipCompany" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.getUserName()}'s Cart</title>
        <link rel="stylesheet" href="./css/cart.css">
        <script defer src="./javascript/cart.js"></script>
        <script defer src="./javascript/api.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>

    <body>
        <c:set var="user" value="${sessionScope.user}"/>
        <c:set var="cart" value="${sessionScope.cart}"/>
        <c:set var="totalCost" value="${requestScope.totalCost}"/>
        <fmt:setLocale value="vi_VN"/>

        <jsp:include page="header.jsp"/>
        <main>
            <div class="cart-container">
                <div class="cart-section">
                    <div class="cart-row title">
                        <div class="meal">Meal</div>
                        <div class="unit-price">Unit-price</div>
                        <div class="quantity">Quantity</div>
                        <div class="total-price">Total Price</div>
                        <div class="cart-action">Action</div>
                    </div>
                    <c:choose>
                        <c:when test="${cart == null}">
                            <h3>Empty Cart</h3>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="meal_quantity" items="${cart}">
                                <div class="cart-row meal">
                                    <input hidden name="meal-id" class="meal-id" type="text" value="${meal_quantity.key.getId()}">
                                    <div class="meal">
                                        <a href="">
                                            <img class="meal-img" src="${meal_quantity.key.getImage()}" alt="Meal Image">
                                        </a>
                                        <div class="meal-content">
                                            <a href="" class="meal-name">${meal_quantity.key.getName()}</a>
                                            <p class="meal-description">${meal_quantity.key.getDescription()}</p>
                                        </div>
                                    </div>
                                    <div class="unit-price">
                                        <fmt:formatNumber value="${meal_quantity.key.getPrice()}" currencySymbol="₫" type="currency"/>
                                    </div>
                                    <div class="quantity">
                                        <div class="quantity-changer">
                                            <div class="decrease btn">
                                                <i class="fa-solid fa-minus"></i>
                                            </div>
                                            <input name="quantity" class="quantity-value" type="text" value="${meal_quantity.value}">
                                            <div class="increase btn">
                                                <i class="fa-solid fa-plus"></i>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="total-price"><fmt:formatNumber value="${meal_quantity.key.getPrice() * meal_quantity.value}" currencySymbol="₫"  type="currency"/></div>
                                    <div class="cart-action">
                                        <button class="remove" type="button">Remove</button>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="cart-checkout">
                    <div class="total-section">
                        <div id="cart-size" class="total-item">Total (${cart != null ? cart.size() : 0} ${cart.size() > 1 ? 'items' : 'item'}):</div>
                        <div id="total-cost" class="total-cost"><fmt:formatNumber value="${totalCost}" currencySymbol="₫" type="currency"/></div>
                    </div>
                    <button type="button" id="checkout-btn" class="checkout-btn">Check Out</button>
                </div> 
                <dialog id="check-out-dialog">
                    <form id="check-out-form" action="main?route=customer-place-order" method="post">
                        <div class="address-select">
                            <select required class="input-section cart" name="city" id="city">
                                <option disabled selected value="">Select City / Province</option>
                            </select>

                            <select required class="input-section cart" disabled name="district" id="district">
                                <option value="">Select District</option>
                            </select>

                            <select required class="input-section cart" disabled name="ward" id="ward">
                                <option value="">Select Ward</option>
                            </select>
                        </div>

                        <input required class="input-section" name="street" type="text" id="street" placeholder="Enter your street">

                        <div class="shipper-select">
                            <select required class="input-section" name="shipper" id="shipper">
                                <option value="" disabled selected>Select Ship Company</option>
                                <c:forEach var="shipper" items="${ShipCompany.values()}">
                                    <option value="${shipper.getId()}" >${shipper.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="check-out-action">
                            <button id="place-order-btn" class="dialog-btn" type="submit">Place Order</button>
                            <button id="cancel-btn" class="dialog-btn" type="button">Cancel</button>
                        </div>
                    </form>
                </dialog>
            </div>
        </main>
    </body>
</html>
