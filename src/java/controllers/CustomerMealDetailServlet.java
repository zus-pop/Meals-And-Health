package controllers;

import dao.MealDAO;
import dao.MealIngredientDAO;
import dto.Meal;
import dto.MealIngredient;
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
@WebServlet({"/customer-meal-detail-view"})
public class CustomerMealDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mealId = Integer.parseInt(request.getParameter("mealId"));
        MealDAO mealDao = MealDAO.getInstance();
        MealIngredientDAO ingsOfMealDao = MealIngredientDAO.getInstance();
        
        Meal meal = mealDao.findById(mealId);
        List<MealIngredient> ingsOfMeal = ingsOfMealDao.findByMealId(mealId);
        request.setAttribute("meal", meal);
        request.setAttribute("ingsOfMeal", ingsOfMeal);
        request.getRequestDispatcher("customer-meal-detail.jsp").forward(request, response);
    }
    
}
