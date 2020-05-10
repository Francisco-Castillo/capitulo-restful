/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fcastillo
 */
@Embeddable
public class DetallePagosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idpago")
    private int idpago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcuota")
    private int idcuota;

    public DetallePagosPK() {
    }

    public DetallePagosPK(int idpago, int idcuota) {
        this.idpago = idpago;
        this.idcuota = idcuota;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdcuota() {
        return idcuota;
    }

    public void setIdcuota(int idcuota) {
        this.idcuota = idcuota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idpago;
        hash += (int) idcuota;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePagosPK)) {
            return false;
        }
        DetallePagosPK other = (DetallePagosPK) object;
        if (this.idpago != other.idpago) {
            return false;
        }
        if (this.idcuota != other.idcuota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fcastillo.capitulo.rest.entity.DetallePagosPK[ idpago=" + idpago + ", idcuota=" + idcuota + " ]";
    }
    
}
