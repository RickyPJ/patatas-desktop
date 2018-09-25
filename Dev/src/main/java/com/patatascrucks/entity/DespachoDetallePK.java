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
public class DespachoDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idEmisor")
    private String idEmisor;
    @Basic(optional = false)
    @Column(name = "idVendedor")
    private String idVendedor;
    @Basic(optional = false)
    @Column(name = "idDespacho")
    private String idDespacho;
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

    public DespachoDetallePK() {
    }

    public DespachoDetallePK(String idEmisor, String idVendedor, String idDespacho, Date fecha, int idProducto, int idGrupo) {
        this.idEmisor = idEmisor;
        this.idVendedor = idVendedor;
        this.idDespacho = idDespacho;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.idGrupo = idGrupo;
    }

    public String getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(String idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getIdDespacho() {
        return idDespacho;
    }

    public void setIdDespacho(String idDespacho) {
        this.idDespacho = idDespacho;
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
        hash += (idEmisor != null ? idEmisor.hashCode() : 0);
        hash += (idVendedor != null ? idVendedor.hashCode() : 0);
        hash += (idDespacho != null ? idDespacho.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) idProducto;
        hash += (int) idGrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DespachoDetallePK)) {
            return false;
        }
        DespachoDetallePK other = (DespachoDetallePK) object;
        if ((this.idEmisor == null && other.idEmisor != null) || (this.idEmisor != null && !this.idEmisor.equals(other.idEmisor))) {
            return false;
        }
        if ((this.idVendedor == null && other.idVendedor != null) || (this.idVendedor != null && !this.idVendedor.equals(other.idVendedor))) {
            return false;
        }
        if ((this.idDespacho == null && other.idDespacho != null) || (this.idDespacho != null && !this.idDespacho.equals(other.idDespacho))) {
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
        return "com.patatascrucks.entity.DespachoDetallePK[ idEmisor=" + idEmisor + ", idVendedor=" + idVendedor + ", idDespacho=" + idDespacho + ", fecha=" + fecha + ", idProducto=" + idProducto + ", idGrupo=" + idGrupo + " ]";
    }
    
}
