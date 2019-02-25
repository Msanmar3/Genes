/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.user;

import com.upo.genesmaven.controller.UsersJpaController;
import com.upo.genesmaven.controller.exceptions.IllegalOrphanException;
import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
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
public class servletDeleteUser extends HttpServlet {

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
            throws ServletException, IOException, IllegalOrphanException {
        HttpSession session = request.getSession(false);
        String redirect;
        if (session != null) {
            String idUser = request.getParameter("idUserUrlDelete"); 
            if ((Objects.requireNonNull(idUser) == null ? idUser == null
                    : Objects.requireNonNull(idUser).equals(idUser)) && !idUser.isEmpty()) {

                UsersJpaController ujpc = new UsersJpaController();

                try {
                    ujpc.destroy(Integer.parseInt(idUser));
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(servletDeleteUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                 request.getSession().removeAttribute("usersList");
                    request.getSession().setAttribute("usersList", ujpc.findUsersEntities());
                    request.getSession().removeAttribute("section");
                    request.getSession().setAttribute("section", "sectionListUsers.jsp");
                    redirect = "inicio.jsp";
            } else {

                request.getSession().removeAttribute("errorDeleteUser");
                request.getSession().setAttribute("errorDeleteUser", "Error al eliminar el usuario.");
                redirect = "inicio.jsp";

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
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(servletDeleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(servletDeleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
