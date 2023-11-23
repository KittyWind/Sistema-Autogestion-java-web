
package com.mycompany.sistema.autogestion.java.web.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import com.mycompany.sistema.autogestion.java.web.modelo.DAO;
import com.mycompany.sistema.autogestion.java.web.modelo.AlumnoDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.CursoBean;
import com.mycompany.sistema.autogestion.java.web.modelo.CursoDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.ProfesorDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioBean;
import com.mycompany.sistema.autogestion.java.web.modelo.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Francisco
 */
public class CursoServlet extends HttpServlet {

    private DAO<CursoBean, Integer> cursoDAO;
    
    @Override
    public void init() throws ServletException {
        cursoDAO = new CursoDAO();
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CursoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CursoServlet at " + request.getContextPath() + "</h1>");
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
            HttpSession session = request.getSession();
            int idUsuario = obtenerIdUsuarioDesdeSesion(session);
        try {
            String servletPath = request.getServletPath();
            switch (servletPath){
                case "/jsp/jsp_profesor/cursos":
                    int idProfesor = obtenerIdProfesorPorIdUsuario(idUsuario);
                    request.setAttribute("cursos", cursoDAO.listar());
                    request.getRequestDispatcher("/jsp/jsp_profesor/Cursos.jsp").forward(request, response);
                    break;
                case "/jsp/jsp_profesor/cursoDetalle":
                    int id = Integer.parseInt(request.getParameter("idCursada"));
                    request.setAttribute("curso", cursoDAO.buscar(id));
                    request.getRequestDispatcher("/jsp/jsp_profesor/AlumnoCurso").forward(request, response);
                    break;
                default:
                    response.sendError(404, "Recurso no encontrado");
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
    }

    private int obtenerIdUsuarioDesdeSesion(HttpSession session) {
        // Asumo que el ID del usuario está almacenado en la sesión con el nombre "user"
        UsuarioBean usuario = (UsuarioBean) session.getAttribute("userLogueado");
        return usuario.getIdUsuario();
    }

    private int getIdAlumnoFromIdUsuario(int idUsuario) {
        AlumnoDAO aDao = new AlumnoDAO();
        return aDao.buscar(idUsuario).getIdAlumno();
    }

    private int obtenerIdProfesorPorIdUsuario(int idUsuario) {
        ProfesorDAO pDao = new ProfesorDAO();
        return pDao.buscar(idUsuario).getIdProfesor();
    }
}
