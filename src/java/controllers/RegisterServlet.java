/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("type", "Register");
        request.getRequestDispatcher("login-register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re-password");
        UserAccountDAO userDao = UserAccountDAO.getInstance();
        UserAccount userEmailCheck = userDao.findByEmail(email);
        if (userEmailCheck == null) {
            userDao.persist(email, Util.decode(userName), rePassword);
            String msg = "Sign up successfully!";
            request.setAttribute("type", "Login");
            request.setAttribute("emailBack", email);
            request.setAttribute("passwordBack", password);
            request.setAttribute("created", msg);
            request.getRequestDispatcher("login-register.jsp").forward(request, response);
        } else {
            request.setAttribute("type", "Register");
            String msg = "Your email has been used!";
            request.setAttribute("userExisted", msg);
            request.setAttribute("registerEmailBack", email);
            request.setAttribute("registerUsernameBack", Util.decode(userName));
            request.setAttribute("registerPasswordBack", password);
            request.setAttribute("registerRePasswordBack", rePassword);
            request.getRequestDispatcher("login-register.jsp").forward(request, response);
        }
    }

}
