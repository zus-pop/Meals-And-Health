package controllers;

import dao.WeeklyMealDAO;
import dto.UserAccount;
import dto.WeeklyMeal;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.Util;

/**
 *
 * @author hoang
 */
@WebServlet({"/admin-get-weekly-meal-view", "/admin-create-weekly-meal"})
@MultipartConfig
public class AdminWeeklyMealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("main?route=home").forward(request, response);
        }
        WeeklyMealDAO weeklyMealDao = WeeklyMealDAO.getInstance();
        List<WeeklyMeal> weeklyMeals = weeklyMealDao.findAll();

        String msg = (String) request.getSession().getAttribute("msg");
        request.getSession().removeAttribute("msg");

        request.setAttribute("weeklyMeals", weeklyMeals);
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        request.getRequestDispatcher("admin-weekly-meal-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String weeklyMealName = request.getParameter("name");
        String description = request.getParameter("des");
        String image = "";
        Part part = request.getPart("image");
        String path = "F:\\PRJ301\\Meals-And-Health\\web\\images\\weekly-meals\\";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!part.getSubmittedFileName().isEmpty()) {
            String imagePath = path + part.getSubmittedFileName();
            part.write(imagePath);
            image = "images/weekly-meals/" + part.getSubmittedFileName();
        }
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        weeklyDao.persist(Util.decode(weeklyMealName), Util.decode(description), image);
        request.getRequestDispatcher("main?route=admin-add-weekly-detail-view").forward(request, response);
    }
}
