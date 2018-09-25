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
@Table(name = "retencion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencion.findAll", query = "SELECT r FROM Retencion r")
    , @NamedQuery(name = "Retencion.findByIdRetencion", query = "SELECT r FROM Retencion r WHERE r.idRetencion = :idRetencion")
    , @NamedQuery(name = "Retencion.findByRetencion", query = "SELECT r FROM Retencion r WHERE r.retencion = :retencion")})
public class Retencion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idRetencion")
    private Integer idRetencion;
    @Basic(optional = false)
    @Column(name = "retencion")
    private String retencion;

    public Retencion() {
    }

    public Retencion(Integer idRetencion) {
        this.idRetencion = idRetencion;
    }

    public Retencion(Integer idRetencion, String retencion) {
        this.idRetencion = idRetencion;
        this.retencion = retencion;
    }

    public Integer getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(Integer idRetencion) {
        this.idRetencion = idRetencion;
    }

    public String getRetencion() {
        return retencion;
    }

    public void setRetencion(String retencion) {
        this.retencion = retencion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRetencion != null ? idRetencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retencion)) {
            return false;
        }
        Retencion other = (Retencion) object;
        if ((this.idRetencion == null && other.idRetencion != null) || (this.idRetencion != null && !this.idRetencion.equals(other.idRetencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Retencion[ idRetencion=" + idRetencion + " ]";
    }
    
}
