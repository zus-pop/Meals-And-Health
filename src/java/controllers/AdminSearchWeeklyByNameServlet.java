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
@WebServlet({"/admin-search-weekly-by-name"})
public class AdminSearchWeeklyByNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        List<WeeklyMeal> weeklyMeals = weeklyDao.findByName(name);
        String msg;
        if (weeklyMeals.isEmpty()) {
            msg = "0 Result!";
            request.setAttribute("msg", msg);
        }
        
        request.setAttribute("weeklyMeals", weeklyMeals);
        request.setAttribute("nameBack", name);
        request.getRequestDispatcher("admin-weekly-meal-list.jsp").forward(request, response);
    }
    
}
