/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mónica Sánchez Martín
 */
@Entity
@Table(name = "iterations_species")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IterationsSpecies.findAll", query = "SELECT i FROM IterationsSpecies i")
    , @NamedQuery(name = "IterationsSpecies.findByIdIterationSpecie", query = "SELECT i FROM IterationsSpecies i WHERE i.idIterationSpecie = :idIterationSpecie")
    , @NamedQuery(name = "IterationsSpecies.findByIdIteration", query = "SELECT i FROM IterationsSpecies i WHERE i.idIteration = :idIteration")
    , @NamedQuery(name = "IterationsSpecies.findByIdSpecie", query = "SELECT i FROM IterationsSpecies i WHERE i.idSpecie = :idSpecie")})
public class IterationsSpecies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_iteration_specie")
    private Integer idIterationSpecie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_iteration")
    private int idIteration;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_specie")
    private int idSpecie;

    public IterationsSpecies() {
    }

    public IterationsSpecies(Integer idIterationSpecie) {
        this.idIterationSpecie = idIterationSpecie;
    }

    public IterationsSpecies(Integer idIterationSpecie, int idIteration, int idSpecie) {
        this.idIterationSpecie = idIterationSpecie;
        this.idIteration = idIteration;
        this.idSpecie = idSpecie;
    }

    public Integer getIdIterationSpecie() {
        return idIterationSpecie;
    }

    public void setIdIterationSpecie(Integer idIterationSpecie) {
        this.idIterationSpecie = idIterationSpecie;
    }

    public int getIdIteration() {
        return idIteration;
    }

    public void setIdIteration(int idIteration) {
        this.idIteration = idIteration;
    }

    public int getIdSpecie() {
        return idSpecie;
    }

    public void setIdSpecie(int idSpecie) {
        this.idSpecie = idSpecie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIterationSpecie != null ? idIterationSpecie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IterationsSpecies)) {
            return false;
        }
        IterationsSpecies other = (IterationsSpecies) object;
        if ((this.idIterationSpecie == null && other.idIterationSpecie != null) || (this.idIterationSpecie != null && !this.idIterationSpecie.equals(other.idIterationSpecie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.IterationsSpecies[ idIterationSpecie=" + idIterationSpecie + " ]";
    }
    
}
