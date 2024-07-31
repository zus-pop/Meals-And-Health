package controllers;

import dao.WeeklyMealDAO;
import dto.WeeklyMeal;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet({"/search-weekly-by-name"})
public class SearchWeeklyMealByNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("search");
        WeeklyMealDAO weeklyDao = WeeklyMealDAO.getInstance();
        List<WeeklyMeal> weeklyMeals = weeklyDao.findByName(name);
        String msg;
        System.out.println(weeklyMeals.isEmpty());
        if (weeklyMeals.isEmpty()) {
            msg = "Empty result!";
            request.setAttribute("msg", msg);
        }
        if (weeklyMeals.size() >= 3) {
            Cookie cookie = new Cookie("weeklyKeyword", URLEncoder.encode(name, "UTF-8"));
            cookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(cookie);
        }
        request.setAttribute("weeklyMeals", weeklyMeals);
        request.setAttribute("nameBack", name);
        request.getRequestDispatcher("weekly-meal-list.jsp").forward(request, response);
    }

}
