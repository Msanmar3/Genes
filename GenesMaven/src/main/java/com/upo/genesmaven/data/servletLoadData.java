/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.data;

import com.upo.genesmaven.controller.FilesJpaController;
import com.upo.genesmaven.controller.UsersJpaController;
import com.upo.genesmaven.entities.Files;
import com.upo.genesmaven.entities.Users;
import com.upo.genesmaven.genemaniareader.DBase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class servletLoadData extends HttpServlet {

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
        String UPLOAD_DIRECTORY = "data";
        String DEFAULT_FILENAME = "default.file";
        String redirect = "";
        HttpSession session = request.getSession(false);

        int MEMORY_THRESHOLD = 1024 * 1024 * 3;
        int MAX_FILE_SIZE = 1024 * 1024 * 40;
        int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

        if (session != null) {
            request.getSession().removeAttribute("errorLoadData");
            Users us = (Users) request.getSession().getAttribute("user");

            if (ServletFileUpload.isMultipartContent(request)) {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(MEMORY_THRESHOLD);
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(MAX_FILE_SIZE);
                upload.setSizeMax(MAX_REQUEST_SIZE);
                // String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
                System.out.println("ruta:" + uploadPath);
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String fileName = null, filePath = "";

                try {
                    List<FileItem> formItems = upload.parseRequest(request);
                    HashMap<String, String> hmap = new HashMap<String, String>();

                    if (formItems != null && formItems.size() > 0) {
                        DBase d = new DBase();

                        for (FileItem item : formItems) {
                            if (!item.isFormField()) {
                                fileName = new File(item.getName()).getName();
                                filePath = uploadPath + File.separator + fileName;
                                File storeFile = new File(filePath);
                                item.write(storeFile);
                                request.setAttribute("message", "File " + fileName + " has uploaded successfully!");

                            } else {
                                // Si no es un input file (input type="text|radio|checkbox|etc", select, etc).
                                String fieldName = item.getFieldName();
                                String fieldValue = item.getString();
                                hmap.put(fieldName, fieldValue);
                            }
                        }

                        if (hmap.get("origin") != null && hmap.get("firstName") != null && hmap.get("secondName") != null) {
                            try {
                                d.importDataUser(filePath, hmap.get("origin"), hmap.get("firstName") + "_" + hmap.get("secondName"), "genesUser", us);

                                UsersJpaController ujpc = new UsersJpaController();
                                Users user = ujpc.findCreateUser(us.getEmail());

                                FilesJpaController fjpc = new FilesJpaController();
                                Files file = new Files();
                                if (user != null) {

                                    file.setIdUser(user);
                                    file.setCreated(new Date());
                                    file.setNameFile(fileName);
                                    file.setUrl(filePath);
                                    fjpc.create(file);
                                    request.getSession().removeAttribute("section");
                                    request.getSession().setAttribute("section", "sectionResultUpload.jsp");
                                    redirect = "inicio.jsp";

                                    response.sendRedirect("sectionResultUpload.jsp");
                                }
                            } catch (FileNotFoundException e) {
                                File f = new File(filePath);
                                f.delete();
                                e.printStackTrace();
                            } catch (IOException e) {
                                File f = new File(filePath);
                                f.delete();
                                e.printStackTrace();
                            }
                        } else {
                            request.getSession().removeAttribute("errorUpdateFile");
                            request.getSession().setAttribute("errorUpdateFile", "Rellene todos los campos obligatorios");
                        }
                    }
                } catch (Exception ex) {
                    request.setAttribute("message", "There was an error: " + ex.getMessage());
                }

            }
        } else {
            request.getSession().removeAttribute("errorLoadData");
            request.getSession().setAttribute("errorLoadData", "Rellene todos los campos obligatorios");
            redirect = "inicio.jsp";
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
