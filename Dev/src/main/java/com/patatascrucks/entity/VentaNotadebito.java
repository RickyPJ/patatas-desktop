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
@Table(name = "venta_notadebito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaNotadebito.findAll", query = "SELECT v FROM VentaNotadebito v")
    , @NamedQuery(name = "VentaNotadebito.findByIdEmisor", query = "SELECT v FROM VentaNotadebito v WHERE v.ventaNotadebitoPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "VentaNotadebito.findByIdVendedor", query = "SELECT v FROM VentaNotadebito v WHERE v.ventaNotadebitoPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "VentaNotadebito.findByIdVenta", query = "SELECT v FROM VentaNotadebito v WHERE v.ventaNotadebitoPK.idVenta = :idVenta")
    , @NamedQuery(name = "VentaNotadebito.findByFecha", query = "SELECT v FROM VentaNotadebito v WHERE v.ventaNotadebitoPK.fecha = :fecha")
    , @NamedQuery(name = "VentaNotadebito.findByIdComprobante", query = "SELECT v FROM VentaNotadebito v WHERE v.ventaNotadebitoPK.idComprobante = :idComprobante")
    , @NamedQuery(name = "VentaNotadebito.findByFechaComprobante", query = "SELECT v FROM VentaNotadebito v WHERE v.ventaNotadebitoPK.fechaComprobante = :fechaComprobante")
    , @NamedQuery(name = "VentaNotadebito.findByIdImpuesto", query = "SELECT v FROM VentaNotadebito v WHERE v.idImpuesto = :idImpuesto")
    , @NamedQuery(name = "VentaNotadebito.findByIdTarifa", query = "SELECT v FROM VentaNotadebito v WHERE v.idTarifa = :idTarifa")
    , @NamedQuery(name = "VentaNotadebito.findByValor", query = "SELECT v FROM VentaNotadebito v WHERE v.valor = :valor")
    , @NamedQuery(name = "VentaNotadebito.findByMotivo", query = "SELECT v FROM VentaNotadebito v WHERE v.motivo = :motivo")})
public class VentaNotadebito implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentaNotadebitoPK ventaNotadebitoPK;
    @Basic(optional = false)
    @Column(name = "idImpuesto")
    private int idImpuesto;
    @Basic(optional = false)
    @Column(name = "idTarifa")
    private int idTarifa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "motivo")
    private String motivo;

    public VentaNotadebito() {
    }

    public VentaNotadebito(VentaNotadebitoPK ventaNotadebitoPK) {
        this.ventaNotadebitoPK = ventaNotadebitoPK;
    }

    public VentaNotadebito(VentaNotadebitoPK ventaNotadebitoPK, int idImpuesto, int idTarifa, BigDecimal valor, String motivo) {
        this.ventaNotadebitoPK = ventaNotadebitoPK;
        this.idImpuesto = idImpuesto;
        this.idTarifa = idTarifa;
        this.valor = valor;
        this.motivo = motivo;
    }

    public VentaNotadebito(String idEmisor, String idVendedor, String idVenta, Date fecha, String idComprobante, Date fechaComprobante) {
        this.ventaNotadebitoPK = new VentaNotadebitoPK(idEmisor, idVendedor, idVenta, fecha, idComprobante, fechaComprobante);
    }

    public VentaNotadebitoPK getVentaNotadebitoPK() {
        return ventaNotadebitoPK;
    }

    public void setVentaNotadebitoPK(VentaNotadebitoPK ventaNotadebitoPK) {
        this.ventaNotadebitoPK = ventaNotadebitoPK;
    }

    public int getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(int idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaNotadebitoPK != null ? ventaNotadebitoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaNotadebito)) {
            return false;
        }
        VentaNotadebito other = (VentaNotadebito) object;
        if ((this.ventaNotadebitoPK == null && other.ventaNotadebitoPK != null) || (this.ventaNotadebitoPK != null && !this.ventaNotadebitoPK.equals(other.ventaNotadebitoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VentaNotadebito[ ventaNotadebitoPK=" + ventaNotadebitoPK + " ]";
    }
    
}
