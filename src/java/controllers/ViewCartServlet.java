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
@WebServlet({"/view-cart"})
public class ViewCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Meal, Integer> cart = (Map<Meal, Integer>) request.getSession().getAttribute("cart");
        double totalCost = 0;
        if (cart != null) {
            totalCost = Util.getTotalCost(cart);
        }
        request.setAttribute("totalCost", totalCost);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

}
