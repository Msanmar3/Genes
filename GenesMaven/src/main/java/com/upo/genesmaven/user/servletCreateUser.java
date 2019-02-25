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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class servletCreateUser extends HttpServlet {

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
            request.getSession().removeAttribute("errorCreateUser");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String rol = request.getParameter("rol");
            if (((Objects.requireNonNull(password) == null ? password == null : Objects.requireNonNull(password).equals(password)) && !password.isEmpty())
                    && ((Objects.requireNonNull(email) == null ? email == null : Objects.requireNonNull(email).equals(email)) && !email.isEmpty())
                    && ((Objects.requireNonNull(rol) == null ? rol == null : Objects.requireNonNull(rol).equals(rol)) && !rol.isEmpty())
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
                    request.getSession().removeAttribute("section");
                    request.getSession().setAttribute("section", "central.jsp");
                    redirect = "inicio.jsp";
                } else {
                    request.getSession().removeAttribute("errorCreateUser");
                    request.getSession().setAttribute("errorCreateUser", "El usuario ya existe");
                    request.getSession().removeAttribute("section");
                    request.getSession().setAttribute("section", "central.jsp");
                   
                    redirect = "inicio.jsp";
                }

            } else {
                request.getSession().removeAttribute("errorCreateUser");
                request.getSession().setAttribute("errorCreateUser", "Rellene todos los campos obligatorios");
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
