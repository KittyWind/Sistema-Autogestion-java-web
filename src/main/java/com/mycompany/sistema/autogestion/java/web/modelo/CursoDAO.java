
package com.mycompany.sistema.autogestion.java.web.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class CursoDAO implements DAO<CursoBean, Integer> {
    // private static int contador = 1;
    // private List <CursoBean> cursos;

    // public CursoDAO() {
    //     this.cursos = new ArrayList<>();
    //     insertarCursos();
    // }
    
    @Override
    public void insertar(CursoBean curso) {
        // curso.setId_cursada(contador);
        // cursos.add(curso);
        // contador++;
    }

    @Override
    public void modificar(CursoBean entidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CursoBean> listar() {
        // return new ArrayList<>(this.cursos);
        List<CursoBean> cursos = new LinkedList<>();
        String query = "SELECT * FROM cursada";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                cursos.add(rsRowToCurso(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return cursos;
    }

    @Override
    public CursoBean buscar(Integer idCursada) throws Exception {
        // throw new UnsupportedOperationException("Not supported yet.");
        CursoBean cu = null;
        String query = "SELECT * FROM cursada WHERE id_cursada = ?";
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, idCursada);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    cu = rsRowToCurso(rs);
                }
            } catch (SQLException ex){
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return cu;
    }

    public List<CursoBean> listarPorIdProfesor(int id) {
        List<CursoBean> cursos = new LinkedList<>();
        String query = "select cu.* from cursada cu inner join `profesor/cursada` pc on pc.id_cursada = cu.id_cursada inner join profesor p on pc.id_profesor = p.id_profesor where pc.id_profesor = ?;";
        try (Connection con = ConnectionPool.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cursos.add(rsRowToCurso(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return cursos;
    }

    private void insertarCursos() {
        // insertar(new CursoBean(contador,"15"));
        // insertar(new CursoBean(contador,"13"));
        // insertar(new CursoBean(contador,"25"));
        // insertar(new CursoBean(contador,"23"));
        // insertar(new CursoBean(contador,"33"));
        // insertar(new CursoBean(contador,"35"));
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private CursoBean rsRowToCurso(ResultSet rs) {
        try {
            int idCursada = rs.getInt("id_cursada");
            String anioDivision = rs.getString("anio_division");
            return new CursoBean(idCursada, anioDivision);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

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

