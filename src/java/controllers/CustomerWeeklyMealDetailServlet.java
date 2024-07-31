package controllers;

import dao.PersonalMealPlanDAO;
import dao.PersonalMealPlanDetailDAO;
import dao.WeeklyMealDAO;
import dao.WeeklyMealDetailDAO;
import dto.PersonalMealPlanDetail;
import dto.UserAccount;
import dto.WeeklyMeal;
import dto.WeeklyMealDetail;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet({"/customer-weekly-meal-detail-view", "/customer-fetch-weekly-meal"})
public class CustomerWeeklyMealDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int weeklyId = Integer.parseInt(request.getParameter("weeklyId"));

        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        WeeklyMeal weeklyMeal = weeklyDao.findById(weeklyId);

        request.setAttribute("weeklyMeal", weeklyMeal);
        request.getRequestDispatcher("weekly-meal-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserAccount customer = (UserAccount) request.getSession().getAttribute("user");
        if (customer == null) {
            response.sendRedirect("main?route=login");
            return;
        }
        
        int weeklyId = Integer.parseInt(request.getParameter("weeklyId"));
        
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        WeeklyMealDetailDAO weeklyDetailDao = WeeklyMealDetailDAO.getInstance();
        PersonalMealPlanDAO personalDao = PersonalMealPlanDAO.getInstance();
        PersonalMealPlanDetailDAO personalDetailDao = PersonalMealPlanDetailDAO.getInstance();

        WeeklyMeal weeklyMeal = weeklyDao.findById(weeklyId);
        List<WeeklyMealDetail> weeklyList = weeklyDetailDao.getMealListById(weeklyId);
        
        personalDao.persist(weeklyMeal.getName(), weeklyMeal.getDescription(), weeklyMeal.getImage(), customer.getId());
        int currentPersonalMealPlanId = personalDao.getCurrentPersonalMealPlanId();
        
        List<PersonalMealPlanDetail> personalList = weeklyList.stream()
                .map(detail -> new PersonalMealPlanDetail(currentPersonalMealPlanId, detail.getMeal(), detail.getType(), detail.getDaysOfTheWeek()))
                .collect(Collectors.toList());
        
        int result = personalDetailDao.persist(personalList);
        String msg = result > 0 ? "Added successfully" : "Add failed";
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("main?route=personal-get-meal-plan");
    }
}
