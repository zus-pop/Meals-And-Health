package controllers;

import dao.PersonalMealPlanDAO;
import dto.PersonalMealPlan;
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
@WebServlet({"/personal-get-meal-plan-detail"})
public class PersonalMealPlanDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personalMealId = Integer.parseInt(request.getParameter("personalMealId"));
        PersonalMealPlanDAO personalDao = PersonalMealPlanDAO.getInstance();
        PersonalMealPlan personalMeal = personalDao.findById(personalMealId);
        
        String msg = (String) request.getSession().getAttribute("msg");
        request.getSession().removeAttribute("msg");
        
        request.setAttribute("personalMeal", personalMeal);
        request.setAttribute("msg", msg);
        
        request.getRequestDispatcher("personal-weekly-meal-detail.jsp").forward(request, response);
    }
    
}
