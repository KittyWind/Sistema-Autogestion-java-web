
package com.mycompany.sistema.autogestion.java.web.modelo;

import java.io.Serializable;

/**
 *
 * @author Francisco
 */
public class CursoBean implements Serializable {
    private int idCursada;
    private String anioDivision;

    public CursoBean() {
    }

    public CursoBean(int idCursada, String anioDivision) {
        this.idCursada = idCursada;
        this.anioDivision = anioDivision;
    }

    public int getIdCursada() {
        return idCursada;
    }

    public void setIdCursada(int idCursada) {
        this.idCursada = idCursada;
    }

    

    public String getAnioDivision() {
        return anioDivision;
    }

    public void setAnioDivision(String anioDivision) {
        this.anioDivision = anioDivision;
    }
    
    
}
