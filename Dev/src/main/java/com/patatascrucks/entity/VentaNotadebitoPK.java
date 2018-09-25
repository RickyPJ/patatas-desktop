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
public class VentaNotadebitoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idEmisor")
    private String idEmisor;
    @Basic(optional = false)
    @Column(name = "idVendedor")
    private String idVendedor;
    @Basic(optional = false)
    @Column(name = "idVenta")
    private String idVenta;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "idComprobante")
    private String idComprobante;
    @Basic(optional = false)
    @Column(name = "fechaComprobante")
    @Temporal(TemporalType.DATE)
    private Date fechaComprobante;

    public VentaNotadebitoPK() {
    }

    public VentaNotadebitoPK(String idEmisor, String idVendedor, String idVenta, Date fecha, String idComprobante, Date fechaComprobante) {
        this.idEmisor = idEmisor;
        this.idVendedor = idVendedor;
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.idComprobante = idComprobante;
        this.fechaComprobante = fechaComprobante;
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

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(String idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmisor != null ? idEmisor.hashCode() : 0);
        hash += (idVendedor != null ? idVendedor.hashCode() : 0);
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (idComprobante != null ? idComprobante.hashCode() : 0);
        hash += (fechaComprobante != null ? fechaComprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaNotadebitoPK)) {
            return false;
        }
        VentaNotadebitoPK other = (VentaNotadebitoPK) object;
        if ((this.idEmisor == null && other.idEmisor != null) || (this.idEmisor != null && !this.idEmisor.equals(other.idEmisor))) {
            return false;
        }
        if ((this.idVendedor == null && other.idVendedor != null) || (this.idVendedor != null && !this.idVendedor.equals(other.idVendedor))) {
            return false;
        }
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.idComprobante == null && other.idComprobante != null) || (this.idComprobante != null && !this.idComprobante.equals(other.idComprobante))) {
            return false;
        }
        if ((this.fechaComprobante == null && other.fechaComprobante != null) || (this.fechaComprobante != null && !this.fechaComprobante.equals(other.fechaComprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VentaNotadebitoPK[ idEmisor=" + idEmisor + ", idVendedor=" + idVendedor + ", idVenta=" + idVenta + ", fecha=" + fecha + ", idComprobante=" + idComprobante + ", fechaComprobante=" + fechaComprobante + " ]";
    }
    
}
