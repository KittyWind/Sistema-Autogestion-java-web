
package com.mycompany.sistema.autogestion.java.web.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import com.mycompany.sistema.autogestion.java.web.modelo.DAO;
import com.mycompany.sistema.autogestion.java.web.modelo.MateriaBean;
import com.mycompany.sistema.autogestion.java.web.modelo.MateriaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel Botas
 */
public class MateriaServlet extends HttpServlet {
    
    private DAO<MateriaBean,Integer> materiaDAO;
    
    @Override
    public void init() throws ServletException {
        materiaDAO = new MateriaDAO();
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
            out.println("<title>Servlet MateriaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MateriaServlet at " + request.getContextPath() + "</h1>");
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
        try {
            String servletPath = request.getServletPath();
            switch (servletPath){
                case "/jsp/jsp_profesor/materias":
                    request.setAttribute("materias", materiaDAO.listar());
                    request.getRequestDispatcher("/jsp/jsp_profesor/AlumnoCali").forward(request, response);
                    break;
                case "/jsp/jsp_alumnos/materias":
                    request.setAttribute("materias", materiaDAO.listar());
                    request.getRequestDispatcher("/jsp/jsp_alumnos/Materias.jsp").forward(request, response);
                    break;
                case "/jsp/jsp_alumnos/materiasCalif":
                    request.setAttribute("materias", materiaDAO.listar());
                    request.getRequestDispatcher("/jsp/jsp_alumnos/Calificaciones.jsp").forward(request, response);
                    break;
                case "/jsp/jsp_alumnos/materiaDetalle":
                    int id = Integer.parseInt(request.getParameter("idMateria"));
                    request.setAttribute("materia", materiaDAO.buscar(id));
                    request.getRequestDispatcher("/jsp/jsp_alumnos/calificaciones").forward(request, response);
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

}
