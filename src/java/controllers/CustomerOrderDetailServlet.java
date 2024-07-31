package controllers;

import dao.OrderDAO;
import dto.Order;
import dto.UserAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet({"/customer-get-order-detail"})
public class CustomerOrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("main?route=home").forward(request, response);
            return;
        }
        
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDAO orderDao = OrderDAO.getInstance();
        Order order = orderDao.findById(orderId);
        request.setAttribute("order", order);
        request.getRequestDispatcher("customer-order-detail.jsp").forward(request, response);
    }
    
}
