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
public class RutaClientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idRuta")
    private int idRuta;
    @Basic(optional = false)
    @Column(name = "idCliente")
    private String idCliente;

    public RutaClientePK() {
    }

    public RutaClientePK(int idRuta, String idCliente) {
        this.idRuta = idRuta;
        this.idCliente = idCliente;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRuta;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaClientePK)) {
            return false;
        }
        RutaClientePK other = (RutaClientePK) object;
        if (this.idRuta != other.idRuta) {
            return false;
        }
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.RutaClientePK[ idRuta=" + idRuta + ", idCliente=" + idCliente + " ]";
    }
    
}
