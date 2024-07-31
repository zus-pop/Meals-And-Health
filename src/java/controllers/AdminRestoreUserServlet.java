package controllers;

import dao.UserAccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HUUPHUOC
 */
@WebServlet("/admin-restore-user")
public class AdminRestoreUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        UserAccountDAO userDao = UserAccountDAO.getInstance();
        int result = userDao.restoreUser(Integer.parseInt(id.trim()));
        response.sendRedirect("main?route=admin-manage-user-bin-view");
    }
}
