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
@Table(name = "devolucion_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionDetalle.findAll", query = "SELECT d FROM DevolucionDetalle d")
    , @NamedQuery(name = "DevolucionDetalle.findByIdEmisor", query = "SELECT d FROM DevolucionDetalle d WHERE d.devolucionDetallePK.idEmisor = :idEmisor")
    , @NamedQuery(name = "DevolucionDetalle.findByIdVendedor", query = "SELECT d FROM DevolucionDetalle d WHERE d.devolucionDetallePK.idVendedor = :idVendedor")
    , @NamedQuery(name = "DevolucionDetalle.findByIdDevolucion", query = "SELECT d FROM DevolucionDetalle d WHERE d.devolucionDetallePK.idDevolucion = :idDevolucion")
    , @NamedQuery(name = "DevolucionDetalle.findByFecha", query = "SELECT d FROM DevolucionDetalle d WHERE d.devolucionDetallePK.fecha = :fecha")
    , @NamedQuery(name = "DevolucionDetalle.findByIdProducto", query = "SELECT d FROM DevolucionDetalle d WHERE d.devolucionDetallePK.idProducto = :idProducto")
    , @NamedQuery(name = "DevolucionDetalle.findByIdGrupo", query = "SELECT d FROM DevolucionDetalle d WHERE d.devolucionDetallePK.idGrupo = :idGrupo")
    , @NamedQuery(name = "DevolucionDetalle.findByCantidad", query = "SELECT d FROM DevolucionDetalle d WHERE d.cantidad = :cantidad")})
public class DevolucionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DevolucionDetallePK devolucionDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;

    public DevolucionDetalle() {
    }

    public DevolucionDetalle(DevolucionDetallePK devolucionDetallePK) {
        this.devolucionDetallePK = devolucionDetallePK;
    }

    public DevolucionDetalle(DevolucionDetallePK devolucionDetallePK, BigDecimal cantidad) {
        this.devolucionDetallePK = devolucionDetallePK;
        this.cantidad = cantidad;
    }

    public DevolucionDetalle(String idEmisor, String idVendedor, String idDevolucion, Date fecha, int idProducto, int idGrupo) {
        this.devolucionDetallePK = new DevolucionDetallePK(idEmisor, idVendedor, idDevolucion, fecha, idProducto, idGrupo);
    }

    public DevolucionDetallePK getDevolucionDetallePK() {
        return devolucionDetallePK;
    }

    public void setDevolucionDetallePK(DevolucionDetallePK devolucionDetallePK) {
        this.devolucionDetallePK = devolucionDetallePK;
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
        hash += (devolucionDetallePK != null ? devolucionDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionDetalle)) {
            return false;
        }
        DevolucionDetalle other = (DevolucionDetalle) object;
        if ((this.devolucionDetallePK == null && other.devolucionDetallePK != null) || (this.devolucionDetallePK != null && !this.devolucionDetallePK.equals(other.devolucionDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.DevolucionDetalle[ devolucionDetallePK=" + devolucionDetallePK + " ]";
    }
    
}
