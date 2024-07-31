package controllers;

import dao.MealDAO;
import dao.WeeklyMealDAO;
import dto.Meal;
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
@WebServlet({"/home"})
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mealKeyword = (String) request.getAttribute("mealKeyword");
        String weeklyKeyword = (String) request.getAttribute("weeklyKeyword");
        MealDAO mealDao = MealDAO.getInstance();
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        
        List<Meal> meals = mealDao.findBySuggestedByCookie(mealKeyword);
        List<WeeklyMeal> weeklyMeals = weeklyDao.findBySuggestedCookie(weeklyKeyword);
        
        request.setAttribute("meals", meals);
        request.setAttribute("weeklyMeals", weeklyMeals);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
