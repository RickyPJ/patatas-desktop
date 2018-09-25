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
@Table(name = "despacho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despacho.findAll", query = "SELECT d FROM Despacho d")
    , @NamedQuery(name = "Despacho.findByIdEmisor", query = "SELECT d FROM Despacho d WHERE d.despachoPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "Despacho.findByIdVendedor", query = "SELECT d FROM Despacho d WHERE d.despachoPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "Despacho.findByIdDespacho", query = "SELECT d FROM Despacho d WHERE d.despachoPK.idDespacho = :idDespacho")
    , @NamedQuery(name = "Despacho.findByFecha", query = "SELECT d FROM Despacho d WHERE d.despachoPK.fecha = :fecha")
    , @NamedQuery(name = "Despacho.findByNotas", query = "SELECT d FROM Despacho d WHERE d.notas = :notas")})
public class Despacho implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DespachoPK despachoPK;
    @Column(name = "notas")
    private String notas;

    public Despacho() {
    }

    public Despacho(DespachoPK despachoPK) {
        this.despachoPK = despachoPK;
    }

    public Despacho(String idEmisor, String idVendedor, String idDespacho, Date fecha) {
        this.despachoPK = new DespachoPK(idEmisor, idVendedor, idDespacho, fecha);
    }

    public DespachoPK getDespachoPK() {
        return despachoPK;
    }

    public void setDespachoPK(DespachoPK despachoPK) {
        this.despachoPK = despachoPK;
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
        hash += (despachoPK != null ? despachoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despacho)) {
            return false;
        }
        Despacho other = (Despacho) object;
        if ((this.despachoPK == null && other.despachoPK != null) || (this.despachoPK != null && !this.despachoPK.equals(other.despachoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Despacho[ despachoPK=" + despachoPK + " ]";
    }
    
}
