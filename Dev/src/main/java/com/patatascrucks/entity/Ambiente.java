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
@Table(name = "ambiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambiente.findAll", query = "SELECT a FROM Ambiente a")
    , @NamedQuery(name = "Ambiente.findByIdAmbiente", query = "SELECT a FROM Ambiente a WHERE a.idAmbiente = :idAmbiente")
    , @NamedQuery(name = "Ambiente.findByAmbiente", query = "SELECT a FROM Ambiente a WHERE a.ambiente = :ambiente")})
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAmbiente")
    private Integer idAmbiente;
    @Basic(optional = false)
    @Column(name = "ambiente")
    private String ambiente;

    public Ambiente() {
    }

    public Ambiente(Integer idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public Ambiente(Integer idAmbiente, String ambiente) {
        this.idAmbiente = idAmbiente;
        this.ambiente = ambiente;
    }

    public Integer getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(Integer idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAmbiente != null ? idAmbiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ambiente)) {
            return false;
        }
        Ambiente other = (Ambiente) object;
        if ((this.idAmbiente == null && other.idAmbiente != null) || (this.idAmbiente != null && !this.idAmbiente.equals(other.idAmbiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Ambiente[ idAmbiente=" + idAmbiente + " ]";
    }
    
}
