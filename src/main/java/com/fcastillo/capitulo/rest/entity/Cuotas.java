/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.rest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "Cuotas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuotas.findAll", query = "SELECT c FROM Cuotas c"),
    @NamedQuery(name = "Cuotas.findById", query = "SELECT c FROM Cuotas c WHERE c.id = :id"),
    @NamedQuery(name = "Cuotas.findByNro", query = "SELECT c FROM Cuotas c WHERE c.nro = :nro"),
    @NamedQuery(name = "Cuotas.findByValor", query = "SELECT c FROM Cuotas c WHERE c.valor = :valor"),
    @NamedQuery(name = "Cuotas.findByFvencimiento", query = "SELECT c FROM Cuotas c WHERE c.fvencimiento = :fvencimiento"),
    @NamedQuery(name = "Cuotas.findByEstado", query = "SELECT c FROM Cuotas c WHERE c.estado = :estado")})
public class Cuotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro")
    private int nro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fvencimiento")
    @Temporal(TemporalType.DATE)
    private Date fvencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "idprestamo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Prestamos idprestamo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuotas")
    private List<DetallePagos> detallePagosList;

    public Cuotas() {
    }

    public Cuotas(Integer id) {
        this.id = id;
    }

    public Cuotas(Integer id, int nro, BigDecimal valor, Date fvencimiento, int estado) {
        this.id = id;
        this.nro = nro;
        this.valor = valor;
        this.fvencimiento = fvencimiento;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFvencimiento() {
        return fvencimiento;
    }

    public void setFvencimiento(Date fvencimiento) {
        this.fvencimiento = fvencimiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Prestamos getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(Prestamos idprestamo) {
        this.idprestamo = idprestamo;
    }

    @XmlTransient
    public List<DetallePagos> getDetallePagosList() {
        return detallePagosList;
    }

    public void setDetallePagosList(List<DetallePagos> detallePagosList) {
        this.detallePagosList = detallePagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuotas)) {
            return false;
        }
        Cuotas other = (Cuotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fcastillo.capitulo.rest.entity.Cuotas[ id=" + id + " ]";
    }
    
}
