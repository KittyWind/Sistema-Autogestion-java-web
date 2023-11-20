/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.autogestion.java.web.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kitty
 */
public class AdminDAO implements DAO<AdminBean, Integer> {

    @Override
    public void insertar(AdminBean entidad) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void modificar(AdminBean entidad) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public List<AdminBean> listar() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public AdminBean buscar(Integer id) throws Exception {
        // TODO Auto-generated method stub
        AdminBean ad = null;
        String query = "SELECT * FROM administrador ad\n" + //
                       "INNER JOIN usuario u ON u.id_usuario = ad.id_usuario\n" + //
                       "WHERE a.id_usuario = ?";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    ad = rsRowToAdmin(rs);
                }
            } catch(SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return ad;
    }
    
    private AdminBean rsRowToAdmin(ResultSet rs) {
        try {
            int idAdmin = rs.getInt("id_administrador");
            int idUsuario = rs.getInt("id_usuario");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String contrasenia = rs.getString("contraseña");
            Estado estado = Estado.valueOf(rs.getString("estado").toUpperCase());
            return new AdminBean(idAdmin,idUsuario, nombre, apellido, email, contrasenia, estado);
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
