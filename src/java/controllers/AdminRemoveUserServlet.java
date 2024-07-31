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
@WebServlet({"/admin-remove-user"})
public class AdminRemoveUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String search = request.getParameter("search");
        String deleteType = request.getParameter("deleteType");
        UserAccountDAO userDao = UserAccountDAO.getInstance();
        if (deleteType.equals("hard")) {
            userDao.removeUserHard(Integer.parseInt(id.trim()));
            response.sendRedirect("main?route=admin-manage-user-bin-view");
        } else {
            userDao.removeUserSoft(Integer.parseInt(id.trim()));
            response.sendRedirect("main?route=admin-search-user&search=" + search);
        }
    }
}
