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
@Table(name = "venta_notacredito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaNotacredito.findAll", query = "SELECT v FROM VentaNotacredito v")
    , @NamedQuery(name = "VentaNotacredito.findByIdEmisor", query = "SELECT v FROM VentaNotacredito v WHERE v.ventaNotacreditoPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "VentaNotacredito.findByIdVendedor", query = "SELECT v FROM VentaNotacredito v WHERE v.ventaNotacreditoPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "VentaNotacredito.findByIdVenta", query = "SELECT v FROM VentaNotacredito v WHERE v.ventaNotacreditoPK.idVenta = :idVenta")
    , @NamedQuery(name = "VentaNotacredito.findByFecha", query = "SELECT v FROM VentaNotacredito v WHERE v.ventaNotacreditoPK.fecha = :fecha")
    , @NamedQuery(name = "VentaNotacredito.findByIdComprobante", query = "SELECT v FROM VentaNotacredito v WHERE v.ventaNotacreditoPK.idComprobante = :idComprobante")
    , @NamedQuery(name = "VentaNotacredito.findByFechaComprobante", query = "SELECT v FROM VentaNotacredito v WHERE v.ventaNotacreditoPK.fechaComprobante = :fechaComprobante")
    , @NamedQuery(name = "VentaNotacredito.findByDescuentoAdicional", query = "SELECT v FROM VentaNotacredito v WHERE v.descuentoAdicional = :descuentoAdicional")})
public class VentaNotacredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaNotacreditoPK ventaNotacreditoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "descuentoAdicional")
    private BigDecimal descuentoAdicional;

    public VentaNotacredito() {
    }

    public VentaNotacredito(VentaNotacreditoPK ventaNotacreditoPK) {
        this.ventaNotacreditoPK = ventaNotacreditoPK;
    }

    public VentaNotacredito(VentaNotacreditoPK ventaNotacreditoPK, BigDecimal descuentoAdicional) {
        this.ventaNotacreditoPK = ventaNotacreditoPK;
        this.descuentoAdicional = descuentoAdicional;
    }

    public VentaNotacredito(String idEmisor, String idVendedor, String idVenta, Date fecha, String idComprobante, Date fechaComprobante) {
        this.ventaNotacreditoPK = new VentaNotacreditoPK(idEmisor, idVendedor, idVenta, fecha, idComprobante, fechaComprobante);
    }

    public VentaNotacreditoPK getVentaNotacreditoPK() {
        return ventaNotacreditoPK;
    }

    public void setVentaNotacreditoPK(VentaNotacreditoPK ventaNotacreditoPK) {
        this.ventaNotacreditoPK = ventaNotacreditoPK;
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
        hash += (ventaNotacreditoPK != null ? ventaNotacreditoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaNotacredito)) {
            return false;
        }
        VentaNotacredito other = (VentaNotacredito) object;
        if ((this.ventaNotacreditoPK == null && other.ventaNotacreditoPK != null) || (this.ventaNotacreditoPK != null && !this.ventaNotacreditoPK.equals(other.ventaNotacreditoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VentaNotacredito[ ventaNotacreditoPK=" + ventaNotacreditoPK + " ]";
    }
    
}
