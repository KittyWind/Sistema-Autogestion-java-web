
package com.mycompany.sistema.autogestion.java.web.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import com.mycompany.sistema.autogestion.java.web.modelo.Estado;
import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioBean;
import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Manuel Botas
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        String mail = request.getParameter("mail");
        String contrasenia = request.getParameter("contrasenia");
        UsuarioDAO uDao = new UsuarioDAO();
        UsuarioBean user = uDao.buscar(mail, contrasenia);
        if (user != null && user.getEstado() == Estado.ACTIVO) {
            String rol = uDao.buscarRol(user.getIdUsuario());
            if(rol != null) {
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(60 * 60);
                session.setAttribute("userLogueado", user);
                session.setAttribute("rolUser", rol);
                // TODO Revisar por qué se rompe CSS al usar getRequestDispatcher
                switch (rol) {
                    case "administrador":
                        response.sendRedirect(request.getContextPath() + "/jsp/jsp_admin/MenuAdmin.jsp");
                        // request.getRequestDispatcher("/jsp/jsp_admin/MenuAdmin.jsp").forward(request, response);
                        break;
                    case "profesor":
                        response.sendRedirect(request.getContextPath() + "/jsp/jsp_profesor/MenuProfesor.jsp");
                        // request.getRequestDispatcher("/jsp/jsp_profesor/MenuProfesor.jsp").forward(request, response);
                        break;
                    case "alumno":
                        response.sendRedirect(request.getContextPath() + "/jsp/jsp_alumnos/MenuAlumno.jsp");
                        // request.getRequestDispatcher("/jsp/jsp_alumnos/MenuAlumno.jsp").forward(request, response);
                        break;
                }
            } else {
                request.setAttribute("hayError", true);
                request.setAttribute("mensajeError", "Rol no reconocido");
                doGet(request, response);
            }
        } else {
            request.setAttribute("hayError", true);
            request.setAttribute("mensajeError", "Credenciales incorrectas");
            doGet(request, response);
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
    }

}
