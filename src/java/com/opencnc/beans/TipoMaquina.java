package com.opencnc.beans;
// Generated 14/05/2014 12:34:47 AM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * TipoMaquina generated by hbm2java
 */
public class TipoMaquina  implements java.io.Serializable {


     private Integer tipoMaquinaId;
     private String nombre;
     private Set modelos = new HashSet(0);

    public TipoMaquina() {
    }

	
    public TipoMaquina(String nombre) {
        this.nombre = nombre;
    }
    public TipoMaquina(String nombre, Set modelos) {
       this.nombre = nombre;
       this.modelos = modelos;
    }
   
    public Integer getTipoMaquinaId() {
        return this.tipoMaquinaId;
    }
    
    public void setTipoMaquinaId(Integer tipoMaquinaId) {
        this.tipoMaquinaId = tipoMaquinaId;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getModelos() {
        return this.modelos;
    }
    
    public void setModelos(Set modelos) {
        this.modelos = modelos;
    }




}

