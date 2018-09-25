/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
    , @NamedQuery(name = "Item.findByIdProducto", query = "SELECT i FROM Item i WHERE i.itemPK.idProducto = :idProducto")
    , @NamedQuery(name = "Item.findByIdGrupo", query = "SELECT i FROM Item i WHERE i.itemPK.idGrupo = :idGrupo")
    , @NamedQuery(name = "Item.findByPrecioUnitario", query = "SELECT i FROM Item i WHERE i.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "Item.findByDescuento", query = "SELECT i FROM Item i WHERE i.descuento = :descuento")
    , @NamedQuery(name = "Item.findBySubsidio", query = "SELECT i FROM Item i WHERE i.subsidio = :subsidio")
    , @NamedQuery(name = "Item.findByNotas", query = "SELECT i FROM Item i WHERE i.notas = :notas")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemPK itemPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precioUnitario")
    private BigDecimal precioUnitario;
    @Basic(optional = false)
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Basic(optional = false)
    @Column(name = "subsidio")
    private BigDecimal subsidio;
    @Column(name = "notas")
    private String notas;

    public Item() {
    }

    public Item(ItemPK itemPK) {
        this.itemPK = itemPK;
    }

    public Item(ItemPK itemPK, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subsidio) {
        this.itemPK = itemPK;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subsidio = subsidio;
    }

    public Item(int idProducto, int idGrupo) {
        this.itemPK = new ItemPK(idProducto, idGrupo);
    }

    public ItemPK getItemPK() {
        return itemPK;
    }

    public void setItemPK(ItemPK itemPK) {
        this.itemPK = itemPK;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(BigDecimal subsidio) {
        this.subsidio = subsidio;
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
        hash += (itemPK != null ? itemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemPK == null && other.itemPK != null) || (this.itemPK != null && !this.itemPK.equals(other.itemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Item[ itemPK=" + itemPK + " ]";
    }
    
}
