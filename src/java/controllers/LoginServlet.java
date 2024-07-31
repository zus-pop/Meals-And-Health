package controllers;

import dao.UserAccountDAO;
import dto.UserAccount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoang
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("type", "Login");
        request.getRequestDispatcher("login-register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserAccountDAO userDao = UserAccountDAO.getInstance();
        UserAccount user = userDao.findByEmailAndPassword(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("main?route=home");
        } else {
            String msg = "User does not exist!";
            request.setAttribute("type", "Login");
            request.setAttribute("invalidUser", msg);
            request.setAttribute("emailBack", email);
            request.setAttribute("passwordBack", password);
            request.getRequestDispatcher("login-register.jsp").forward(request, response);
        }
    }

}
