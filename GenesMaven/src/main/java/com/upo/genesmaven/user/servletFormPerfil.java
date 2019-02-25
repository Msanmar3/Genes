/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.user;

import com.upo.genesmaven.controller.RolesJpaController;
import com.upo.genesmaven.controller.UsersJpaController;
import com.upo.genesmaven.entities.Users;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mónic Sánchez Martín
 */
public class servletFormPerfil extends HttpServlet {

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
        String redirect;
        HttpSession session = request.getSession(false);

        if (session != null) {
            Users u = (Users) request.getSession().getAttribute("user");
            Integer idUser = u.getIdUser();
            if ((Objects.requireNonNull(idUser) == null ? idUser == null
                    : Objects.requireNonNull(idUser).equals(idUser))) {

                UsersJpaController ujpc = new UsersJpaController();

                Users user = ujpc.findUsers(idUser);

                if (user != null) {
                    RolesJpaController rjpa = new RolesJpaController();
                    request.getSession().removeAttribute("rolesList");
                    request.getSession().setAttribute("rolesList", rjpa.findRolesEntities());
                    request.getSession().removeAttribute("userUpdatePerfil");
                    request.getSession().setAttribute("userUpdatePerfil", user);
                    request.getSession().removeAttribute("section");
                    request.getSession().setAttribute("section", "sectionUpdatePerfil.jsp");
//                    request.getSession().setAttribute("funcionesJS", "funcionesUsuario.jsp");
                    request.getSession().removeAttribute("funcionesJS");
                    request.getSession().setAttribute("funcionesJS", "validacionUpdateUsuario.js");
                    redirect = "inicio.jsp";
                } else {
                    request.getSession().removeAttribute("errorUpdatePerfil");
                    request.getSession().setAttribute("errorUpdatePerfil", "No existe el usuario");
                    redirect = "index.jsp";
                }

            } else {
                request.getSession().removeAttribute("errorUpdatePerfil");
                request.getSession().setAttribute("errorUpdatePerfil", "No existe el usuario.");
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
