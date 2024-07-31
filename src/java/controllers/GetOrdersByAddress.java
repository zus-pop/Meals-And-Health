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
@WebServlet("/admin-get-order-by-address")
public class GetOrdersByAddress extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String city = request.getParameter("city");
            if (city == null || city.isEmpty()) {
                city = "";
            }
            String district = request.getParameter("district");
            if (district == null || district.isEmpty()) {
                district = "";
            }
            String ward = request.getParameter("ward");
            if (ward == null || ward.isEmpty()) {
                ward = "";
            }
            System.out.println(city);
            OrderDAO orderDao = OrderDAO.getInstance();
            List<Order> orders = orderDao.findByAddress(city, district, ward);
            if (orders != null) {
                request.setAttribute("orders", orders);
                request.setAttribute("searchType", "byAddress");
                request.setAttribute("searchParams", Arrays.asList(new String[]{city, district, ward}));
            }
            request.getRequestDispatcher("admin-manage-order.jsp").forward(request, response);
        }
    }
}
