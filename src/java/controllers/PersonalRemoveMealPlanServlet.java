package controllers;

import dao.PersonalMealPlanDAO;
import dao.PersonalMealPlanDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet({"/personal-remove-meal-plan"})
public class PersonalRemoveMealPlanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personalMealId = Integer.parseInt(request.getParameter("personalMealId"));
        PersonalMealPlanDAO personalDao = PersonalMealPlanDAO.getInstance();
        PersonalMealPlanDetailDAO personalDetailDao = PersonalMealPlanDetailDAO.getInstance();
        int result;
        personalDetailDao.removeByPersonalMealPlanId(personalMealId);
        result = personalDao.remove(personalMealId);
        String msg = result > 0 ? "Success" : "Failed";
        request.getRequestDispatcher("main?route=personal-get-meal-plan").forward(request, response);
    }
    
}
