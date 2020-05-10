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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "Prestamos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamos.findAll", query = "SELECT p FROM Prestamos p"),
    @NamedQuery(name = "Prestamos.findById", query = "SELECT p FROM Prestamos p WHERE p.id = :id"),
    @NamedQuery(name = "Prestamos.findByDineroprestado", query = "SELECT p FROM Prestamos p WHERE p.dineroprestado = :dineroprestado"),
    @NamedQuery(name = "Prestamos.findByFsolicitud", query = "SELECT p FROM Prestamos p WHERE p.fsolicitud = :fsolicitud"),
    @NamedQuery(name = "Prestamos.findByCantidadcuotas", query = "SELECT p FROM Prestamos p WHERE p.cantidadcuotas = :cantidadcuotas"),
    @NamedQuery(name = "Prestamos.findByTna", query = "SELECT p FROM Prestamos p WHERE p.tna = :tna"),
    @NamedQuery(name = "Prestamos.findByGastosadmin", query = "SELECT p FROM Prestamos p WHERE p.gastosadmin = :gastosadmin"),
    @NamedQuery(name = "Prestamos.findBySegvida", query = "SELECT p FROM Prestamos p WHERE p.segvida = :segvida"),
    @NamedQuery(name = "Prestamos.findByIva", query = "SELECT p FROM Prestamos p WHERE p.iva = :iva"),
    @NamedQuery(name = "Prestamos.findByObservaciones", query = "SELECT p FROM Prestamos p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "Prestamos.findByEstado", query = "SELECT p FROM Prestamos p WHERE p.estado = :estado")})
public class Prestamos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "dineroprestado")
    private BigDecimal dineroprestado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fsolicitud")
    @Temporal(TemporalType.DATE)
    private Date fsolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadcuotas")
    private int cantidadcuotas;
    @Column(name = "tna")
    private BigDecimal tna;
    @Column(name = "gastosadmin")
    private BigDecimal gastosadmin;
    @Column(name = "segvida")
    private BigDecimal segvida;
    @Column(name = "iva")
    private BigDecimal iva;
    @Size(max = 400)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprestamo")
    private List<Cuotas> cuotasList;
    @JoinColumn(name = "idcliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clientes idcliente;
    @JoinColumn(name = "idoperador", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios idoperador;

    public Prestamos() {
    }

    public Prestamos(Integer id) {
        this.id = id;
    }

    public Prestamos(Integer id, BigDecimal dineroprestado, Date fsolicitud, int cantidadcuotas, int estado) {
        this.id = id;
        this.dineroprestado = dineroprestado;
        this.fsolicitud = fsolicitud;
        this.cantidadcuotas = cantidadcuotas;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getDineroprestado() {
        return dineroprestado;
    }

    public void setDineroprestado(BigDecimal dineroprestado) {
        this.dineroprestado = dineroprestado;
    }

    public Date getFsolicitud() {
        return fsolicitud;
    }

    public void setFsolicitud(Date fsolicitud) {
        this.fsolicitud = fsolicitud;
    }

    public int getCantidadcuotas() {
        return cantidadcuotas;
    }

    public void setCantidadcuotas(int cantidadcuotas) {
        this.cantidadcuotas = cantidadcuotas;
    }

    public BigDecimal getTna() {
        return tna;
    }

    public void setTna(BigDecimal tna) {
        this.tna = tna;
    }

    public BigDecimal getGastosadmin() {
        return gastosadmin;
    }

    public void setGastosadmin(BigDecimal gastosadmin) {
        this.gastosadmin = gastosadmin;
    }

    public BigDecimal getSegvida() {
        return segvida;
    }

    public void setSegvida(BigDecimal segvida) {
        this.segvida = segvida;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Cuotas> getCuotasList() {
        return cuotasList;
    }

    public void setCuotasList(List<Cuotas> cuotasList) {
        this.cuotasList = cuotasList;
    }

    public Clientes getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Clientes idcliente) {
        this.idcliente = idcliente;
    }

    public Usuarios getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Usuarios idoperador) {
        this.idoperador = idoperador;
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
        if (!(object instanceof Prestamos)) {
            return false;
        }
        Prestamos other = (Prestamos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fcastillo.capitulo.rest.entity.Prestamos[ id=" + id + " ]";
    }
    
}
