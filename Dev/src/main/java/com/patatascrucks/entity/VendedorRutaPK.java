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
public class VendedorRutaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idVendedor")
    private String idVendedor;
    @Basic(optional = false)
    @Column(name = "idRuta")
    private int idRuta;

    public VendedorRutaPK() {
    }

    public VendedorRutaPK(String idVendedor, int idRuta) {
        this.idVendedor = idVendedor;
        this.idRuta = idRuta;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVendedor != null ? idVendedor.hashCode() : 0);
        hash += (int) idRuta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendedorRutaPK)) {
            return false;
        }
        VendedorRutaPK other = (VendedorRutaPK) object;
        if ((this.idVendedor == null && other.idVendedor != null) || (this.idVendedor != null && !this.idVendedor.equals(other.idVendedor))) {
            return false;
        }
        if (this.idRuta != other.idRuta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VendedorRutaPK[ idVendedor=" + idVendedor + ", idRuta=" + idRuta + " ]";
    }
    
}
