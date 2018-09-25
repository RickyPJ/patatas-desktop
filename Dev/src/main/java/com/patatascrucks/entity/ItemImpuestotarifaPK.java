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
public class ItemImpuestotarifaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private int idGrupo;
    @Basic(optional = false)
    @Column(name = "idImpuesto")
    private int idImpuesto;
    @Basic(optional = false)
    @Column(name = "idTarifa")
    private int idTarifa;

    public ItemImpuestotarifaPK() {
    }

    public ItemImpuestotarifaPK(int idProducto, int idGrupo, int idImpuesto, int idTarifa) {
        this.idProducto = idProducto;
        this.idGrupo = idGrupo;
        this.idImpuesto = idImpuesto;
        this.idTarifa = idTarifa;
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
        hash += (int) idProducto;
        hash += (int) idGrupo;
        hash += (int) idImpuesto;
        hash += (int) idTarifa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemImpuestotarifaPK)) {
            return false;
        }
        ItemImpuestotarifaPK other = (ItemImpuestotarifaPK) object;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idGrupo != other.idGrupo) {
            return false;
        }
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
        return "com.patatascrucks.entity.ItemImpuestotarifaPK[ idProducto=" + idProducto + ", idGrupo=" + idGrupo + ", idImpuesto=" + idImpuesto + ", idTarifa=" + idTarifa + " ]";
    }
    
}
