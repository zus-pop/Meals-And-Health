package controllers;

import dao.UserAccountDAO;
import dto.UserAccount;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet({"/admin-manage-user-view", "/admin-search-user"})
public class AdminManageUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String search = request.getParameter("search");
            if (search == null || search.isEmpty()) {
                search = "";
            } else {
                request.setAttribute("searchBack", search);
            }
            UserAccountDAO userDao = UserAccountDAO.getInstance();
            List<UserAccount> users = userDao.getAllUsers(search);
            if (!users.isEmpty()) {
                request.setAttribute("users", users);
                request.getRequestDispatcher("admin-manage-user.jsp").forward(request, response);
            } else {
                String msg = "Empty List!!";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("admin-manage-user.jsp").forward(request, response);
            }
        }
    }
}
