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
@Table(name = "comprobante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprobante.findAll", query = "SELECT c FROM Comprobante c")
    , @NamedQuery(name = "Comprobante.findByIdComprobante", query = "SELECT c FROM Comprobante c WHERE c.idComprobante = :idComprobante")
    , @NamedQuery(name = "Comprobante.findByComprobante", query = "SELECT c FROM Comprobante c WHERE c.comprobante = :comprobante")})
public class Comprobante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idComprobante")
    private String idComprobante;
    @Basic(optional = false)
    @Column(name = "comprobante")
    private String comprobante;

    public Comprobante() {
    }

    public Comprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Comprobante(String idComprobante, String comprobante) {
        this.idComprobante = idComprobante;
        this.comprobante = comprobante;
    }

    public String getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComprobante != null ? idComprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprobante)) {
            return false;
        }
        Comprobante other = (Comprobante) object;
        if ((this.idComprobante == null && other.idComprobante != null) || (this.idComprobante != null && !this.idComprobante.equals(other.idComprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Comprobante[ idComprobante=" + idComprobante + " ]";
    }
    
}
