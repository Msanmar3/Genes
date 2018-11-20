/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.login;

import com.upo.genesmaven.controller.RolesMenusJpaController;
import com.upo.genesmaven.controller.UsersJpaController;
import com.upo.genesmaven.entities.RolesMenus;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.upo.genesmaven.entities.Users;
import java.util.List;


/**
 *
 * @author Mónica
 */
public class servletLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String redirect = "error.jsp";

        if (session != null) {
            request.getSession().removeAttribute("errorLogin");
            String em = request.getParameter("email");
            String password = request.getParameter("password");
            if (((Objects.requireNonNull(em) == null ? em == null : Objects.requireNonNull(em).equals(em)) && !em.isEmpty())
                    && ((Objects.requireNonNull(password) == null ? password == null : Objects.requireNonNull(password).equals(password)) && !password.isEmpty())) {

                UsersJpaController ujpc = new UsersJpaController();
                RolesMenusJpaController rmpc = new RolesMenusJpaController();

                Users user = ujpc.findUsersLogin(em, password);

                if (user != null) {

                    user = ujpc.findUsers(user.getIdUser());
   
                    List<RolesMenus> listRolesMenu = rmpc.findRolesMenuByIdRol(user.getIdRole());
                 
                    request.getSession().removeAttribute("user");
                    request.getSession().setAttribute("user", user);
                    
                    request.getSession().removeAttribute("listRolesMenu");
                    request.getSession().setAttribute("listRolesMenu", listRolesMenu);
                    
                    request.getSession().removeAttribute("section");
                    request.getSession().setAttribute("section", "central.jsp");
                    redirect = "inicio.jsp";
                } else {
                    request.getSession().removeAttribute("errorLogin");
                    request.getSession().setAttribute("errorLogin", "Los datos son incorrectos.");
                    redirect = "index.jsp";

                }

            } else {
                request.getSession().removeAttribute("errorLogin");
                request.getSession().setAttribute("errorLogin", "Introduzca usuario y contraseña.");
                redirect = "index.jsp";
            }
           response.sendRedirect(redirect);

        }
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
