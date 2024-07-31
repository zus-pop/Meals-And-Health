<%-- 
    Document   : header
    Created on : Jun 13, 2024, 5:19:05 PM
    Author     : hoang
--%>

<%@page import="utils.Role"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" href="css/action.css">
        <link rel="stylesheet" href="css/header.css">
        <script defer src="javascript/header.js"></script>
        <script defer src="https://kit.fontawesome.com/7e847de314.js" crossorigin="anonymous"></script>
    </head>
    <body>

        <c:set var="user" value="${sessionScope.user}"/>
        <c:set var="cart" value="${sessionScope.cart}"/>
        <header>
            <div id="header-container" class="header-container">
                <h1 class="home-page-shortcut"><a href="main?route=home">M&H</a></h1>
                <div id="search-bar-scrolling" class="search-bar ${param.searchBarHeader}">
                    <form action="main" method="get">
                        <input value="${requestScope.nameBack}" id="header-search-bar" name="search" placeholder="Search" type="search">
                        <button type="submit" name="route" value="${param.route}" tabindex="-1">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
                <nav class="header-nav">
                    <c:if test="${user ne null && user.role eq Role.ADMIN}">
                        <a class="unactive" href="main?route=staff">Dashboard</a>
                    </c:if>
                    <a class="unactive" href="main?route=customer-weekly-meal-view">Weekly Plans</a>
                    <a class="unactive" href="main?route=customer-meal-view">Meals</a>
                </nav>
                <c:if test="${user == null}">
                    <a href="main?route=login" class="login-button">
                        <button type="button">
                            Sign In
                        </button>
                    </a> 
                </c:if>
                <c:if test="${user != null}">
                    <div class="logined-section">
                        <i class="fa-solid fa-grip-lines-vertical"></i>
                        <a href="main?route=view-cart" class="cart-section">
                            <i class="fa-solid fa-cart-shopping cart">
                                <div id="cart-noti" class="items ${cart != null ? 'has-item' : ''}">${cart.size() > 9 ? '9+' : cart.size()}</div>
                            </i>
                        </a>
                        <div class="user">
                            <div id="user-section" class="user-section">
                                <!-- <img class="user-avatar" src="./images/82260365_1446294028868701_7217745884421816320_n.jpg"
                                    alt="avatar"> -->
                                <h6 class="username">${user.getUserName()}</h6>
                                <i id="fa-angle-down" class="fa-solid fa-angle-down user-bar"></i>
                            </div>
                            <div id="user-nav-bar" class="user-nav-bar">
                                <ul>
                                    <a class="option" href="main?route=personal-get-meal-plan">
                                        <li>My Personal Meal Plans</li>
                                    </a>
                                    <a class="option" href="main?route=customer-get-orders">
                                        <li >My Orders</li>
                                    </a>
                                    <a class="option" href="main?route=profile">
                                        <li >Setting Profile</li>
                                    </a>
                                    <a class="option" href="main?route=logout">
                                        <li >Log Out</li>
                                    </a>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </header>
    </body>
</html>
