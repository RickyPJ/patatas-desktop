/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "venta_notacredito_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaNotacreditoDetalle.findAll", query = "SELECT v FROM VentaNotacreditoDetalle v")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByIdEmisor", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.idEmisor = :idEmisor")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByIdVendedor", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.idVendedor = :idVendedor")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByIdVenta", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.idVenta = :idVenta")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByFecha", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.fecha = :fecha")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByIdComprobante", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.idComprobante = :idComprobante")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByFechaComprobante", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.fechaComprobante = :fechaComprobante")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByIdProducto", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.idProducto = :idProducto")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByIdGrupo", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.ventaNotacreditoDetallePK.idGrupo = :idGrupo")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByCantidad", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.cantidad = :cantidad")
    , @NamedQuery(name = "VentaNotacreditoDetalle.findByDescuentoAdicional", query = "SELECT v FROM VentaNotacreditoDetalle v WHERE v.descuentoAdicional = :descuentoAdicional")})
public class VentaNotacreditoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaNotacreditoDetallePK ventaNotacreditoDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @Column(name = "descuentoAdicional")
    private BigDecimal descuentoAdicional;

    public VentaNotacreditoDetalle() {
    }

    public VentaNotacreditoDetalle(VentaNotacreditoDetallePK ventaNotacreditoDetallePK) {
        this.ventaNotacreditoDetallePK = ventaNotacreditoDetallePK;
    }

    public VentaNotacreditoDetalle(VentaNotacreditoDetallePK ventaNotacreditoDetallePK, BigDecimal cantidad, BigDecimal descuentoAdicional) {
        this.ventaNotacreditoDetallePK = ventaNotacreditoDetallePK;
        this.cantidad = cantidad;
        this.descuentoAdicional = descuentoAdicional;
    }

    public VentaNotacreditoDetalle(String idEmisor, String idVendedor, String idVenta, Date fecha, String idComprobante, Date fechaComprobante, int idProducto, int idGrupo) {
        this.ventaNotacreditoDetallePK = new VentaNotacreditoDetallePK(idEmisor, idVendedor, idVenta, fecha, idComprobante, fechaComprobante, idProducto, idGrupo);
    }

    public VentaNotacreditoDetallePK getVentaNotacreditoDetallePK() {
        return ventaNotacreditoDetallePK;
    }

    public void setVentaNotacreditoDetallePK(VentaNotacreditoDetallePK ventaNotacreditoDetallePK) {
        this.ventaNotacreditoDetallePK = ventaNotacreditoDetallePK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getDescuentoAdicional() {
        return descuentoAdicional;
    }

    public void setDescuentoAdicional(BigDecimal descuentoAdicional) {
        this.descuentoAdicional = descuentoAdicional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaNotacreditoDetallePK != null ? ventaNotacreditoDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaNotacreditoDetalle)) {
            return false;
        }
        VentaNotacreditoDetalle other = (VentaNotacreditoDetalle) object;
        if ((this.ventaNotacreditoDetallePK == null && other.ventaNotacreditoDetallePK != null) || (this.ventaNotacreditoDetallePK != null && !this.ventaNotacreditoDetallePK.equals(other.ventaNotacreditoDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VentaNotacreditoDetalle[ ventaNotacreditoDetallePK=" + ventaNotacreditoDetallePK + " ]";
    }
    
}
