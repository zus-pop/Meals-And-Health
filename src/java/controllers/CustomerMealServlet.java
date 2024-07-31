package controllers;

import dao.MealDAO;
import dto.Meal;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet({"/customer-meal-view"})
public class CustomerMealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MealDAO mealDao = MealDAO.getInstance();
        List<Meal> meals = mealDao.findAll();
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("customer-meal-list.jsp").forward(request, response);
    }
}
