/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identificacion.findAll", query = "SELECT i FROM Identificacion i")
    , @NamedQuery(name = "Identificacion.findByIdIdentificacion", query = "SELECT i FROM Identificacion i WHERE i.idIdentificacion = :idIdentificacion")
    , @NamedQuery(name = "Identificacion.findByIdentificacion", query = "SELECT i FROM Identificacion i WHERE i.identificacion = :identificacion")})
public class Identificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idIdentificacion")
    private String idIdentificacion;
    @Basic(optional = false)
    @Column(name = "identificacion")
    private String identificacion;

    public Identificacion() {
    }

    public Identificacion(String idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public Identificacion(String idIdentificacion, String identificacion) {
        this.idIdentificacion = idIdentificacion;
        this.identificacion = identificacion;
    }

    public String getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(String idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdentificacion != null ? idIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identificacion)) {
            return false;
        }
        Identificacion other = (Identificacion) object;
        if ((this.idIdentificacion == null && other.idIdentificacion != null) || (this.idIdentificacion != null && !this.idIdentificacion.equals(other.idIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Identificacion[ idIdentificacion=" + idIdentificacion + " ]";
    }
    
}
