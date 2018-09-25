/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produccion.findAll", query = "SELECT p FROM Produccion p")
    , @NamedQuery(name = "Produccion.findByIdSocio", query = "SELECT p FROM Produccion p WHERE p.produccionPK.idSocio = :idSocio")
    , @NamedQuery(name = "Produccion.findByFecha", query = "SELECT p FROM Produccion p WHERE p.produccionPK.fecha = :fecha")
    , @NamedQuery(name = "Produccion.findByNotas", query = "SELECT p FROM Produccion p WHERE p.notas = :notas")})
public class Produccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProduccionPK produccionPK;
    @Column(name = "notas")
    private String notas;

    public Produccion() {
    }

    public Produccion(ProduccionPK produccionPK) {
        this.produccionPK = produccionPK;
    }

    public Produccion(String idSocio, Date fecha) {
        this.produccionPK = new ProduccionPK(idSocio, fecha);
    }

    public ProduccionPK getProduccionPK() {
        return produccionPK;
    }

    public void setProduccionPK(ProduccionPK produccionPK) {
        this.produccionPK = produccionPK;
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
        hash += (produccionPK != null ? produccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produccion)) {
            return false;
        }
        Produccion other = (Produccion) object;
        if ((this.produccionPK == null && other.produccionPK != null) || (this.produccionPK != null && !this.produccionPK.equals(other.produccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Produccion[ produccionPK=" + produccionPK + " ]";
    }
    
}
