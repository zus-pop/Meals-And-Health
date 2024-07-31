package controllers;

import dao.PersonalMealPlanDAO;
import dto.PersonalMealPlan;
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
@WebServlet({"/personal-get-update-meal-plan-view", "/personal-update-meal-plan"})
@MultipartConfig
public class UpdatePersonalMealPlanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personalMealId = Integer.parseInt(request.getParameter("personalMealId"));
        PersonalMealPlanDAO personalDao = PersonalMealPlanDAO.getInstance();
        PersonalMealPlan personalMeal = personalDao.findById(personalMealId);

        request.setAttribute("personalMealId", personalMealId);
        request.setAttribute("name", personalMeal.getName());
        request.setAttribute("des", personalMeal.getDescription());

        request.getRequestDispatcher("personal-edit-weekly-meal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personalMealId = Integer.parseInt(request.getParameter("personalMealId"));
        String name = Util.decode(request.getParameter("name"));
        String des = Util.decode(request.getParameter("des"));
        Part imagePart = request.getPart("image");
        String path = "F:\\PRJ301\\Meals-And-Health\\web\\images\\weekly-meals\\";
        String image;
        int result;
        
        PersonalMealPlanDAO personalDao = PersonalMealPlanDAO.getInstance();
        
         if (!imagePart.getSubmittedFileName().isEmpty()) {
            String imagePath = path + imagePart.getSubmittedFileName();
            imagePart.write(imagePath);
            image = "images/weekly-meals/" + imagePart.getSubmittedFileName();
            result = personalDao.update(personalMealId, name, des, image);
        } else {
            result = personalDao.update(personalMealId, name, des);
        }
         
         String msg = result > 0 ? "Success" : "Failed";
         request.getSession().setAttribute("msg", msg);
         
         response.sendRedirect("main?route=personal-get-meal-plan");
    }

}
