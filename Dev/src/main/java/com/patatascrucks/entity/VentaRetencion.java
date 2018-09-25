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
@Table(name = "venta_retencion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaRetencion.findAll", query = "SELECT v FROM VentaRetencion v")
    , @NamedQuery(name = "VentaRetencion.findByIdEmisor", query = "SELECT v FROM VentaRetencion v WHERE v.ventaRetencionPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "VentaRetencion.findByIdVendedor", query = "SELECT v FROM VentaRetencion v WHERE v.ventaRetencionPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "VentaRetencion.findByIdVenta", query = "SELECT v FROM VentaRetencion v WHERE v.ventaRetencionPK.idVenta = :idVenta")
    , @NamedQuery(name = "VentaRetencion.findByFecha", query = "SELECT v FROM VentaRetencion v WHERE v.ventaRetencionPK.fecha = :fecha")
    , @NamedQuery(name = "VentaRetencion.findByIdComprobante", query = "SELECT v FROM VentaRetencion v WHERE v.ventaRetencionPK.idComprobante = :idComprobante")
    , @NamedQuery(name = "VentaRetencion.findByFechaComprobante", query = "SELECT v FROM VentaRetencion v WHERE v.ventaRetencionPK.fechaComprobante = :fechaComprobante")
    , @NamedQuery(name = "VentaRetencion.findByIdRetencion", query = "SELECT v FROM VentaRetencion v WHERE v.idRetencion = :idRetencion")
    , @NamedQuery(name = "VentaRetencion.findByIdTarifa", query = "SELECT v FROM VentaRetencion v WHERE v.idTarifa = :idTarifa")})
public class VentaRetencion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaRetencionPK ventaRetencionPK;
    @Basic(optional = false)
    @Column(name = "idRetencion")
    private int idRetencion;
    @Basic(optional = false)
    @Column(name = "idTarifa")
    private int idTarifa;

    public VentaRetencion() {
    }

    public VentaRetencion(VentaRetencionPK ventaRetencionPK) {
        this.ventaRetencionPK = ventaRetencionPK;
    }

    public VentaRetencion(VentaRetencionPK ventaRetencionPK, int idRetencion, int idTarifa) {
        this.ventaRetencionPK = ventaRetencionPK;
        this.idRetencion = idRetencion;
        this.idTarifa = idTarifa;
    }

    public VentaRetencion(String idEmisor, String idVendedor, String idVenta, Date fecha, String idComprobante, Date fechaComprobante) {
        this.ventaRetencionPK = new VentaRetencionPK(idEmisor, idVendedor, idVenta, fecha, idComprobante, fechaComprobante);
    }

    public VentaRetencionPK getVentaRetencionPK() {
        return ventaRetencionPK;
    }

    public void setVentaRetencionPK(VentaRetencionPK ventaRetencionPK) {
        this.ventaRetencionPK = ventaRetencionPK;
    }

    public int getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(int idRetencion) {
        this.idRetencion = idRetencion;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaRetencionPK != null ? ventaRetencionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaRetencion)) {
            return false;
        }
        VentaRetencion other = (VentaRetencion) object;
        if ((this.ventaRetencionPK == null && other.ventaRetencionPK != null) || (this.ventaRetencionPK != null && !this.ventaRetencionPK.equals(other.ventaRetencionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VentaRetencion[ ventaRetencionPK=" + ventaRetencionPK + " ]";
    }
    
}
