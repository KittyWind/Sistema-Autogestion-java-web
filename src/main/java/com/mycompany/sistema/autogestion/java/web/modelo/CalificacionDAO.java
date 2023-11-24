
package com.mycompany.sistema.autogestion.java.web.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Manuel Botas
 */
public class CalificacionDAO implements	DAO<CalificacionBean, Integer> {

    @Override
    public void insertar(CalificacionBean entidad) {
        String query = "INSERT INTO calificacion (nota, num_examen, id_alumno, id_materia)"+
        "VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, entidad.getNota());
            ps.setInt(2, entidad.getNumExamen());
            ps.setInt(3, entidad.getIdAlumno());
            ps.setInt(4, entidad.getIdMateria());
            ps.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException (ex); 
        }
    }

    @Override
    public void modificar(CalificacionBean entidad) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet.");
        String query = "UPDATE calificacion SET nota = ?, num_examen = ?,id_alumno = ?,id_materia = ? WHERE id_calificacion = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, entidad.getNota());
            ps.setInt(2, entidad.getNumExamen());
            ps.setInt(3, entidad.getIdAlumno());
            ps.setInt(4, entidad.getIdMateria());
            ps.setInt(5, entidad.getIdCalificacion());
            ps.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException (ex); 
        }
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        String query = "DELETE FROM calificacion WHERE id_calificacion = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException (ex); 
        }
    }

    @Override
    public List<CalificacionBean> listar() {
        List<CalificacionBean> calificaciones = new LinkedList<>();
        String query = "SELECT * FROM calificacion";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                calificaciones.add(rsRowToCalificacion(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return calificaciones;
    }
    
    //obtener las calificaciones de los alumnos de los cursos administrados por un profesor específico
    public List<CalificacionBean> listarPorIdProfesor(int idProfesor) {
        List<CalificacionBean> calificaciones = new LinkedList<>();
        String query = "SELECT c.* " +
                       "FROM calificacion c " +
                       "INNER JOIN alumno a ON c.id_alumno = a.id_alumno " +
                       "INNER JOIN cursada cu ON a.id_cursada = cu.id_cursada " +
                       "INNER JOIN `profesor/cursada` pc ON cu.id_cursada = pc.id_cursada " +
                       "WHERE pc.id_profesor = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idProfesor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    calificaciones.add(rsRowToCalificacion(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return calificaciones;
    }


    public List<CalificacionBean> listarPorIdAlumno(int idAlumno) {
        List<CalificacionBean> calificaciones = new LinkedList<>();
        //TODO Arreglar esta consulta
        String query = "SELECT * FROM calificacion c\n" +
                       "INNER JOIN alumno a ON c.id_alumno = a.id_alumno\n" +
                       "WHERE a.id_alumno = ?";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idAlumno);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    calificaciones.add(rsRowToCalificacion(rs));
                }
            } catch(SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
        return calificaciones;
    }

    private CalificacionBean rsRowToCalificacion(ResultSet rs) {
        try {
            int idCalificacion = rs.getInt("id_calificacion");
            int nota = rs.getInt("nota");
            int numExamen = rs.getInt("num_examen");
            int idAlumno = rs.getInt("id_alumno");
            int idMateria = rs.getInt("id_materia");
            return new CalificacionBean(idCalificacion, nota, numExamen, idAlumno, idMateria);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public CalificacionBean buscar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
        // método en tu clase ProfesorDAO o donde manejes las operaciones relacionadas con profesores
    public int obtenerIdProfesorPorIdUsuario(int idUsuario) {
        int idProfesor = -1; // Valor por defecto si no se encuentra el profesor

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            String query = "SELECT id_profesor FROM profesor WHERE id_usuario = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                idProfesor = rs.getInt("id_profesor");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            // Cierra las conexiones
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        return idProfesor;
    }
    
}
