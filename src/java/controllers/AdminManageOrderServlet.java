package controllers;

import dao.OrderDAO;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HUUPHUOC
 */
@WebServlet("/admin-manage-order-view")
public class AdminManageOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            OrderDAO orderDao = OrderDAO.getInstance();
            List<Order> orders = orderDao.findAll();
            if (orders != null) {
                request.setAttribute("orders", orders);
                request.setAttribute("searchType", "nothing");
                request.setAttribute("searchParams", Arrays.asList(new String[]{}));
            } else {
                request.setAttribute("emptyMessage", "No orders found!");
            }
            request.getRequestDispatcher("admin-manage-order.jsp").forward(request, response);
        }
    }

}
