/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "formapago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formapago.findAll", query = "SELECT f FROM Formapago f")
    , @NamedQuery(name = "Formapago.findByIdFormaPago", query = "SELECT f FROM Formapago f WHERE f.idFormaPago = :idFormaPago")
    , @NamedQuery(name = "Formapago.findByFormaPago", query = "SELECT f FROM Formapago f WHERE f.formaPago = :formaPago")
    , @NamedQuery(name = "Formapago.findByFechaInicio", query = "SELECT f FROM Formapago f WHERE f.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Formapago.findByFechaFin", query = "SELECT f FROM Formapago f WHERE f.fechaFin = :fechaFin")})
public class Formapago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFormaPago")
    private String idFormaPago;
    @Basic(optional = false)
    @Column(name = "formaPago")
    private String formaPago;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public Formapago() {
    }

    public Formapago(String idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public Formapago(String idFormaPago, String formaPago, Date fechaInicio) {
        this.idFormaPago = idFormaPago;
        this.formaPago = formaPago;
        this.fechaInicio = fechaInicio;
    }

    public String getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(String idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPago != null ? idFormaPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formapago)) {
            return false;
        }
        Formapago other = (Formapago) object;
        if ((this.idFormaPago == null && other.idFormaPago != null) || (this.idFormaPago != null && !this.idFormaPago.equals(other.idFormaPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Formapago[ idFormaPago=" + idFormaPago + " ]";
    }
    
}
