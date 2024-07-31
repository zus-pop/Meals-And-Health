package controllers;

import dao.WeeklyMealDAO;
import dto.WeeklyMeal;
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
@WebServlet("/customer-weekly-meal-view")
public class CustomerWeeklyMealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WeeklyMealDAO weeklyMealDao = WeeklyMealDAO.getInstance();
        List<WeeklyMeal> weeklyMeals = weeklyMealDao.findAll();
        request.setAttribute("weeklyMeals", weeklyMeals);
        request.getRequestDispatcher("weekly-meal-list.jsp").forward(request, response);
    }

    
}
