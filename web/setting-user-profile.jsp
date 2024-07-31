<%-- 
    Document   : create-weekly-meal
    Created on : Jun 16, 2024, 2:23:25 AM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Weekly Meal</title>
        <link rel="stylesheet" href="./css/setting-user-profile.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="create-section">
                <h2 class="title">${sessionScope.user.getUserName()}'s Profile</h2>
                <form class="form" action="main" method="post">
                    <div class="name-field">
                        <label for="user-name">User name</label>
                        <input required placeholder="Enter name" name="name" type="text" value="${sessionScope.user.getUserName()}">
                    </div>
                    <div class="phone-field">
                        <label for="phoneNumber">Phone Number</label>
                        <input maxlength="10" placeholder="Enter phone number" name="phone" type="tel" value="${sessionScope.user.getPhone()}">
                    </div>

                    <button class="button" name="route" value="personal-update-profile" type="submit">Update</button>
                </form>
                        <h3 style="text-align: center">${requestScope.msg}</h3>
            </div>
        </div>
    </body>
</html>
