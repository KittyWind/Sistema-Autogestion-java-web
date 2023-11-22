
package com.mycompany.sistema.autogestion.java.web.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class AlumnoDAO implements DAO<AlumnoBean, Integer>  {
  
    @Override
    public void insertar(AlumnoBean entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void modificar(AlumnoBean entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    } 
    

    @Override
    public void eliminar(Integer id) throws Exception {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    // obtener todos los alumnos almacenados en la BD
    @Override
    public List<AlumnoBean> listar() {
        List <AlumnoBean> alumnos = new LinkedList<>(); 
        String query = "SELECT * FROM alumno a\n" + "INNER JOIN usuario u ON u.id_usuario = a.id_usuario";
        try(Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();) {
            while(rs.next()){
                alumnos.add(rsRowToAlumno(rs));
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return alumnos;
    }
    
     // obtener los alumnos por curso almacenados en la BD
    // se que no entra
    public List<AlumnoBean> listar(int curso) {
        System.out.println("FUNCIONA ESTO OBTENERALUMNOSPORCURSO");
        List<AlumnoBean> alumnos = new LinkedList<>();
        String query = "SELECT DISTINCT u.id_usuario, a.id_alumno, u.nombre, u.apellido, u.email, UPPER(u.estado) AS estado " +
                       "FROM usuario u " +
                       "INNER JOIN alumno a ON u.id_usuario = a.id_usuario " +
                       "INNER JOIN calificacion ca ON a.id_alumno = ca.id_alumno " +
                       "INNER JOIN materia m ON ca.id_materia = m.id_materia " +
                       "INNER JOIN `secundariabd`.`cursada/materia` cm ON m.id_materia = cm.id_materia " +
                       "INNER JOIN cursada c ON cm.id_cursada = c.id_cursada " +
                       "WHERE c.id_cursada = ? AND estado = 'ACTIVO'";

        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, curso);
            try (ResultSet rs = ps.executeQuery()) { //ResultSet rs contiene los resultados de la consulta
                while (rs.next()) {
                    alumnos.add(rsRowToAlumno(rs)); //convertir la fila actual del ResultSet en un objeto Curso
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return alumnos;
    }
    
    @Override
    public AlumnoBean buscar(Integer id) {
        AlumnoBean a = null;
        String query = "SELECT * FROM alumno a\n" + //
                       "INNER JOIN usuario u ON u.id_usuario = a.id_usuario\n" + //
                       "WHERE a.id_usuario = ?";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    a = rsRowToAlumno(rs);
                }
            } catch(SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return a;
    }

    private AlumnoBean rsRowToAlumno(ResultSet rs) {
        try {
            int idAlumno = rs.getInt("id_alumno");
            int idUsuario = rs.getInt("id_usuario");
            int idCursada = rs.getInt("id_cursada");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String contrasenia = rs.getString("contraseña");
            Estado estado = Estado.valueOf(rs.getString("estado").toUpperCase());
            return new AlumnoBean(idAlumno,idCursada,idUsuario,nombre,apellido,email,contrasenia,estado);
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}