/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.sistema.autogestion.java.web.controlador;

import java.io.IOException;
import java.io.PrintWriter;

// import com.mycompany.sistema.autogestion.java.web.modelo.AdminDAO;
// import com.mycompany.sistema.autogestion.java.web.modelo.AlumnoDAO;
// import com.mycompany.sistema.autogestion.java.web.modelo.CalificacionBean;
// import com.mycompany.sistema.autogestion.java.web.modelo.CalificacionDAO;
// import com.mycompany.sistema.autogestion.java.web.modelo.MateriaDAO;
// import com.mycompany.sistema.autogestion.java.web.modelo.ProfesorDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioBean;
import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author kitty
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private DAO<UsuarioBean, Integer> usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

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
        HttpSession session = request.getSession();
        int idUsuario = obtenerIdUsuarioDesdeSesion(session); // Obtener el ID del usuario desde la sesión
        try {
            String servletPath = request.getServletPath();
            switch (servletPath){
                case "/jsp/jsp_admin/mostrarUsuarios":
                    request.setAttribute("usuarios", usuarioDAO.listar());
                    request.getRequestDispatcher("/jsp/jsp_admin/MostrarUsuarios.jsp").forward(request, response);
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

     private int obtenerIdUsuarioDesdeSesion(HttpSession session) {
        // Asumo que el ID del usuario está almacenado en la sesión con el nombre "user"
        UsuarioBean usuario = (UsuarioBean) session.getAttribute("userLogueado");
        return usuario.getIdUsuario();
    }


    
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
