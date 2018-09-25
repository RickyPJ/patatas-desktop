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
@Table(name = "despacho_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DespachoDetalle.findAll", query = "SELECT d FROM DespachoDetalle d")
    , @NamedQuery(name = "DespachoDetalle.findByIdEmisor", query = "SELECT d FROM DespachoDetalle d WHERE d.despachoDetallePK.idEmisor = :idEmisor")
    , @NamedQuery(name = "DespachoDetalle.findByIdVendedor", query = "SELECT d FROM DespachoDetalle d WHERE d.despachoDetallePK.idVendedor = :idVendedor")
    , @NamedQuery(name = "DespachoDetalle.findByIdDespacho", query = "SELECT d FROM DespachoDetalle d WHERE d.despachoDetallePK.idDespacho = :idDespacho")
    , @NamedQuery(name = "DespachoDetalle.findByFecha", query = "SELECT d FROM DespachoDetalle d WHERE d.despachoDetallePK.fecha = :fecha")
    , @NamedQuery(name = "DespachoDetalle.findByIdProducto", query = "SELECT d FROM DespachoDetalle d WHERE d.despachoDetallePK.idProducto = :idProducto")
    , @NamedQuery(name = "DespachoDetalle.findByIdGrupo", query = "SELECT d FROM DespachoDetalle d WHERE d.despachoDetallePK.idGrupo = :idGrupo")
    , @NamedQuery(name = "DespachoDetalle.findByCantidad", query = "SELECT d FROM DespachoDetalle d WHERE d.cantidad = :cantidad")})
public class DespachoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DespachoDetallePK despachoDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;

    public DespachoDetalle() {
    }

    public DespachoDetalle(DespachoDetallePK despachoDetallePK) {
        this.despachoDetallePK = despachoDetallePK;
    }

    public DespachoDetalle(DespachoDetallePK despachoDetallePK, BigDecimal cantidad) {
        this.despachoDetallePK = despachoDetallePK;
        this.cantidad = cantidad;
    }

    public DespachoDetalle(String idEmisor, String idVendedor, String idDespacho, Date fecha, int idProducto, int idGrupo) {
        this.despachoDetallePK = new DespachoDetallePK(idEmisor, idVendedor, idDespacho, fecha, idProducto, idGrupo);
    }

    public DespachoDetallePK getDespachoDetallePK() {
        return despachoDetallePK;
    }

    public void setDespachoDetallePK(DespachoDetallePK despachoDetallePK) {
        this.despachoDetallePK = despachoDetallePK;
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
        hash += (despachoDetallePK != null ? despachoDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DespachoDetalle)) {
            return false;
        }
        DespachoDetalle other = (DespachoDetalle) object;
        if ((this.despachoDetallePK == null && other.despachoDetallePK != null) || (this.despachoDetallePK != null && !this.despachoDetallePK.equals(other.despachoDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.DespachoDetalle[ despachoDetallePK=" + despachoDetallePK + " ]";
    }
    
}
