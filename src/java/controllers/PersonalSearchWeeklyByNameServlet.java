package controllers;

import dao.PersonalMealPlanDAO;
import dto.PersonalMealPlan;
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
@WebServlet({"/personal-search-weekly-by-name"})
public class PersonalSearchWeeklyByNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        PersonalMealPlanDAO personalDao = PersonalMealPlanDAO.getInstance();
        List<PersonalMealPlan> personalMeals = personalDao.findByName(name);
        String msg;
        if (personalMeals.isEmpty()) {
            msg = "0 Result!";
            request.setAttribute("msg", msg);
        }
        request.setAttribute("personalMeals", personalMeals);
        request.setAttribute("nameBack", name);
        request.getRequestDispatcher("personal-weekly-meal-list.jsp").forward(request, response);
    }

}
