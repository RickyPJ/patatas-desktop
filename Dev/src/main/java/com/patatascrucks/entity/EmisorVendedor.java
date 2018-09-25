/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
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
@Table(name = "emisor_vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmisorVendedor.findAll", query = "SELECT e FROM EmisorVendedor e")
    , @NamedQuery(name = "EmisorVendedor.findByIdEmisor", query = "SELECT e FROM EmisorVendedor e WHERE e.emisorVendedorPK.idEmisor = :idEmisor")
    , @NamedQuery(name = "EmisorVendedor.findByIdVendedor", query = "SELECT e FROM EmisorVendedor e WHERE e.emisorVendedorPK.idVendedor = :idVendedor")})
public class EmisorVendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmisorVendedorPK emisorVendedorPK;

    public EmisorVendedor() {
    }

    public EmisorVendedor(EmisorVendedorPK emisorVendedorPK) {
        this.emisorVendedorPK = emisorVendedorPK;
    }

    public EmisorVendedor(String idEmisor, String idVendedor) {
        this.emisorVendedorPK = new EmisorVendedorPK(idEmisor, idVendedor);
    }

    public EmisorVendedorPK getEmisorVendedorPK() {
        return emisorVendedorPK;
    }

    public void setEmisorVendedorPK(EmisorVendedorPK emisorVendedorPK) {
        this.emisorVendedorPK = emisorVendedorPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emisorVendedorPK != null ? emisorVendedorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmisorVendedor)) {
            return false;
        }
        EmisorVendedor other = (EmisorVendedor) object;
        if ((this.emisorVendedorPK == null && other.emisorVendedorPK != null) || (this.emisorVendedorPK != null && !this.emisorVendedorPK.equals(other.emisorVendedorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.EmisorVendedor[ emisorVendedorPK=" + emisorVendedorPK + " ]";
    }
    
}
