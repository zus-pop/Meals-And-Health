package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@MultipartConfig
public class MainServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url;
        String route = request.getParameter("route");
        System.out.println(route);
        if (route == null) {
            route = "home";
        }
        System.out.println(request.getSession().getId());
        switch(route) {
                case "home":
                    url = "home";
                    break;
                case "login":
                    url = "login";
                    break;
                case "logout":
                    url = "logout";
                    break;
                case "register":
                    url = "register";
                    break;
                case "staff":
                    url = "dashboard.jsp";
                    break;
                case "view-cart":
                    url = "view-cart";
                    break;
                case "customer-meal-view":
                    url = "customer-meal-view";
                    break;
                case "customer-meal-detail-view":
                    url = "customer-meal-detail-view";
                    break;
                case "search-meal-by-name":
                    url = "search-meal-by-name";
                    break;
                case "customer-add-to-cart":
                    url = "customer-add-to-cart";
                    break;
                case "customer-update-cart":
                    url = "customer-update-cart";
                    break;
                case "customer-remove-item-cart":
                    url = "customer-remove-item-cart";
                    break;
                case "customer-place-order":
                    url = "customer-place-order";
                    break;
                case "customer-get-orders":
                    url = "customer-get-orders";
                    break;
                case "customer-get-order-detail":
                    url = "customer-get-order-detail";
                    break;
                case "customer-weekly-meal-view":
                    url = "customer-weekly-meal-view";
                    break;
                case "search-weekly-by-name":
                    url = "search-weekly-by-name";
                    break;
                case "customer-weekly-meal-detail-view":
                    url = "customer-weekly-meal-detail-view";
                    break;
                case "customer-fetch-weekly-meal":
                    url = "customer-fetch-weekly-meal";
                    break;
                case "profile":
                    url = "setting-user-profile.jsp";
                    break;
                case "personal-update-profile":
                    url = "personal-update-profile";
                    break;
                case "personal-get-meal-plan":
                    url = "personal-get-meal-plan";
                    break;
                case "personal-search-weekly-by-name":
                    url = "personal-search-weekly-by-name";
                    break;
                case "personal-get-meal-plan-detail":
                    url = "personal-get-meal-plan-detail";
                    break;
                case "personal-get-update-meal-plan-view":
                    url = "personal-get-update-meal-plan-view";
                    break;
                case "personal-update-meal-plan":
                    url = "personal-update-meal-plan";
                    break;
                case "personal-get-update-meal-plan-detail-view":
                    url = "personal-get-update-meal-plan-detail-view";
                    break;
                case "personal-update-meal-plan-detail":
                    url = "personal-update-meal-plan-detail";
                    break;
                case "personal-remove-meal-plan":
                    url = "personal-remove-meal-plan";
                    break;
                case "admin-manage-user-view":
                    url = "admin-manage-user-view";
                    break;
                case "admin-manage-user-bin-view":
                    url = "admin-manage-user-bin-view";
                    break;
                case "admin-search-user":
                    url = "admin-search-user";
                    break;
                case "admin-remove-user":
                    url = "admin-remove-user";
                    break;
                case "admin-restore-user":
                    url = "admin-restore-user";
                    break;
                case "admin-manage-order-view":
                    url = "admin-manage-order-view";
                    break;
                case "admin-manage-order-detail":
                    url = "admin-manage-order-detail";
                    break;
                case "admin-get-order-by-address":
                    url = "admin-get-order-by-address";
                    break;
                case "admin-get-order-by-date-contact":
                    url = "admin-get-order-by-date-contact";
                    break;
                case "admin-update-order-status":
                    url = "admin-update-order-status";
                    break;
                case "admin-get-weekly-meal-view":
                    url = "admin-get-weekly-meal-view";
                    break;
                case "admin-search-weekly-by-name":
                    url = "admin-search-weekly-by-name";
                    break;
                case "admin-get-create-weekly-meal-view":
                    url = "admin-create-weekly-meal.jsp";
                    break;
                case "admin-create-weekly-meal":
                    url = "admin-create-weekly-meal";
                    break;
                case "admin-add-weekly-detail-view":
                    url = "admin-add-weekly-detail-view";
                    break;
                case "admin-get-weekly-detail":
                    url = "admin-get-weekly-detail";
                    break;
                case "admin-add-weekly-meal-detail":
                    url = "admin-add-weekly-meal-detail";
                    break;
                case "admin-get-update-weekly-view":
                    url = "admin-get-update-weekly-view";
                    break;
                case "admin-update-weekly-meal":
                    url = "admin-update-weekly-meal";
                    break;
                case "admin-get-update-weekly-detail-view":
                    url = "admin-get-update-weekly-detail-view";
                    break;
                case "admin-update-weekly-detail":
                    url = "admin-update-weekly-detail";
                    break;
                case "admin-remove-weekly-meal":
                    url = "admin-remove-weekly-meal";
                    break;
                default:
                    url = "error.jsp";
                    break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
