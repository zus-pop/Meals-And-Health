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
        <title>Update Weekly Meal</title>
        <link rel="stylesheet" href="./css/create-weekly-meal.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="create-section">
                <h2 class="title">Update Weekly Meal</h2>
                <form class="form" action="main" method="post" enctype="multipart/form-data">
                    <input name="weeklyId" value="${requestScope.weeklyId}" hidden/>
                    <div class="name-field">
                        <label for="weekly-meal">Weekly Meal Name</label>
                        <input required placeholder="Enter name" name="name" type="text" value="${requestScope.name}">
                    </div>
                    <div class="des-field">
                        <label for="des">Description</label>
                        <textarea rows="5" placeholder="Enter description" name="des" id="des">${requestScope.des}</textarea>
                    </div>
                    <div class="img-field">
                        <label for="image">Weekly Meal Image</label>
                        <input name="image" type="file" />
                    </div>
                    <button class="button" name="route" value="admin-update-weekly-meal" type="submit">Update</button>
                </form>
            </div>
        </div>
    </body>
</html>
