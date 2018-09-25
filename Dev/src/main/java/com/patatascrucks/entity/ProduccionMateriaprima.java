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
@Table(name = "produccion_materiaprima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProduccionMateriaprima.findAll", query = "SELECT p FROM ProduccionMateriaprima p")
    , @NamedQuery(name = "ProduccionMateriaprima.findByIdSocio", query = "SELECT p FROM ProduccionMateriaprima p WHERE p.produccionMateriaprimaPK.idSocio = :idSocio")
    , @NamedQuery(name = "ProduccionMateriaprima.findByFecha", query = "SELECT p FROM ProduccionMateriaprima p WHERE p.produccionMateriaprimaPK.fecha = :fecha")
    , @NamedQuery(name = "ProduccionMateriaprima.findByIdMateriaPrima", query = "SELECT p FROM ProduccionMateriaprima p WHERE p.produccionMateriaprimaPK.idMateriaPrima = :idMateriaPrima")
    , @NamedQuery(name = "ProduccionMateriaprima.findByPeso", query = "SELECT p FROM ProduccionMateriaprima p WHERE p.peso = :peso")})
public class ProduccionMateriaprima implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProduccionMateriaprimaPK produccionMateriaprimaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "peso")
    private BigDecimal peso;

    public ProduccionMateriaprima() {
    }

    public ProduccionMateriaprima(ProduccionMateriaprimaPK produccionMateriaprimaPK) {
        this.produccionMateriaprimaPK = produccionMateriaprimaPK;
    }

    public ProduccionMateriaprima(ProduccionMateriaprimaPK produccionMateriaprimaPK, BigDecimal peso) {
        this.produccionMateriaprimaPK = produccionMateriaprimaPK;
        this.peso = peso;
    }

    public ProduccionMateriaprima(String idSocio, Date fecha, int idMateriaPrima) {
        this.produccionMateriaprimaPK = new ProduccionMateriaprimaPK(idSocio, fecha, idMateriaPrima);
    }

    public ProduccionMateriaprimaPK getProduccionMateriaprimaPK() {
        return produccionMateriaprimaPK;
    }

    public void setProduccionMateriaprimaPK(ProduccionMateriaprimaPK produccionMateriaprimaPK) {
        this.produccionMateriaprimaPK = produccionMateriaprimaPK;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produccionMateriaprimaPK != null ? produccionMateriaprimaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProduccionMateriaprima)) {
            return false;
        }
        ProduccionMateriaprima other = (ProduccionMateriaprima) object;
        if ((this.produccionMateriaprimaPK == null && other.produccionMateriaprimaPK != null) || (this.produccionMateriaprimaPK != null && !this.produccionMateriaprimaPK.equals(other.produccionMateriaprimaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.ProduccionMateriaprima[ produccionMateriaprimaPK=" + produccionMateriaprimaPK + " ]";
    }
    
}
