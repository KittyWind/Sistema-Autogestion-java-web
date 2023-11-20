
package com.mycompany.sistema.autogestion.java.web.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Manuel Botas
 */
public class ProfesorDAO implements DAO<ProfesorBean,Integer> {

    @Override
    public void insertar(ProfesorBean entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificar(ProfesorBean entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ProfesorBean> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProfesorBean buscar(Integer id) {
        ProfesorBean p = null;
        String query = "SELECT * FROM profesor p\n" + //
                       "INNER JOIN usuario u ON u.id_usuario = p.id_usuario\n" + //
                       "WHERE u.id_usuario = ?";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    p = rsRowToProfesor(rs);
                }
            } catch(SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return p;
    }

    private ProfesorBean rsRowToProfesor(ResultSet rs) {
        try {
            int idProfesor = rs.getInt("id_profesor");
            int idUsuario = rs.getInt("id_usuario");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String contrasenia = rs.getString("contraseña");
            Estado estado = Estado.valueOf(rs.getString("estado").toUpperCase());
            return new ProfesorBean(idProfesor, idUsuario, nombre, apellido, email, contrasenia, estado);
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}