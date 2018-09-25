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
@Table(name = "produccion_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProduccionDetalle.findAll", query = "SELECT p FROM ProduccionDetalle p")
    , @NamedQuery(name = "ProduccionDetalle.findByIdSocio", query = "SELECT p FROM ProduccionDetalle p WHERE p.produccionDetallePK.idSocio = :idSocio")
    , @NamedQuery(name = "ProduccionDetalle.findByFecha", query = "SELECT p FROM ProduccionDetalle p WHERE p.produccionDetallePK.fecha = :fecha")
    , @NamedQuery(name = "ProduccionDetalle.findByIdProducto", query = "SELECT p FROM ProduccionDetalle p WHERE p.produccionDetallePK.idProducto = :idProducto")
    , @NamedQuery(name = "ProduccionDetalle.findByIdGrupo", query = "SELECT p FROM ProduccionDetalle p WHERE p.produccionDetallePK.idGrupo = :idGrupo")
    , @NamedQuery(name = "ProduccionDetalle.findByCantidad", query = "SELECT p FROM ProduccionDetalle p WHERE p.cantidad = :cantidad")})
public class ProduccionDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProduccionDetallePK produccionDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "cantidad")
    private BigDecimal cantidad;

    public ProduccionDetalle() {
    }

    public ProduccionDetalle(ProduccionDetallePK produccionDetallePK) {
        this.produccionDetallePK = produccionDetallePK;
    }

    public ProduccionDetalle(ProduccionDetallePK produccionDetallePK, BigDecimal cantidad) {
        this.produccionDetallePK = produccionDetallePK;
        this.cantidad = cantidad;
    }

    public ProduccionDetalle(String idSocio, Date fecha, int idProducto, int idGrupo) {
        this.produccionDetallePK = new ProduccionDetallePK(idSocio, fecha, idProducto, idGrupo);
    }

    public ProduccionDetallePK getProduccionDetallePK() {
        return produccionDetallePK;
    }

    public void setProduccionDetallePK(ProduccionDetallePK produccionDetallePK) {
        this.produccionDetallePK = produccionDetallePK;
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
        hash += (produccionDetallePK != null ? produccionDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduccionDetalle)) {
            return false;
        }
        ProduccionDetalle other = (ProduccionDetalle) object;
        if ((this.produccionDetallePK == null && other.produccionDetallePK != null) || (this.produccionDetallePK != null && !this.produccionDetallePK.equals(other.produccionDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.ProduccionDetalle[ produccionDetallePK=" + produccionDetallePK + " ]";
    }
    
}
