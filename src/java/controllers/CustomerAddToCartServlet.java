package controllers;

import dao.MealDAO;
import dto.Meal;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet({"/customer-add-to-cart"})
public class CustomerAddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        if (request.getSession().getAttribute("user") == null) {
            response.getWriter().println(
                    "{"
                    + "\"result\": " + 0
                    + "}");
            return;
        }

        int mealId = Integer.parseInt(request.getParameter("mealId"));
        String quantity = request.getParameter("quantity");

        Meal selectedMeal = MealDAO.getInstance().findById(mealId);
        Map<Meal, Integer> cart = (Map<Meal, Integer>) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            if (quantity != null) {
                cart.put(selectedMeal, Integer.parseInt(quantity));
            } else {
                cart.put(selectedMeal, 1);
            }
        } else {
            Meal meal = cart.keySet()
                    .stream()
                    .filter(m -> m.getId() == selectedMeal.getId())
                    .findFirst()
                    .orElse(null);
            if (meal != null) {
                if (quantity != null) {
                    cart.put(meal, cart.get(meal) + Integer.parseInt(quantity));
                } else {
                    cart.put(meal, cart.get(meal) + 1);
                }
            } else {
                if (quantity != null) {
                    cart.put(selectedMeal, Integer.parseInt(quantity));
                } else {
                    cart.put(selectedMeal, 1);
                }
            }
        }

        request.getSession().setAttribute("cart", cart);
        response.getWriter().println("{"
                + "\"result\": " + 1 + ","
                + "\"size\": " + cart.size()
                + "}");
//        request.getRequestDispatcher("main?route=customer-meal-view").forward(request, response);
    }

}
