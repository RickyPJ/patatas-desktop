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
@Table(name = "impuestotarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuestotarifa.findAll", query = "SELECT i FROM Impuestotarifa i")
    , @NamedQuery(name = "Impuestotarifa.findByIdImpuesto", query = "SELECT i FROM Impuestotarifa i WHERE i.impuestotarifaPK.idImpuesto = :idImpuesto")
    , @NamedQuery(name = "Impuestotarifa.findByIdTarifa", query = "SELECT i FROM Impuestotarifa i WHERE i.impuestotarifaPK.idTarifa = :idTarifa")
    , @NamedQuery(name = "Impuestotarifa.findByNombreTarifa", query = "SELECT i FROM Impuestotarifa i WHERE i.nombreTarifa = :nombreTarifa")
    , @NamedQuery(name = "Impuestotarifa.findByTarifa", query = "SELECT i FROM Impuestotarifa i WHERE i.tarifa = :tarifa")})
public class Impuestotarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ImpuestotarifaPK impuestotarifaPK;
    @Basic(optional = false)
    @Column(name = "nombreTarifa")
    private String nombreTarifa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "tarifa")
    private BigDecimal tarifa;

    public Impuestotarifa() {
    }

    public Impuestotarifa(ImpuestotarifaPK impuestotarifaPK) {
        this.impuestotarifaPK = impuestotarifaPK;
    }

    public Impuestotarifa(ImpuestotarifaPK impuestotarifaPK, String nombreTarifa, BigDecimal tarifa) {
        this.impuestotarifaPK = impuestotarifaPK;
        this.nombreTarifa = nombreTarifa;
        this.tarifa = tarifa;
    }

    public Impuestotarifa(int idImpuesto, int idTarifa) {
        this.impuestotarifaPK = new ImpuestotarifaPK(idImpuesto, idTarifa);
    }

    public ImpuestotarifaPK getImpuestotarifaPK() {
        return impuestotarifaPK;
    }

    public void setImpuestotarifaPK(ImpuestotarifaPK impuestotarifaPK) {
        this.impuestotarifaPK = impuestotarifaPK;
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
        hash += (impuestotarifaPK != null ? impuestotarifaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuestotarifa)) {
            return false;
        }
        Impuestotarifa other = (Impuestotarifa) object;
        if ((this.impuestotarifaPK == null && other.impuestotarifaPK != null) || (this.impuestotarifaPK != null && !this.impuestotarifaPK.equals(other.impuestotarifaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Impuestotarifa[ impuestotarifaPK=" + impuestotarifaPK + " ]";
    }
    
}
