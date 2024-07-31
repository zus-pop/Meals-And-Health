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
@WebServlet({"/customer-remove-item-cart"})
public class CustomerRemoveItemCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mealId = Integer.parseInt(request.getParameter("mealId"));
        Map<Meal, Integer> cart = (Map<Meal, Integer>) request.getSession().getAttribute("cart");
        boolean result = false;
        int size = 0;
        double totalCost = 0;
        if (cart != null) {
            result = cart.keySet()
                    .removeIf(meal -> meal.getId() == mealId);
            if (cart.isEmpty()) {
                request.getSession().removeAttribute("cart");
            } else {
                request.getSession().setAttribute("cart", cart);
            }
            totalCost = Util.getTotalCost(cart);
            size = cart.size();
        }
        response.getWriter().println("{"
                + "\"result\": " + result + ","
                + "\"totalCost\": " + totalCost + ","
                + "\"size\": " + size
                + "}");
    }

}
