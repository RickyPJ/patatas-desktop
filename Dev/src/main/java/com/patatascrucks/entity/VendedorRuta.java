/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
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
@Table(name = "vendedor_ruta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VendedorRuta.findAll", query = "SELECT v FROM VendedorRuta v")
    , @NamedQuery(name = "VendedorRuta.findByIdVendedor", query = "SELECT v FROM VendedorRuta v WHERE v.vendedorRutaPK.idVendedor = :idVendedor")
    , @NamedQuery(name = "VendedorRuta.findByIdRuta", query = "SELECT v FROM VendedorRuta v WHERE v.vendedorRutaPK.idRuta = :idRuta")
    , @NamedQuery(name = "VendedorRuta.findByNotas", query = "SELECT v FROM VendedorRuta v WHERE v.notas = :notas")})
public class VendedorRuta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VendedorRutaPK vendedorRutaPK;
    @Column(name = "notas")
    private String notas;

    public VendedorRuta() {
    }

    public VendedorRuta(VendedorRutaPK vendedorRutaPK) {
        this.vendedorRutaPK = vendedorRutaPK;
    }

    public VendedorRuta(String idVendedor, int idRuta) {
        this.vendedorRutaPK = new VendedorRutaPK(idVendedor, idRuta);
    }

    public VendedorRutaPK getVendedorRutaPK() {
        return vendedorRutaPK;
    }

    public void setVendedorRutaPK(VendedorRutaPK vendedorRutaPK) {
        this.vendedorRutaPK = vendedorRutaPK;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendedorRutaPK != null ? vendedorRutaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VendedorRuta)) {
            return false;
        }
        VendedorRuta other = (VendedorRuta) object;
        if ((this.vendedorRutaPK == null && other.vendedorRutaPK != null) || (this.vendedorRutaPK != null && !this.vendedorRutaPK.equals(other.vendedorRutaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.VendedorRuta[ vendedorRutaPK=" + vendedorRutaPK + " ]";
    }
    
}
