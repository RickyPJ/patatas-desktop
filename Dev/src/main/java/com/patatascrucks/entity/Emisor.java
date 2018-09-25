/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "emisor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emisor.findAll", query = "SELECT e FROM Emisor e")
    , @NamedQuery(name = "Emisor.findByIdEmisor", query = "SELECT e FROM Emisor e WHERE e.idEmisor = :idEmisor")
    , @NamedQuery(name = "Emisor.findByNombre", query = "SELECT e FROM Emisor e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Emisor.findByComercial", query = "SELECT e FROM Emisor e WHERE e.comercial = :comercial")
    , @NamedQuery(name = "Emisor.findByDireccionMatriz", query = "SELECT e FROM Emisor e WHERE e.direccionMatriz = :direccionMatriz")
    , @NamedQuery(name = "Emisor.findByDireccionEmisor", query = "SELECT e FROM Emisor e WHERE e.direccionEmisor = :direccionEmisor")
    , @NamedQuery(name = "Emisor.findByEstablecimiento", query = "SELECT e FROM Emisor e WHERE e.establecimiento = :establecimiento")
    , @NamedQuery(name = "Emisor.findByContabilidad", query = "SELECT e FROM Emisor e WHERE e.contabilidad = :contabilidad")
    , @NamedQuery(name = "Emisor.findByIdAmbiente", query = "SELECT e FROM Emisor e WHERE e.idAmbiente = :idAmbiente")
    , @NamedQuery(name = "Emisor.findByIdEmision", query = "SELECT e FROM Emisor e WHERE e.idEmision = :idEmision")})
public class Emisor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEmisor")
    private String idEmisor;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "comercial")
    private String comercial;
    @Basic(optional = false)
    @Column(name = "direccionMatriz")
    private String direccionMatriz;
    @Column(name = "direccionEmisor")
    private String direccionEmisor;
    @Basic(optional = false)
    @Column(name = "establecimiento")
    private String establecimiento;
    @Basic(optional = false)
    @Column(name = "contabilidad")
    private boolean contabilidad;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @Basic(optional = false)
    @Column(name = "idAmbiente")
    private int idAmbiente;
    @Basic(optional = false)
    @Column(name = "idEmision")
    private int idEmision;

    public Emisor() {
    }

    public Emisor(String idEmisor) {
        this.idEmisor = idEmisor;
    }

    public Emisor(String idEmisor, String nombre, String direccionMatriz, String establecimiento, boolean contabilidad, int idAmbiente, int idEmision) {
        this.idEmisor = idEmisor;
        this.nombre = nombre;
        this.direccionMatriz = direccionMatriz;
        this.establecimiento = establecimiento;
        this.contabilidad = contabilidad;
        this.idAmbiente = idAmbiente;
        this.idEmision = idEmision;
    }

    public String getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(String idEmisor) {
        this.idEmisor = idEmisor;
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

    public String getDireccionMatriz() {
        return direccionMatriz;
    }

    public void setDireccionMatriz(String direccionMatriz) {
        this.direccionMatriz = direccionMatriz;
    }

    public String getDireccionEmisor() {
        return direccionEmisor;
    }

    public void setDireccionEmisor(String direccionEmisor) {
        this.direccionEmisor = direccionEmisor;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public boolean getContabilidad() {
        return contabilidad;
    }

    public void setContabilidad(boolean contabilidad) {
        this.contabilidad = contabilidad;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public int getIdEmision() {
        return idEmision;
    }

    public void setIdEmision(int idEmision) {
        this.idEmision = idEmision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmisor != null ? idEmisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emisor)) {
            return false;
        }
        Emisor other = (Emisor) object;
        if ((this.idEmisor == null && other.idEmisor != null) || (this.idEmisor != null && !this.idEmisor.equals(other.idEmisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.patatascrucks.entity.Emisor[ idEmisor=" + idEmisor + " ]";
    }
    
}
