/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class AuthorsYear implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_author")
    private Integer idAuthor;
    @Column(name = "name_author")
    private String nameAuthor;
    @Column(name = "year")
    private String year;

    public AuthorsYear() {
    }

    public AuthorsYear(Integer idAuthor, String nameAuthor, String year) {
        this.idAuthor = idAuthor;
        this.nameAuthor = nameAuthor;
        this.year = year;
    }

    public AuthorsYear(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public AuthorsYear(Integer idAuthor, String nameAuthor) {
        this.idAuthor = idAuthor;
        this.nameAuthor = nameAuthor;
    }

    public Integer getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Integer idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuthor != null ? idAuthor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorsYear)) {
            return false;
        }
        AuthorsYear other = (AuthorsYear) object;
        if ((this.idAuthor == null && other.idAuthor != null) || (this.idAuthor != null && !this.idAuthor.equals(other.idAuthor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.AuthorsYears[ idAuthor=" + idAuthor + " ]";
    }

}
