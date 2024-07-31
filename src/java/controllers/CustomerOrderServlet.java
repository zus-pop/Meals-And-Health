package controllers;

import dao.OrderDAO;
import dto.Order;
import dto.UserAccount;
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
@WebServlet({"/customer-get-orders"})
public class CustomerOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("main?route=home").forward(request, response);
            return;
        }
        
        OrderDAO orderDao = OrderDAO.getInstance();
        
        List<Order> customerOrders = orderDao.findByCustomerId(user.getId());
        
        request.setAttribute("customerOrders", customerOrders);
        request.getRequestDispatcher("customer-order-list.jsp").forward(request, response);
    }
    
}
