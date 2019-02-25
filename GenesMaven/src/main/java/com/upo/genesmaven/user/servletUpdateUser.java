/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.user;

import com.upo.genesmaven.controller.UsersJpaController;
import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Roles;
import com.upo.genesmaven.entities.Users;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class servletUpdateUser extends HttpServlet {

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
            throws ServletException, IOException, NonexistentEntityException, Exception {
        HttpSession session = request.getSession(false);
        String redirect = "error.jsp";

        if (session != null) {
            request.getSession().removeAttribute("errorUpdateUser");
            Integer id = Integer.parseInt(request.getParameter("idUser"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String rol = request.getParameter("rol");
            if (((Objects.requireNonNull(email) == null ? email == null : Objects.requireNonNull(email).equals(email)) && !email.isEmpty())
                    && ((Objects.requireNonNull(rol) == null ? rol == null : Objects.requireNonNull(rol).equals(rol)) && !rol.isEmpty())
                    && ((Objects.requireNonNull(name) == null ? name == null : Objects.requireNonNull(name).equals(name)) && !name.isEmpty())) {

                UsersJpaController ujpc = new UsersJpaController();
                Users user = ujpc.findUsers(id);
                user.setUser(name);
                user.setNameUser(name);
                user.setEmail(email);
                user.setIdRole(new Roles(Integer.parseInt(rol)));

                ujpc.edit(user);
                /*  request.getSession().removeAttribute("usersList");
                    request.getSession().setAttribute("usersList", ujpc.findUsersEntities());
                    request.getSession().removeAttribute("section");
                    request.getSession().setAttribute("section", "sectionListUsers.jsp");
                    redirect = "inicio.jsp";*/
                redirect = "servletListUsers";

            } else {
                request.getSession().removeAttribute("errorUpdateUser");
                request.getSession().setAttribute("errorUpdateUser", "Rellene todos los campos obligatorios");
//                redirect = "inicio.jsp";
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(servletUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(servletUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
