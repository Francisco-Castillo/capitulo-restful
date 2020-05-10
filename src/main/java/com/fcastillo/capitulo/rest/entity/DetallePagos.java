/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "DetallePagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallePagos.findAll", query = "SELECT d FROM DetallePagos d"),
    @NamedQuery(name = "DetallePagos.findByIdpago", query = "SELECT d FROM DetallePagos d WHERE d.detallePagosPK.idpago = :idpago"),
    @NamedQuery(name = "DetallePagos.findByIdcuota", query = "SELECT d FROM DetallePagos d WHERE d.detallePagosPK.idcuota = :idcuota"),
    @NamedQuery(name = "DetallePagos.findByRecargo", query = "SELECT d FROM DetallePagos d WHERE d.recargo = :recargo"),
    @NamedQuery(name = "DetallePagos.findByMontoabonado", query = "SELECT d FROM DetallePagos d WHERE d.montoabonado = :montoabonado")})
public class DetallePagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallePagosPK detallePagosPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "recargo")
    private BigDecimal recargo;
    @Column(name = "montoabonado")
    private BigDecimal montoabonado;
    @JoinColumn(name = "idcuota", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuotas cuotas;
    @JoinColumn(name = "idpago", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pagos pagos;

    public DetallePagos() {
    }

    public DetallePagos(DetallePagosPK detallePagosPK) {
        this.detallePagosPK = detallePagosPK;
    }

    public DetallePagos(int idpago, int idcuota) {
        this.detallePagosPK = new DetallePagosPK(idpago, idcuota);
    }

    public DetallePagosPK getDetallePagosPK() {
        return detallePagosPK;
    }

    public void setDetallePagosPK(DetallePagosPK detallePagosPK) {
        this.detallePagosPK = detallePagosPK;
    }

    public BigDecimal getRecargo() {
        return recargo;
    }

    public void setRecargo(BigDecimal recargo) {
        this.recargo = recargo;
    }

    public BigDecimal getMontoabonado() {
        return montoabonado;
    }

    public void setMontoabonado(BigDecimal montoabonado) {
        this.montoabonado = montoabonado;
    }

    public Cuotas getCuotas() {
        return cuotas;
    }

    public void setCuotas(Cuotas cuotas) {
        this.cuotas = cuotas;
    }

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallePagosPK != null ? detallePagosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePagos)) {
            return false;
        }
        DetallePagos other = (DetallePagos) object;
        if ((this.detallePagosPK == null && other.detallePagosPK != null) || (this.detallePagosPK != null && !this.detallePagosPK.equals(other.detallePagosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fcastillo.capitulo.rest.entity.DetallePagos[ detallePagosPK=" + detallePagosPK + " ]";
    }
    
}
