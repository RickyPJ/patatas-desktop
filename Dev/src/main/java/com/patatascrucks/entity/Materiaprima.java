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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "materiaprima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materiaprima.findAll", query = "SELECT m FROM Materiaprima m")
    , @NamedQuery(name = "Materiaprima.findByIdMateriaPrima", query = "SELECT m FROM Materiaprima m WHERE m.idMateriaPrima = :idMateriaPrima")
    , @NamedQuery(name = "Materiaprima.findByMateriaPrima", query = "SELECT m FROM Materiaprima m WHERE m.materiaPrima = :materiaPrima")})
public class Materiaprima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMateriaPrima")
    private Integer idMateriaPrima;
    @Basic(optional = false)
    @Column(name = "materiaPrima")
    private String materiaPrima;

    public Materiaprima() {
    }

    public Materiaprima(Integer idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public Materiaprima(Integer idMateriaPrima, String materiaPrima) {
        this.idMateriaPrima = idMateriaPrima;
        this.materiaPrima = materiaPrima;
    }

    public Integer getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(Integer idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(String materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateriaPrima != null ? idMateriaPrima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materiaprima)) {
            return false;
        }
        Materiaprima other = (Materiaprima) object;
        if ((this.idMateriaPrima == null && other.idMateriaPrima != null) || (this.idMateriaPrima != null && !this.idMateriaPrima.equals(other.idMateriaPrima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Materiaprima[ idMateriaPrima=" + idMateriaPrima + " ]";
    }
    
}
