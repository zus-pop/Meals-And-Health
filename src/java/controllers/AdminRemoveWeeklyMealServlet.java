package controllers;

import dao.WeeklyMealDAO;
import dao.WeeklyMealDetailDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet({"/admin-remove-weekly-meal"})
public class AdminRemoveWeeklyMealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int weeklyId = Integer.parseInt(request.getParameter("weeklyId"));
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        WeeklyMealDetailDAO weeklyDetailDao = WeeklyMealDetailDAO.getInstance();
        int result;
        weeklyDetailDao.removeByWeeklyId(weeklyId);
        result = weeklyDao.remove(weeklyId);
        String msg = result > 0 ? "Success" : "Failed";
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("main?route=admin-get-weekly-meal-view").forward(request, response);
    }
    
}
