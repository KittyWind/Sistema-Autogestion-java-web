
package com.mycompany.sistema.autogestion.java.web.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import com.mycompany.sistema.autogestion.java.web.modelo.AlumnoDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.CalificacionBean;
import com.mycompany.sistema.autogestion.java.web.modelo.CalificacionDAO;
import com.mycompany.sistema.autogestion.java.web.modelo.MateriaDAO;
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

public class CalificacionServlet extends HttpServlet {

    private CalificacionDAO calificacionDAO;

    @Override
    public void init() throws ServletException {
        calificacionDAO = new CalificacionDAO();
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
            out.println("<title>Servlet CalificacionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalificacionServlet at " + request.getContextPath() + "</h1>");
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
            int idUsuario = obtenerIdUsuarioDesdeSesion(session); // Obtener el ID del usuario desde la sesión
        try {
            String servletPath = request.getServletPath();
            switch (servletPath){
                case "/jsp/jsp_alumnos/calificaciones":
                    int idAlumno = getIdAlumnoFromIdUsuario(idUsuario);
                    request.setAttribute("calificaciones", calificacionDAO.listarPorIdAlumno(idAlumno));
                    if(request.getAttribute("materia") != null) {
                        request.getRequestDispatcher("/jsp/jsp_alumnos/MateriaDetalle.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/jsp/jsp_alumnos/materiasCalif").forward(request, response);         
                    }
                break;
                case "/jsp/jsp_profesor/calificacion":
                    int idProfesor = obtenerIdProfesorPorIdUsuario(idUsuario);
                    request.setAttribute("calificaciones", calificacionDAO.listarPorIdProfesor(idProfesor));
                    request.getRequestDispatcher("/jsp/jsp_profesor/materias").forward(request, response);  
                break;
                case "/jsp/jsp_profesor/addCalificacion":
                    request.getRequestDispatcher("/jsp/jsp_profesor/AgregarCalificaciones.jsp").forward(request, response);
                break;
                case "/jsp/jsp_profesor/califEditar":
                    request.setAttribute("idAlumno", request.getParameter("idAlumno"));
                    request.setAttribute("idMateria", request.getParameter("idMateria"));
                    request.setAttribute("idCalificacion", request.getParameter("idCalificacion"));
                    request.getRequestDispatcher("/jsp/jsp_profesor/editarCalificacion.jsp").forward(request, response);
                break;
                case "/jsp/jsp_profesor/califBorrar":
                    int id = Integer.parseInt(request.getParameter("idCalificacion"));
                    calificacionDAO.eliminar(id);
                    request.getRequestDispatcher("/jsp/jsp_profesor/MenuProfesor.jsp").forward(request, response);
                break;
            }  
        } catch (Exception e) {
            response.sendError(500, e.getMessage());
        }
    }
    
    // TODO Agregar documentación
    // método para obtener el ID del usuario desde la sesión
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
            HttpSession session = request.getSession();
            try {
                String servletPath = request.getServletPath();
                String nota = request.getParameter("nota");
                String numExamen = request.getParameter("numExamen");
                if (nota != "" && numExamen != "") {
                    int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
                    int idMateria = Integer.parseInt(request.getParameter("idMateria"));
                    int notan= Integer.parseInt(nota);
                    int numExamenn = Integer.parseInt(numExamen);
                    switch (servletPath) {
                        case "/jsp/jsp_profesor/editarCalif":
                            int idCalificacion = Integer.parseInt(request.getParameter("idCalificacion"));
                            CalificacionBean c = new CalificacionBean(idCalificacion, notan, numExamenn, idAlumno, idMateria);
                            calificacionDAO.modificar(c);
                            request.getRequestDispatcher("/jsp/jsp_profesor/MenuProfesor.jsp").forward(request, response);
                        break;
                        case "/jsp/jsp_profesor/insertarCalif":
                        default:
                        break;
                    }
                } else {
                    request.setAttribute("hayError", true);
                    request.setAttribute("mensajeError", "Datos vacios");
                    request.getRequestDispatcher("/jsp/jsp_profesor/editarCalificacion.jsp").forward(request, response);
                }
                // switch (servletPath){
                //     case "/jsp/jsp_profesor/editarCalif":
                        
                //         if (nota != "" && numExamen != "") {
                //             int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
                //             int idMateria = Integer.parseInt(request.getParameter("idMateria"));
                //             int idCalificacion = Integer.parseInt(request.getParameter("idCalificacion"));
                //             int notan= Integer.parseInt(nota);
                //             int numExamenn = Integer.parseInt(numExamen);
                //             CalificacionBean c = new CalificacionBean(idCalificacion, notan, numExamenn, idAlumno, idMateria);
                //             calificacionDAO.modificar(c);
                //             request.getRequestDispatcher("/jsp/jsp_profesor/MenuProfesor.jsp").forward(request, response);
                //         } else {
                //             request.setAttribute("hayError", true);
                //             request.setAttribute("mensajeError", "Datos vacios");
                //             request.getRequestDispatcher("/jsp/jsp_profesor/editarCalificacion.jsp").forward(request, response);;
                //         }
                //     break;       
                // }
            } catch (Exception e) {
                response.sendError(500,e.getMessage());
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
