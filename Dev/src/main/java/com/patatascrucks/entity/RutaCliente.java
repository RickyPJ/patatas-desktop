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
@Table(name = "ruta_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RutaCliente.findAll", query = "SELECT r FROM RutaCliente r")
    , @NamedQuery(name = "RutaCliente.findByIdRuta", query = "SELECT r FROM RutaCliente r WHERE r.rutaClientePK.idRuta = :idRuta")
    , @NamedQuery(name = "RutaCliente.findByIdCliente", query = "SELECT r FROM RutaCliente r WHERE r.rutaClientePK.idCliente = :idCliente")
    , @NamedQuery(name = "RutaCliente.findByNotas", query = "SELECT r FROM RutaCliente r WHERE r.notas = :notas")})
public class RutaCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RutaClientePK rutaClientePK;
    @Column(name = "notas")
    private String notas;

    public RutaCliente() {
    }

    public RutaCliente(RutaClientePK rutaClientePK) {
        this.rutaClientePK = rutaClientePK;
    }

    public RutaCliente(int idRuta, String idCliente) {
        this.rutaClientePK = new RutaClientePK(idRuta, idCliente);
    }

    public RutaClientePK getRutaClientePK() {
        return rutaClientePK;
    }

    public void setRutaClientePK(RutaClientePK rutaClientePK) {
        this.rutaClientePK = rutaClientePK;
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
        hash += (rutaClientePK != null ? rutaClientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RutaCliente)) {
            return false;
        }
        RutaCliente other = (RutaCliente) object;
        if ((this.rutaClientePK == null && other.rutaClientePK != null) || (this.rutaClientePK != null && !this.rutaClientePK.equals(other.rutaClientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.RutaCliente[ rutaClientePK=" + rutaClientePK + " ]";
    }
    
}
