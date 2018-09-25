/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "perdida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perdida.findAll", query = "SELECT p FROM Perdida p")
    , @NamedQuery(name = "Perdida.findByIdEmisor", query = "SELECT p FROM Perdida p WHERE p.perdidaPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "Perdida.findByIdVendedor", query = "SELECT p FROM Perdida p WHERE p.perdidaPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "Perdida.findByIdPerdida", query = "SELECT p FROM Perdida p WHERE p.perdidaPK.idPerdida = :idPerdida")
    , @NamedQuery(name = "Perdida.findByFecha", query = "SELECT p FROM Perdida p WHERE p.perdidaPK.fecha = :fecha")
    , @NamedQuery(name = "Perdida.findByNotas", query = "SELECT p FROM Perdida p WHERE p.notas = :notas")})
public class Perdida implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerdidaPK perdidaPK;
    @Column(name = "notas")
    private String notas;

    public Perdida() {
    }

    public Perdida(PerdidaPK perdidaPK) {
        this.perdidaPK = perdidaPK;
    }

    public Perdida(String idEmisor, String idVendedor, String idPerdida, Date fecha) {
        this.perdidaPK = new PerdidaPK(idEmisor, idVendedor, idPerdida, fecha);
    }

    public PerdidaPK getPerdidaPK() {
        return perdidaPK;
    }

    public void setPerdidaPK(PerdidaPK perdidaPK) {
        this.perdidaPK = perdidaPK;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perdidaPK != null ? perdidaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perdida)) {
            return false;
        }
        Perdida other = (Perdida) object;
        if ((this.perdidaPK == null && other.perdidaPK != null) || (this.perdidaPK != null && !this.perdidaPK.equals(other.perdidaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Perdida[ perdidaPK=" + perdidaPK + " ]";
    }
    
}
