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
@Table(name = "devolucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d")
    , @NamedQuery(name = "Devolucion.findByIdEmisor", query = "SELECT d FROM Devolucion d WHERE d.devolucionPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "Devolucion.findByIdVendedor", query = "SELECT d FROM Devolucion d WHERE d.devolucionPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "Devolucion.findByIdDevolucion", query = "SELECT d FROM Devolucion d WHERE d.devolucionPK.idDevolucion = :idDevolucion")
    , @NamedQuery(name = "Devolucion.findByFecha", query = "SELECT d FROM Devolucion d WHERE d.devolucionPK.fecha = :fecha")
    , @NamedQuery(name = "Devolucion.findByNotas", query = "SELECT d FROM Devolucion d WHERE d.notas = :notas")})
public class Devolucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DevolucionPK devolucionPK;
    @Column(name = "notas")
    private String notas;

    public Devolucion() {
    }

    public Devolucion(DevolucionPK devolucionPK) {
        this.devolucionPK = devolucionPK;
    }

    public Devolucion(String idEmisor, String idVendedor, String idDevolucion, Date fecha) {
        this.devolucionPK = new DevolucionPK(idEmisor, idVendedor, idDevolucion, fecha);
    }

    public DevolucionPK getDevolucionPK() {
        return devolucionPK;
    }

    public void setDevolucionPK(DevolucionPK devolucionPK) {
        this.devolucionPK = devolucionPK;
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
        hash += (devolucionPK != null ? devolucionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devolucion)) {
            return false;
        }
        Devolucion other = (Devolucion) object;
        if ((this.devolucionPK == null && other.devolucionPK != null) || (this.devolucionPK != null && !this.devolucionPK.equals(other.devolucionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Devolucion[ devolucionPK=" + devolucionPK + " ]";
    }
    
}
