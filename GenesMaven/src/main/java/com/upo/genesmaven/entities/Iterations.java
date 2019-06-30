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
@Table(name = "iterations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Iterations.findAll", query = "SELECT i FROM Iterations i")
    , @NamedQuery(name = "Iterations.findByIdIteration", query = "SELECT i FROM Iterations i WHERE i.idIteration = :idIteration")
    , @NamedQuery(name = "Iterations.findByNameIteration", query = "SELECT i FROM Iterations i WHERE i.nameIteration = :nameIteration")
    , @NamedQuery(name = "Iterations.findByYear", query = "SELECT i FROM Iterations i WHERE i.year = :year")
    , @NamedQuery(name = "Iterations.findByIdAuthor", query = "SELECT i FROM Iterations i WHERE i.idAuthor = :idAuthor")
    , @NamedQuery(name = "Iterations.findByIdSpecie", query = "SELECT i FROM Iterations i, IterationsSpecies ie WHERE i.idIteration = ie.idIteration AND ie.idSpecie = :idSpecie")}
)
public class Iterations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_iteration")
    private Integer idIteration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name_iteration")
    private String nameIteration;
    @Column(name = "year")
    private String year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_author")
    private int idAuthor;

    public Iterations() {
    }

    public Iterations(Integer idIteration) {
        this.idIteration = idIteration;
    }

    public Iterations(Integer idIteration, String nameIteration, int idAuthor) {
        this.idIteration = idIteration;
        this.nameIteration = nameIteration;
        this.idAuthor = idAuthor;
    }

    public Integer getIdIteration() {
        return idIteration;
    }

    public void setIdIteration(Integer idIteration) {
        this.idIteration = idIteration;
    }

    public String getNameIteration() {
        return nameIteration;
    }

    public void setNameIteration(String nameIteration) {
        this.nameIteration = nameIteration;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIteration != null ? idIteration.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Iterations)) {
            return false;
        }
        Iterations other = (Iterations) object;
        if ((this.idIteration == null && other.idIteration != null) || (this.idIteration != null && !this.idIteration.equals(other.idIteration))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.Iterations[ idIteration=" + idIteration + " ]";
    }
    
}
