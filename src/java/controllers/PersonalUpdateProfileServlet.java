package controllers;

import dao.UserAccountDAO;
import dto.UserAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Util;

/**
 *
 * @author hoang
 */
@WebServlet({"/personal-update-profile"})
public class PersonalUpdateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = Util.decode(request.getParameter("name"));
        String phone = request.getParameter("phone");
        UserAccountDAO userDao = UserAccountDAO.getInstance();
        UserAccount user = (UserAccount) request.getSession().getAttribute("user");
        
        if (phone == null || phone.isEmpty()) {
            phone = null;
        }
        System.out.println(phone);
        int result = userDao.update(user, name.trim(), phone);
        if (result > 0) {
            user.setUserName(name.trim());
            user.setPhone(phone);
        }
        
        String msg = result > 0 ? "Update Success" : "Update Failed";
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("setting-user-profile.jsp").forward(request, response);
    }

}
