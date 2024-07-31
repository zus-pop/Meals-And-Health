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
@WebServlet({"/admin-get-weekly-detail", "/admin-add-weekly-detail-view"})
public class AdminWeeklyMealDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int weeklyId = Integer.parseInt(request.getParameter("weeklyId"));
        
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        WeeklyMeal weeklyMeal = weeklyDao.findById(weeklyId);
        
        String msg = (String) request.getSession().getAttribute("msg");
        request.getSession().removeAttribute("msg");
        
        request.setAttribute("weeklyMeal", weeklyMeal);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("admin-weekly-meal-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        MealDAO mealDao = MealDAO.getInstance();
        int id = weeklyDao.getCurrentWeeklyId();
        List<Meal> meals = mealDao.findAll();
        request.setAttribute("weeklyId", id);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("admin-create-weekly-meal-detail.jsp").forward(request, response);
    }

}
