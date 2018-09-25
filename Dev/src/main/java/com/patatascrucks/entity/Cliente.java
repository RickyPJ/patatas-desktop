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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByComercial", query = "SELECT c FROM Cliente c WHERE c.comercial = :comercial")
    , @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email")
    , @NamedQuery(name = "Cliente.findByLatitud", query = "SELECT c FROM Cliente c WHERE c.latitud = :latitud")
    , @NamedQuery(name = "Cliente.findByLongitud", query = "SELECT c FROM Cliente c WHERE c.longitud = :longitud")
    , @NamedQuery(name = "Cliente.findByIdIdentificacion", query = "SELECT c FROM Cliente c WHERE c.idIdentificacion = :idIdentificacion")
    , @NamedQuery(name = "Cliente.findByActivo", query = "SELECT c FROM Cliente c WHERE c.activo = :activo")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCliente")
    private String idCliente;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "comercial")
    private String comercial;
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Basic(optional = false)
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Basic(optional = false)
    @Column(name = "idIdentificacion")
    private String idIdentificacion;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;

    public Cliente() {
    }

    public Cliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String idCliente, String nombre, String comercial, String telefono, String email, BigDecimal latitud, BigDecimal longitud, String idIdentificacion, boolean activo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.comercial = comercial;
        this.telefono = telefono;
        this.email = email;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idIdentificacion = idIdentificacion;
        this.activo = activo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComercial() {
        return comercial;
    }

    public void setComercial(String comercial) {
        this.comercial = comercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(String idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Cliente[ idCliente=" + idCliente + " ]";
    }
    
}
