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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mónica Sánchez Martín
 */
@Entity
@Table(name = "species")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Species.findAll", query = "SELECT s FROM Species s")
    , @NamedQuery(name = "Species.findByIdSpecie", query = "SELECT s FROM Species s WHERE s.idSpecie = :idSpecie")
    , @NamedQuery(name = "Species.findByNameSpecie", query = "SELECT s FROM Species s WHERE s.nameSpecie = :nameSpecie")})
public class Species implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_specie")
    private Integer idSpecie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name_specie")
    private String nameSpecie;

    public Species() {
    }

    public Species(Integer idSpecie) {
        this.idSpecie = idSpecie;
    }

    public Species(Integer idSpecie, String nameSpecie) {
        this.idSpecie = idSpecie;
        this.nameSpecie = nameSpecie;
    }

    public Integer getIdSpecie() {
        return idSpecie;
    }

    public void setIdSpecie(Integer idSpecie) {
        this.idSpecie = idSpecie;
    }

    public String getNameSpecie() {
        return nameSpecie;
    }

    public void setNameSpecie(String nameSpecie) {
        this.nameSpecie = nameSpecie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpecie != null ? idSpecie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Species)) {
            return false;
        }
        Species other = (Species) object;
        if ((this.idSpecie == null && other.idSpecie != null) || (this.idSpecie != null && !this.idSpecie.equals(other.idSpecie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.Species[ idSpecie=" + idSpecie + " ]";
    }
    
}
