<%-- 
    Document   : manage-order
    Created on : Jul 1, 2024, 8:49:56 PM
    Author     : HUUPHUOC
--%>

<%@page import="utils.OrderStatus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Manager</title>
        <link rel="stylesheet" href="./css/admin-manage-order.css">
        <script defer src="./javascript/api.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp">
            <jsp:param name="searchBarHeader" value="force-unactive"/>
        </jsp:include>
        <c:set var="orders" value="${requestScope.orders}" />
        <fmt:setLocale value="vi_VN"/>

        <div class="manage-order-container">
            <h1 class="manage-title">Order Management</h1>
            <div class="search-container">
                <form action="main" method="get"> 
                    <fieldset class="search-by-address">
                        <legend>Search by address</legend>
                        <div class="address-selector">
                            <select  name="city" id="city">
                                <option disabled selected value="">Select City / Province</option>
                            </select>

                            <select  disabled name="district" id="district">
                                <option value="">Select District</option>
                            </select>

                            <select  disabled name="ward" id="ward">
                                <option value="">Select Ward</option>
                            </select>
                        </div>
                        <button class="address-btn" type="submit" name="route" value="admin-get-order-by-address">Search</button>
                    </fieldset>
                </form>

                <form action="main" method="get">
                    <fieldset class="search-by-date-contact">
                        <legend>Search by date & contact</legend>
                        <div class="contact-date-section">
                            <label for="startDate">Start Date:</label>
                            <input type="date" id="startDate" name="startDate" value="${requestScope.startDateBack}">
                        </div>
                        <div class="contact-date-section">
                            <label for="endDate">End Date:</label>
                            <input type="date" id="endDate" name="endDate" value="${requestScope.endDateBack}">
                        </div>
                        <div class="contact-date-section">
                            <label for="contact">Email/Phone:</label>
                            <input type="text" id="contact" name="contact" value="${requestScope.contactBack}"
                                   placeholder="Enter email or phone">
                        </div>
                        <button class="date-contact-btn" type="submit" name="route" value="admin-get-order-by-date-contact">Search</button>
                    </fieldset>
                </form>
            </div>
            <c:if test="${not empty orders}" >
                <div class="table">
                    <div class="title-row row">
                        <p class="col">Order ID</p>
                        <p class="col">Customer</p>
                        <p class="col">Phone</p>
                        <p class="email-col col">Email</p>
                        <p class="col">Order Date</p>
                        <p class="col">Ship Via</p>
                        <p class="col">Total</p>
                        <p class="col">Status</p>
                        <p class="address-col col">Delivery Address</p>
                        <p class="col">Actions</p>
                    </div>
                    <c:forEach var="order" items="${orders}">
                        <div class="content-row row">
                            <p class="col">${order.id}</p>
                            <p class="col">${order.customer.userName}</p>
                            <p class="col">${order.customer.phone != null ? order.customer.phone : 'None'}</p>
                            <p class="email-col col">${order.customer.email}</p>
                            <p class="col">${order.orderDate}</p>
                            <p class="col">${order.shipVia.name}</p>
                            <p class="col"><fmt:formatNumber value="${order.total}" currencySymbol="₫" type="currency"/></p>
                            <p class="col">${order.status.name}</p>
                            <p class="address-col col">${order.deliveryAddress}</p>
                            <div class="action-col col">
                                <a class="detail-btn"
                                   href="main?route=admin-manage-order-detail&orderId=${order.id}">Details</a>
                                <form action="main?route=admin-update-order-status" method="post" onChange="this.submit()">
                                    <input type="hidden" name="orderId" value="${order.id}">
                                    <input type="hidden" name="searchType" value="${requestScope.searchType}">
                                    <c:forEach var="searchParam" items="${requestScope.searchParams}">
                                        <input type="hidden" name="searchParams" value="${searchParam}">
                                    </c:forEach>    
                                    <c:set var="currentStatus" value="${order.status}"/>
                                    <select class="status-changer" name="newStatus" ${currentStatus == OrderStatus.CANCELED ? 'disabled' : currentStatus == OrderStatus.COMPLETED ? 'disabled' : '' }>
                                        <c:forEach var="status" items="${OrderStatus.values()}">
                                            <option value="${status.id}" ${currentStatus == status ? 'selected' : ''} ${currentStatus.id > status.id ? 'disabled' : ''}>${status.name}</option>
                                        </c:forEach>   
                                    </select>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${empty orders}">
                <p class="empty-msg">${emptyMessage}</p>
            </c:if>

        </div>
    </body>
</html>
