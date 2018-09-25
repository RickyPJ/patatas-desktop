/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ricardo
 */
@Embeddable
public class ProduccionMateriaprimaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idSocio")
    private String idSocio;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "idMateriaPrima")
    private int idMateriaPrima;

    public ProduccionMateriaprimaPK() {
    }

    public ProduccionMateriaprimaPK(String idSocio, Date fecha, int idMateriaPrima) {
        this.idSocio = idSocio;
        this.fecha = fecha;
        this.idMateriaPrima = idMateriaPrima;
    }

    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public void setIdMateriaPrima(int idMateriaPrima) {
        this.idMateriaPrima = idMateriaPrima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSocio != null ? idSocio.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) idMateriaPrima;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduccionMateriaprimaPK)) {
            return false;
        }
        ProduccionMateriaprimaPK other = (ProduccionMateriaprimaPK) object;
        if ((this.idSocio == null && other.idSocio != null) || (this.idSocio != null && !this.idSocio.equals(other.idSocio))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.idMateriaPrima != other.idMateriaPrima) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.ProduccionMateriaprimaPK[ idSocio=" + idSocio + ", fecha=" + fecha + ", idMateriaPrima=" + idMateriaPrima + " ]";
    }
    
}
