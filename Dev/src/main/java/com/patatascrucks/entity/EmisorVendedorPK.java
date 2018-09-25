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
public class EmisorVendedorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idEmisor")
    private String idEmisor;
    @Basic(optional = false)
    @Column(name = "idVendedor")
    private String idVendedor;

    public EmisorVendedorPK() {
    }

    public EmisorVendedorPK(String idEmisor, String idVendedor) {
        this.idEmisor = idEmisor;
        this.idVendedor = idVendedor;
    }

    public String getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(String idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmisor != null ? idEmisor.hashCode() : 0);
        hash += (idVendedor != null ? idVendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmisorVendedorPK)) {
            return false;
        }
        EmisorVendedorPK other = (EmisorVendedorPK) object;
        if ((this.idEmisor == null && other.idEmisor != null) || (this.idEmisor != null && !this.idEmisor.equals(other.idEmisor))) {
            return false;
        }
        if ((this.idVendedor == null && other.idVendedor != null) || (this.idVendedor != null && !this.idVendedor.equals(other.idVendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.EmisorVendedorPK[ idEmisor=" + idEmisor + ", idVendedor=" + idVendedor + " ]";
    }
    
}
