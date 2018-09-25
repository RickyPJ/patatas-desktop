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
@Table(name = "emision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emision.findAll", query = "SELECT e FROM Emision e")
    , @NamedQuery(name = "Emision.findByIdEmision", query = "SELECT e FROM Emision e WHERE e.idEmision = :idEmision")
    , @NamedQuery(name = "Emision.findByEmision", query = "SELECT e FROM Emision e WHERE e.emision = :emision")})
public class Emision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEmision")
    private Integer idEmision;
    @Basic(optional = false)
    @Column(name = "emision")
    private String emision;

    public Emision() {
    }

    public Emision(Integer idEmision) {
        this.idEmision = idEmision;
    }

    public Emision(Integer idEmision, String emision) {
        this.idEmision = idEmision;
        this.emision = emision;
    }

    public Integer getIdEmision() {
        return idEmision;
    }

    public void setIdEmision(Integer idEmision) {
        this.idEmision = idEmision;
    }

    public String getEmision() {
        return emision;
    }

    public void setEmision(String emision) {
        this.emision = emision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmision != null ? idEmision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emision)) {
            return false;
        }
        Emision other = (Emision) object;
        if ((this.idEmision == null && other.idEmision != null) || (this.idEmision != null && !this.idEmision.equals(other.idEmision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Emision[ idEmision=" + idEmision + " ]";
    }
    
}
