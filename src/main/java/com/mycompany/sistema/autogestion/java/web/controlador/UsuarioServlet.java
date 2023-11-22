/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.sistema.autogestion.java.web.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioBean;
import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.DAO;
import com.mycompany.sistema.autogestion.java.web.modelo.Estado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author kitty
 */
public class UsuarioServlet extends HttpServlet {

    private DAO<UsuarioBean, Integer> usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        try {
            String servletPath = request.getServletPath();
            switch (servletPath) {
                case "/jsp/jsp_admin/mostrarUsuarios":
                    request.setAttribute("usuarios", usuarioDAO.listar());
                    request.getRequestDispatcher("/jsp/jsp_admin/Usuarios.jsp").forward(request, response);
                    break;
                case "/jsp/jsp_admin/borrarUsuario":
                    int idbaja = Integer.parseInt(request.getParameter("idUsuario"));
                    UsuarioBean ubaja = usuarioDAO.buscar(idbaja);
                    ubaja.setEstado(Estado.INACTIVO);
                    usuarioDAO.modificar(ubaja);
                    request.getRequestDispatcher("/jsp/jsp_admin/MenuAdmin.jsp").forward(request, response);
                    break;
                case "/jsp/jsp_admin/activarUsuario":
                    int idalta = Integer.parseInt(request.getParameter("idUsuario"));
                    UsuarioBean ualta = usuarioDAO.buscar(idalta);
                    ualta.setEstado(Estado.ACTIVO);
                    usuarioDAO.modificar(ualta);
                    request.getRequestDispatcher("/jsp/jsp_admin/MenuAdmin.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            response.sendError(500, e.getMessage());
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
