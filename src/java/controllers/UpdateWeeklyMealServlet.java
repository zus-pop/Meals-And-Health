package controllers;

import dao.WeeklyMealDAO;
import dto.WeeklyMeal;
import java.io.IOException;
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
@WebServlet({"/admin-get-update-weekly-view", "/admin-update-weekly-meal"})
@MultipartConfig
public class UpdateWeeklyMealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int weeklyId = Integer.parseInt(request.getParameter("weeklyId"));
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        WeeklyMeal weekly = weeklyDao.findById(weeklyId);

        request.setAttribute("weeklyId", weeklyId);
        request.setAttribute("name", weekly.getName());
        request.setAttribute("des", weekly.getDescription());

        request.getRequestDispatcher("admin-edit-weekly-meal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int weeklyId = Integer.parseInt(request.getParameter("weeklyId"));
        String name = Util.decode(request.getParameter("name").trim());
        String des = Util.decode(request.getParameter("des").trim());
        Part part = request.getPart("image");
        String path = "F:\\PRJ301\\Meals-And-Health\\web\\images\\weekly-meals\\";
        String image;
        int result;

        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();

        if (!part.getSubmittedFileName().isEmpty()) {
            String imagePath = path + part.getSubmittedFileName();
            part.write(imagePath);
            image = "images/weekly-meals/" + part.getSubmittedFileName();
            result = weeklyDao.update(weeklyId, name, des, image);
        } else {
            result = weeklyDao.update(weeklyId, name, des);
        }

        String msg = result > 0 ? "Success" : "Failed";
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("main?route=admin-get-weekly-meal-view");
    }

}
