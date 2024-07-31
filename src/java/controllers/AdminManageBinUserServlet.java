package controllers;

import dao.UserAccountDAO;
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
 * @author HUUPHUOC
 */
@WebServlet("/admin-manage-user-bin-view")
public class AdminManageBinUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserAccountDAO userDao = UserAccountDAO.getInstance();
        List<UserAccount> list = userDao.getAllUsersForBin();
        if (!list.isEmpty()) {
            request.setAttribute("binUsers", list);
        } else {
            request.setAttribute("msg", "Empty!!");
        }
        request.getRequestDispatcher("admin-manage-user-bin.jsp").forward(request, response);
    }
}
