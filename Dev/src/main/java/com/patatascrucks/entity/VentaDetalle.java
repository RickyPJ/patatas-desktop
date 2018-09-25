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
@Table(name = "venta_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaDetalle.findAll", query = "SELECT v FROM VentaDetalle v")
    , @NamedQuery(name = "VentaDetalle.findByIdEmisor", query = "SELECT v FROM VentaDetalle v WHERE v.ventaDetallePK.idEmisor = :idEmisor")
    , @NamedQuery(name = "VentaDetalle.findByIdVendedor", query = "SELECT v FROM VentaDetalle v WHERE v.ventaDetallePK.idVendedor = :idVendedor")
    , @NamedQuery(name = "VentaDetalle.findByIdVenta", query = "SELECT v FROM VentaDetalle v WHERE v.ventaDetallePK.idVenta = :idVenta")
    , @NamedQuery(name = "VentaDetalle.findByFecha", query = "SELECT v FROM VentaDetalle v WHERE v.ventaDetallePK.fecha = :fecha")
    , @NamedQuery(name = "VentaDetalle.findByIdProducto", query = "SELECT v FROM VentaDetalle v WHERE v.ventaDetallePK.idProducto = :idProducto")
    , @NamedQuery(name = "VentaDetalle.findByIdGrupo", query = "SELECT v FROM VentaDetalle v WHERE v.ventaDetallePK.idGrupo = :idGrupo")
    , @NamedQuery(name = "VentaDetalle.findByCantidad", query = "SELECT v FROM VentaDetalle v WHERE v.cantidad = :cantidad")})
public class VentaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaDetallePK ventaDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;

    public VentaDetalle() {
    }

    public VentaDetalle(VentaDetallePK ventaDetallePK) {
        this.ventaDetallePK = ventaDetallePK;
    }

    public VentaDetalle(VentaDetallePK ventaDetallePK, BigDecimal cantidad) {
        this.ventaDetallePK = ventaDetallePK;
        this.cantidad = cantidad;
    }

    public VentaDetalle(String idEmisor, String idVendedor, String idVenta, Date fecha, int idProducto, int idGrupo) {
        this.ventaDetallePK = new VentaDetallePK(idEmisor, idVendedor, idVenta, fecha, idProducto, idGrupo);
    }

    public VentaDetallePK getVentaDetallePK() {
        return ventaDetallePK;
    }

    public void setVentaDetallePK(VentaDetallePK ventaDetallePK) {
        this.ventaDetallePK = ventaDetallePK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaDetallePK != null ? ventaDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaDetalle)) {
            return false;
        }
        VentaDetalle other = (VentaDetalle) object;
        if ((this.ventaDetallePK == null && other.ventaDetallePK != null) || (this.ventaDetallePK != null && !this.ventaDetallePK.equals(other.ventaDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VentaDetalle[ ventaDetallePK=" + ventaDetallePK + " ]";
    }
    
}
