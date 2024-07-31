package controllers;

import dto.Meal;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Util;

/**
 *
 * @author hoang
 */
@WebServlet({"/customer-update-cart"})
public class CustomerUpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int mealId = Integer.parseInt(request.getParameter("mealId"));
        Map<Meal, Integer> cart = (Map<Meal, Integer>) request.getSession().getAttribute("cart");
        double totalCost = 0;
        if (cart != null) {
            Meal meal = cart.keySet()
                    .stream()
                    .filter(m -> m.getId() == mealId)
                    .findFirst()
                    .orElse(null);
            if (meal != null) {
                cart.put(meal, quantity);
            }
            totalCost = Util.getTotalCost(cart);
        }
        request.getSession().setAttribute("cart", cart);
        response.getWriter().println(
                "{"
                + " \"quantity\": " + quantity + ","
                + "\"mealId\": " + mealId + ","
                + "\"totalCost\": " + totalCost
                + "}");
    }

}
