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
@Table(name = "item_impuestotarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemImpuestotarifa.findAll", query = "SELECT i FROM ItemImpuestotarifa i")
    , @NamedQuery(name = "ItemImpuestotarifa.findByIdProducto", query = "SELECT i FROM ItemImpuestotarifa i WHERE i.itemImpuestotarifaPK.idProducto = :idProducto")
    , @NamedQuery(name = "ItemImpuestotarifa.findByIdGrupo", query = "SELECT i FROM ItemImpuestotarifa i WHERE i.itemImpuestotarifaPK.idGrupo = :idGrupo")
    , @NamedQuery(name = "ItemImpuestotarifa.findByIdImpuesto", query = "SELECT i FROM ItemImpuestotarifa i WHERE i.itemImpuestotarifaPK.idImpuesto = :idImpuesto")
    , @NamedQuery(name = "ItemImpuestotarifa.findByIdTarifa", query = "SELECT i FROM ItemImpuestotarifa i WHERE i.itemImpuestotarifaPK.idTarifa = :idTarifa")})
public class ItemImpuestotarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemImpuestotarifaPK itemImpuestotarifaPK;

    public ItemImpuestotarifa() {
    }

    public ItemImpuestotarifa(ItemImpuestotarifaPK itemImpuestotarifaPK) {
        this.itemImpuestotarifaPK = itemImpuestotarifaPK;
    }

    public ItemImpuestotarifa(int idProducto, int idGrupo, int idImpuesto, int idTarifa) {
        this.itemImpuestotarifaPK = new ItemImpuestotarifaPK(idProducto, idGrupo, idImpuesto, idTarifa);
    }

    public ItemImpuestotarifaPK getItemImpuestotarifaPK() {
        return itemImpuestotarifaPK;
    }

    public void setItemImpuestotarifaPK(ItemImpuestotarifaPK itemImpuestotarifaPK) {
        this.itemImpuestotarifaPK = itemImpuestotarifaPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemImpuestotarifaPK != null ? itemImpuestotarifaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemImpuestotarifa)) {
            return false;
        }
        ItemImpuestotarifa other = (ItemImpuestotarifa) object;
        if ((this.itemImpuestotarifaPK == null && other.itemImpuestotarifaPK != null) || (this.itemImpuestotarifaPK != null && !this.itemImpuestotarifaPK.equals(other.itemImpuestotarifaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.ItemImpuestotarifa[ itemImpuestotarifaPK=" + itemImpuestotarifaPK + " ]";
    }
    
}
