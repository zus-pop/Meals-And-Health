package controllers;

import dao.PersonalMealPlanDAO;
import dto.PersonalMealPlan;
import dto.UserAccount;
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
@WebServlet({"/personal-get-meal-plan"})
public class PersonalMealPlanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("main?route=home");
            return;
        }
        PersonalMealPlanDAO personalDao = PersonalMealPlanDAO.getInstance();
        List<PersonalMealPlan> personalMeals = personalDao.findByCustomerId(user.getId());

        String msg = (String) request.getSession().getAttribute("msg");
        request.getSession().removeAttribute("msg");

        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        request.setAttribute("personalMeals", personalMeals);
        request.getRequestDispatcher("personal-weekly-meal-list.jsp").forward(request, response);
    }

}
