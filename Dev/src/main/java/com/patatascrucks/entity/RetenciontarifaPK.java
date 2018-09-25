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
public class RetenciontarifaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idRetencion")
    private int idRetencion;
    @Basic(optional = false)
    @Column(name = "idTarifa")
    private int idTarifa;

    public RetenciontarifaPK() {
    }

    public RetenciontarifaPK(int idRetencion, int idTarifa) {
        this.idRetencion = idRetencion;
        this.idTarifa = idTarifa;
    }

    public int getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(int idRetencion) {
        this.idRetencion = idRetencion;
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
        hash += (int) idRetencion;
        hash += (int) idTarifa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetenciontarifaPK)) {
            return false;
        }
        RetenciontarifaPK other = (RetenciontarifaPK) object;
        if (this.idRetencion != other.idRetencion) {
            return false;
        }
        if (this.idTarifa != other.idTarifa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.RetenciontarifaPK[ idRetencion=" + idRetencion + ", idTarifa=" + idTarifa + " ]";
    }
    
}
