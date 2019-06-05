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
@Table(name = "nomenclatures")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomenclatures.findAll", query = "SELECT n FROM Nomenclatures n")
    , @NamedQuery(name = "Nomenclatures.findByIdNomenclature", query = "SELECT n FROM Nomenclatures n WHERE n.idNomenclature = :idNomenclature")
    , @NamedQuery(name = "Nomenclatures.findByNameNomenclature", query = "SELECT n FROM Nomenclatures n WHERE n.nameNomenclature = :nameNomenclature")
    , @NamedQuery(name = "Nomenclatures.findByIdSpecie", query = "SELECT n FROM Nomenclatures n WHERE n.idSpecie = :idSpecie")})
public class Nomenclatures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nomenclature")
    private Integer idNomenclature;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name_nomenclature")
    private String nameNomenclature;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_specie")
    private int idSpecie;

    public Nomenclatures() {
    }

    public Nomenclatures(Integer idNomenclature) {
        this.idNomenclature = idNomenclature;
    }

    public Nomenclatures(Integer idNomenclature, String nameNomenclature, int idSpecie) {
        this.idNomenclature = idNomenclature;
        this.nameNomenclature = nameNomenclature;
        this.idSpecie = idSpecie;
    }

    public Integer getIdNomenclature() {
        return idNomenclature;
    }

    public void setIdNomenclature(Integer idNomenclature) {
        this.idNomenclature = idNomenclature;
    }

    public String getNameNomenclature() {
        return nameNomenclature;
    }

    public void setNameNomenclature(String nameNomenclature) {
        this.nameNomenclature = nameNomenclature;
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
        hash += (idNomenclature != null ? idNomenclature.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomenclatures)) {
            return false;
        }
        Nomenclatures other = (Nomenclatures) object;
        if ((this.idNomenclature == null && other.idNomenclature != null) || (this.idNomenclature != null && !this.idNomenclature.equals(other.idNomenclature))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.Nomenclatures[ idNomenclature=" + idNomenclature + " ]";
    }
    
}
