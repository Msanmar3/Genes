/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.user;

import com.upo.genesmaven.controller.UsersJpaController;
import com.upo.genesmaven.entities.Roles;
import com.upo.genesmaven.entities.Users;
import com.upo.genesmaven.services.Sha1;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class servletCreateUserIndex extends HttpServlet {

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
        String redirect = "error.jsp";
        request.getSession().removeAttribute("errorCreateUser");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String rol = "3";
        if (((Objects.requireNonNull(password) == null ? password == null : Objects.requireNonNull(password).equals(password)) && !password.isEmpty())
                && ((Objects.requireNonNull(email) == null ? email == null : Objects.requireNonNull(email).equals(email)) && !email.isEmpty())
                && ((Objects.requireNonNull(name) == null ? name == null : Objects.requireNonNull(name).equals(name)) && !name.isEmpty())) {

            UsersJpaController ujpc = new UsersJpaController();
            Users user = ujpc.findCreateUser(email);

            if (user == null) {

                user = new Users();
                user.setUser(name);
                user.setNameUser(name);
                try {
                    user.setPassword(Sha1.sha1(password));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(servletCreateUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                user.setEmail(email);
                user.setIdRole(new Roles(Integer.parseInt(rol)));
                ujpc.create(user);
                redirect = "index.jsp";
            } else {
                request.getSession().removeAttribute("errorLogin");
                request.getSession().setAttribute("errorLogin", "El usuario ya existe.");
                redirect = "index.jsp";
            }

        } else {
            request.getSession().removeAttribute("errorCreateUser");
            request.getSession().setAttribute("errorCreateUser", "Rellene todos los campos obligatorios");
            redirect = "inicio.jsp";
        }
        response.sendRedirect(redirect);

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
