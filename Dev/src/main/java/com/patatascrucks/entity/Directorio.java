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
@Table(name = "directorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Directorio.findAll", query = "SELECT d FROM Directorio d")
    , @NamedQuery(name = "Directorio.findByIdDirectorio", query = "SELECT d FROM Directorio d WHERE d.idDirectorio = :idDirectorio")
    , @NamedQuery(name = "Directorio.findByDirectorio", query = "SELECT d FROM Directorio d WHERE d.directorio = :directorio")})
public class Directorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDirectorio")
    private Integer idDirectorio;
    @Basic(optional = false)
    @Column(name = "directorio")
    private String directorio;

    public Directorio() {
    }

    public Directorio(Integer idDirectorio) {
        this.idDirectorio = idDirectorio;
    }

    public Directorio(Integer idDirectorio, String directorio) {
        this.idDirectorio = idDirectorio;
        this.directorio = directorio;
    }

    public Integer getIdDirectorio() {
        return idDirectorio;
    }

    public void setIdDirectorio(Integer idDirectorio) {
        this.idDirectorio = idDirectorio;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirectorio != null ? idDirectorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Directorio)) {
            return false;
        }
        Directorio other = (Directorio) object;
        if ((this.idDirectorio == null && other.idDirectorio != null) || (this.idDirectorio != null && !this.idDirectorio.equals(other.idDirectorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Directorio[ idDirectorio=" + idDirectorio + " ]";
    }
    
}
