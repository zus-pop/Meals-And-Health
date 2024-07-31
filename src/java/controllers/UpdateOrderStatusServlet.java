package controllers;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HUUPHUOC
 */
@WebServlet("/admin-update-order-status")
public class UpdateOrderStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("orderId");
            String newstatus = request.getParameter("newStatus");
            OrderDAO orderDao = OrderDAO.getInstance();
            int result = orderDao.updateStatus(Integer.parseInt(id.trim()), Integer.parseInt(newstatus.trim()));
            // Lấy loại tìm kiếm và các tham số tìm kiếm từ request
            String searchType = request.getParameter("searchType");
            String[] searchParams = request.getParameterValues("searchParams");
            if (null == searchType) {
                response.sendRedirect("main?route=admin-manage-order-view");
            } else // Điều hướng về trang tìm kiếm tương ứng với các tham số tìm kiếm ban đầu
            switch (searchType) {
                case "byAddress":
                    response.sendRedirect("main?route=admin-get-order-by-address&city=" + searchParams[0] + "&district=" + searchParams[1] + "&ward" + searchParams[2]);
                    break;
                case "byDateContact":
                    response.sendRedirect("main?route=admin-get-order-by-date-contact&startDate=" + searchParams[0] + "&endDate=" + searchParams[1] + "&contact=" + searchParams[2]);
                    break;
                default:
                    response.sendRedirect("main?route=admin-manage-order-view");
                    break;
            }
        }
    }
}
