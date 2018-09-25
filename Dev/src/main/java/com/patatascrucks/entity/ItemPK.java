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
public class ItemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private int idGrupo;

    public ItemPK() {
    }

    public ItemPK(int idProducto, int idGrupo) {
        this.idProducto = idProducto;
        this.idGrupo = idGrupo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProducto;
        hash += (int) idGrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemPK)) {
            return false;
        }
        ItemPK other = (ItemPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.ItemPK[ idProducto=" + idProducto + ", idGrupo=" + idGrupo + " ]";
    }
    
}
