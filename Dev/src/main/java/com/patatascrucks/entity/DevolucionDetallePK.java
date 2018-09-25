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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ricardo
 */
@Embeddable
public class DevolucionDetallePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idEmisor")
    private String idEmisor;
    @Basic(optional = false)
    @Column(name = "idVendedor")
    private String idVendedor;
    @Basic(optional = false)
    @Column(name = "idDevolucion")
    private String idDevolucion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "idGrupo")
    private int idGrupo;

    public DevolucionDetallePK() {
    }

    public DevolucionDetallePK(String idEmisor, String idVendedor, String idDevolucion, Date fecha, int idProducto, int idGrupo) {
        this.idEmisor = idEmisor;
        this.idVendedor = idVendedor;
        this.idDevolucion = idDevolucion;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.idGrupo = idGrupo;
    }

    public String getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(String idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(String idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        hash += (idEmisor != null ? idEmisor.hashCode() : 0);
        hash += (idVendedor != null ? idVendedor.hashCode() : 0);
        hash += (idDevolucion != null ? idDevolucion.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) idProducto;
        hash += (int) idGrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionDetallePK)) {
            return false;
        }
        DevolucionDetallePK other = (DevolucionDetallePK) object;
        if ((this.idEmisor == null && other.idEmisor != null) || (this.idEmisor != null && !this.idEmisor.equals(other.idEmisor))) {
            return false;
        }
        if ((this.idVendedor == null && other.idVendedor != null) || (this.idVendedor != null && !this.idVendedor.equals(other.idVendedor))) {
            return false;
        }
        if ((this.idDevolucion == null && other.idDevolucion != null) || (this.idDevolucion != null && !this.idDevolucion.equals(other.idDevolucion))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
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
        return "com.patatascrucks.entity.DevolucionDetallePK[ idEmisor=" + idEmisor + ", idVendedor=" + idVendedor + ", idDevolucion=" + idDevolucion + ", fecha=" + fecha + ", idProducto=" + idProducto + ", idGrupo=" + idGrupo + " ]";
    }
    
}
