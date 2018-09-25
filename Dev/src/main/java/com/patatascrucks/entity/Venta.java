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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByIdEmisor", query = "SELECT v FROM Venta v WHERE v.ventaPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "Venta.findByIdVendedor", query = "SELECT v FROM Venta v WHERE v.ventaPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "Venta.findByIdVenta", query = "SELECT v FROM Venta v WHERE v.ventaPK.idVenta = :idVenta")
    , @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.ventaPK.fecha = :fecha")
    , @NamedQuery(name = "Venta.findByIdCliente", query = "SELECT v FROM Venta v WHERE v.idCliente = :idCliente")
    , @NamedQuery(name = "Venta.findByDescuento", query = "SELECT v FROM Venta v WHERE v.descuento = :descuento")
    , @NamedQuery(name = "Venta.findByDescuentoAdicional", query = "SELECT v FROM Venta v WHERE v.descuentoAdicional = :descuentoAdicional")
    , @NamedQuery(name = "Venta.findByIdFormaPago", query = "SELECT v FROM Venta v WHERE v.idFormaPago = :idFormaPago")
    , @NamedQuery(name = "Venta.findByIdEstado", query = "SELECT v FROM Venta v WHERE v.idEstado = :idEstado")
    , @NamedQuery(name = "Venta.findByNotas", query = "SELECT v FROM Venta v WHERE v.notas = :notas")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaPK ventaPK;
    @Basic(optional = false)
    @Column(name = "idCliente")
    private String idCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Basic(optional = false)
    @Column(name = "descuentoAdicional")
    private BigDecimal descuentoAdicional;
    @Basic(optional = false)
    @Column(name = "idFormaPago")
    private String idFormaPago;
    @Basic(optional = false)
    @Column(name = "idEstado")
    private String idEstado;
    @Column(name = "notas")
    private String notas;

    public Venta() {
    }

    public Venta(VentaPK ventaPK) {
        this.ventaPK = ventaPK;
    }

    public Venta(VentaPK ventaPK, String idCliente, BigDecimal descuento, BigDecimal descuentoAdicional, String idFormaPago, String idEstado) {
        this.ventaPK = ventaPK;
        this.idCliente = idCliente;
        this.descuento = descuento;
        this.descuentoAdicional = descuentoAdicional;
        this.idFormaPago = idFormaPago;
        this.idEstado = idEstado;
    }

    public Venta(String idEmisor, String idVendedor, String idVenta, Date fecha) {
        this.ventaPK = new VentaPK(idEmisor, idVendedor, idVenta, fecha);
    }

    public VentaPK getVentaPK() {
        return ventaPK;
    }

    public void setVentaPK(VentaPK ventaPK) {
        this.ventaPK = ventaPK;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getDescuentoAdicional() {
        return descuentoAdicional;
    }

    public void setDescuentoAdicional(BigDecimal descuentoAdicional) {
        this.descuentoAdicional = descuentoAdicional;
    }

    public String getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(String idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
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
        hash += (ventaPK != null ? ventaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.ventaPK == null && other.ventaPK != null) || (this.ventaPK != null && !this.ventaPK.equals(other.ventaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Venta[ ventaPK=" + ventaPK + " ]";
    }
    
}
