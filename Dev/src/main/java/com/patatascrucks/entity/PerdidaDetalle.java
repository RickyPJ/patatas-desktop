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
@Table(name = "perdida_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerdidaDetalle.findAll", query = "SELECT p FROM PerdidaDetalle p")
    , @NamedQuery(name = "PerdidaDetalle.findByIdEmisor", query = "SELECT p FROM PerdidaDetalle p WHERE p.perdidaDetallePK.idEmisor = :idEmisor")
    , @NamedQuery(name = "PerdidaDetalle.findByIdVendedor", query = "SELECT p FROM PerdidaDetalle p WHERE p.perdidaDetallePK.idVendedor = :idVendedor")
    , @NamedQuery(name = "PerdidaDetalle.findByIdPerdida", query = "SELECT p FROM PerdidaDetalle p WHERE p.perdidaDetallePK.idPerdida = :idPerdida")
    , @NamedQuery(name = "PerdidaDetalle.findByFecha", query = "SELECT p FROM PerdidaDetalle p WHERE p.perdidaDetallePK.fecha = :fecha")
    , @NamedQuery(name = "PerdidaDetalle.findByIdProducto", query = "SELECT p FROM PerdidaDetalle p WHERE p.perdidaDetallePK.idProducto = :idProducto")
    , @NamedQuery(name = "PerdidaDetalle.findByIdGrupo", query = "SELECT p FROM PerdidaDetalle p WHERE p.perdidaDetallePK.idGrupo = :idGrupo")
    , @NamedQuery(name = "PerdidaDetalle.findByCantidad", query = "SELECT p FROM PerdidaDetalle p WHERE p.cantidad = :cantidad")})
public class PerdidaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerdidaDetallePK perdidaDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;

    public PerdidaDetalle() {
    }

    public PerdidaDetalle(PerdidaDetallePK perdidaDetallePK) {
        this.perdidaDetallePK = perdidaDetallePK;
    }

    public PerdidaDetalle(PerdidaDetallePK perdidaDetallePK, BigDecimal cantidad) {
        this.perdidaDetallePK = perdidaDetallePK;
        this.cantidad = cantidad;
    }

    public PerdidaDetalle(String idEmisor, String idVendedor, String idPerdida, Date fecha, int idProducto, int idGrupo) {
        this.perdidaDetallePK = new PerdidaDetallePK(idEmisor, idVendedor, idPerdida, fecha, idProducto, idGrupo);
    }

    public PerdidaDetallePK getPerdidaDetallePK() {
        return perdidaDetallePK;
    }

    public void setPerdidaDetallePK(PerdidaDetallePK perdidaDetallePK) {
        this.perdidaDetallePK = perdidaDetallePK;
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
        hash += (perdidaDetallePK != null ? perdidaDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerdidaDetalle)) {
            return false;
        }
        PerdidaDetalle other = (PerdidaDetalle) object;
        if ((this.perdidaDetallePK == null && other.perdidaDetallePK != null) || (this.perdidaDetallePK != null && !this.perdidaDetallePK.equals(other.perdidaDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.PerdidaDetalle[ perdidaDetallePK=" + perdidaDetallePK + " ]";
    }
    
}
