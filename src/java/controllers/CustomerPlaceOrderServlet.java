package controllers;

import dao.OrderDAO;
import dto.Meal;
import dto.UserAccount;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Address;
import utils.Util;

/**
 *
 * @author hoang
 */
@WebServlet({"/customer-place-order"})
public class CustomerPlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = Util.decode(request.getParameter("city"));
        String district = Util.decode(request.getParameter("district"));
        String ward = Util.decode(request.getParameter("ward"));
        String street = Util.decode(request.getParameter("street"));
        
        Address deliveryAddress = new Address(street, ward, district, city);
        int shipperId = Integer.parseInt(request.getParameter("shipper"));
        UserAccount customer = (UserAccount) request.getSession().getAttribute("user");
        Map<Meal, Integer> cart = (Map<Meal, Integer>) request.getSession().getAttribute("cart");
        
        OrderDAO orderDao = OrderDAO.getInstance();
        int result = orderDao.placeOrder(customer, shipperId, deliveryAddress, cart);
        if (result > 0) {
            request.getSession().removeAttribute("cart");
        }
        response.sendRedirect("main?route=customer-get-orders");
    }
    
}
