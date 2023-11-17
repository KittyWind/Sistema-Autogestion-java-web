
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
public class MateriaDAO implements DAO<MateriaBean, Integer> {

    @Override
    public void insertar(MateriaBean entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificar(MateriaBean entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(MateriaBean id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<MateriaBean> listar() {
        List<MateriaBean> materias = new LinkedList<>();
        String query = "SELECT * FROM materia";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                materias.add(rsRowToMateria(rs));
            }
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return materias;
    }

    private MateriaBean rsRowToMateria(ResultSet rs) {
        try {
            int idMateria = rs.getInt("id_materia");
            String nombre = rs.getString("nombre");
            return new MateriaBean(idMateria, nombre);
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public MateriaBean buscar(Integer id) {
        MateriaBean m = null;
        String query = "SELECT * FROM materia WHERE id_materia = ?";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    m = rsRowToMateria(rs);
                }
            } catch (SQLException ex){
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return m;
    }
    
    public int obtenerIDporMateria(String materia){
        int id=0;
        String query = "SELECT id_materia FROM materia " + 
                       "WHERE materia.nombre = ?";
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, materia);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    id=rs.getInt("id_materia");
                }
            } catch (SQLException x){
                throw new RuntimeException (x);
            }
        } catch (SQLException x){
            throw new RuntimeException (x);
        }
        return id;
    }
    
}
