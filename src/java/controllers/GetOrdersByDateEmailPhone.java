package controllers;

import dao.OrderDAO;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet("/admin-get-order-by-date-contact")
public class GetOrdersByDateEmailPhone extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String startDateString = request.getParameter("startDate");
            Date startDate;
            try {
                if (startDateString == null || startDateString.isEmpty()) {
                    startDate = Date.valueOf("1900-01-01");
                } else {
                    startDate = Date.valueOf(startDateString);
                }
            } catch (IllegalArgumentException e) {
                startDate = Date.valueOf("1900-01-01");
            }

            String endDateString = request.getParameter("endDate");
            Date endDate;
            try {
                if (endDateString == null || endDateString.isEmpty()) {
                    endDate = new Date(System.currentTimeMillis());
                } else {
                    endDate = Date.valueOf(endDateString);
                }
            } catch (IllegalArgumentException e) {
                endDate = new Date(System.currentTimeMillis());
            }

            String contact = request.getParameter("contact");

            OrderDAO orderDao = OrderDAO.getInstance();
            List<Order> orders;

            if (contact == null || contact.isEmpty()) {
                orders = orderDao.findByDate(startDate, endDate);
            } else {
                orders = orderDao.findByDateContact(startDate, endDate, contact.trim());
                request.setAttribute("contactBack", contact.trim());
            }

            request.setAttribute("orders", orders);
            request.setAttribute("startDateBack", startDateString);
            request.setAttribute("endDateBack", endDateString);
            request.setAttribute("searchType", "byDateContact");
            request.setAttribute("searchParams", Arrays.asList(new String[]{startDateString, endDateString, contact}));
            if (orders == null || orders.isEmpty()) {
                request.setAttribute("emptyMessage", "No orders found.");
            }
            request.getRequestDispatcher("admin-manage-order.jsp").forward(request, response);
        }
    }
}
