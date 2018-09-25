/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "retenciontarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retenciontarifa.findAll", query = "SELECT r FROM Retenciontarifa r")
    , @NamedQuery(name = "Retenciontarifa.findByIdRetencion", query = "SELECT r FROM Retenciontarifa r WHERE r.retenciontarifaPK.idRetencion = :idRetencion")
    , @NamedQuery(name = "Retenciontarifa.findByIdTarifa", query = "SELECT r FROM Retenciontarifa r WHERE r.retenciontarifaPK.idTarifa = :idTarifa")
    , @NamedQuery(name = "Retenciontarifa.findByNombreTarifa", query = "SELECT r FROM Retenciontarifa r WHERE r.nombreTarifa = :nombreTarifa")
    , @NamedQuery(name = "Retenciontarifa.findByTarifa", query = "SELECT r FROM Retenciontarifa r WHERE r.tarifa = :tarifa")})
public class Retenciontarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RetenciontarifaPK retenciontarifaPK;
    @Basic(optional = false)
    @Column(name = "nombreTarifa")
    private String nombreTarifa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "tarifa")
    private BigDecimal tarifa;

    public Retenciontarifa() {
    }

    public Retenciontarifa(RetenciontarifaPK retenciontarifaPK) {
        this.retenciontarifaPK = retenciontarifaPK;
    }

    public Retenciontarifa(RetenciontarifaPK retenciontarifaPK, String nombreTarifa, BigDecimal tarifa) {
        this.retenciontarifaPK = retenciontarifaPK;
        this.nombreTarifa = nombreTarifa;
        this.tarifa = tarifa;
    }

    public Retenciontarifa(int idRetencion, int idTarifa) {
        this.retenciontarifaPK = new RetenciontarifaPK(idRetencion, idTarifa);
    }

    public RetenciontarifaPK getRetenciontarifaPK() {
        return retenciontarifaPK;
    }

    public void setRetenciontarifaPK(RetenciontarifaPK retenciontarifaPK) {
        this.retenciontarifaPK = retenciontarifaPK;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retenciontarifaPK != null ? retenciontarifaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retenciontarifa)) {
            return false;
        }
        Retenciontarifa other = (Retenciontarifa) object;
        if ((this.retenciontarifaPK == null && other.retenciontarifaPK != null) || (this.retenciontarifaPK != null && !this.retenciontarifaPK.equals(other.retenciontarifaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Retenciontarifa[ retenciontarifaPK=" + retenciontarifaPK + " ]";
    }
    
}
