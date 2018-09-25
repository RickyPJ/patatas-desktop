/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ricardo
 */
@Embeddable
public class ImpuestotarifaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idImpuesto")
    private int idImpuesto;
    @Basic(optional = false)
    @Column(name = "idTarifa")
    private int idTarifa;

    public ImpuestotarifaPK() {
    }

    public ImpuestotarifaPK(int idImpuesto, int idTarifa) {
        this.idImpuesto = idImpuesto;
        this.idTarifa = idTarifa;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idImpuesto;
        hash += (int) idTarifa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpuestotarifaPK)) {
            return false;
        }
        ImpuestotarifaPK other = (ImpuestotarifaPK) object;
        if (this.idImpuesto != other.idImpuesto) {
            return false;
        }
        if (this.idTarifa != other.idTarifa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.ImpuestotarifaPK[ idImpuesto=" + idImpuesto + ", idTarifa=" + idTarifa + " ]";
    }
    
}
