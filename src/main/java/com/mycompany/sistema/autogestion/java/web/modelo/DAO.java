
package com.mycompany.sistema.autogestion.java.web.modelo;

import java.util.List;

/**
 *
 * @author Manuel Botas
 */
public interface DAO<T, K> {
    void insertar(T entidad) throws Exception;
    void modificar(T entidad) throws Exception;
    void eliminar(K id) throws Exception;
    List<T> listar() throws Exception;
    T buscar(K id) throws Exception;
}
