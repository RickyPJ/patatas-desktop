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
public class ProduccionDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idSocio")
    private String idSocio;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private int idGrupo;

    public ProduccionDetallePK() {
    }

    public ProduccionDetallePK(String idSocio, Date fecha, int idProducto, int idGrupo) {
        this.idSocio = idSocio;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.idGrupo = idGrupo;
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

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSocio != null ? idSocio.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) idProducto;
        hash += (int) idGrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduccionDetallePK)) {
            return false;
        }
        ProduccionDetallePK other = (ProduccionDetallePK) object;
        if ((this.idSocio == null && other.idSocio != null) || (this.idSocio != null && !this.idSocio.equals(other.idSocio))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.ProduccionDetallePK[ idSocio=" + idSocio + ", fecha=" + fecha + ", idProducto=" + idProducto + ", idGrupo=" + idGrupo + " ]";
    }
    
}
