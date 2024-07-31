package controllers;

import dao.MealDAO;
import dto.Meal;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HUUPHUOC
 */
@WebServlet("/search-meal-by-name")
public class GetMealByNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String mealKeyword = request.getParameter("search");
        MealDAO mealDao = MealDAO.getInstance();
        List<Meal> meals = mealDao.findByName(mealKeyword);
        if (!meals.isEmpty()) {
            request.setAttribute("meals", meals);
            request.setAttribute("nameBack", mealKeyword);
            if (meals.size() >= 6) {
                Cookie cookie = new Cookie("mealKeyword", URLEncoder.encode(mealKeyword, "UTF-8"));
                cookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(cookie);
            }
            request.getRequestDispatcher("customer-meal-list.jsp").forward(request, response);
        } else {
            String msg = "Couldn't find any Meal!";
            request.setAttribute("notFound", msg);
            request.setAttribute("nameBack", mealKeyword);
            request.getRequestDispatcher("customer-meal-list.jsp").forward(request, response);
        }
    }
}
