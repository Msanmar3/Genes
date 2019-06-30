/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.data;

import com.upo.genesmaven.controller.AuthorsJpaController;
import com.upo.genesmaven.entities.AuthorsYear;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class servletSearchAuthors extends HttpServlet {

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
        PrintWriter p = response.getWriter();
        System.out.println("servtletSearchAuthors");

        if (session != null) {
            Integer id = Integer.parseInt(request.getParameter("keyword"));

            AuthorsJpaController ijc = new AuthorsJpaController();
            List<AuthorsYear> listAuthors = (List<AuthorsYear>) ijc.findAuthorBySpecie(id);

            String out = "<option value=''>Seleccione un author</option>";
            for (AuthorsYear authors : listAuthors) {
                if (authors.getYear() == null) {
                    out += "<option value=\"" + authors.getIdAuthor() + "\">" + authors.getNameAuthor() + "</option>";

                } else {
                    out += "<option value=\"" + authors.getIdAuthor() + "\">" + authors.getNameAuthor() + "-" + authors.getYear() + "</option>";
                }
            }
            System.out.println("Out:::::::" + out);
            p.println(out);

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
