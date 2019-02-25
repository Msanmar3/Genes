/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mónica Sánchez Martín
 */
@Entity
@Table(name = "files", catalog = "genevalidator", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_file"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Files.findAll", query = "SELECT f FROM Files f")
    , @NamedQuery(name = "Files.findByIdFile", query = "SELECT f FROM Files f WHERE f.idFile = :idFile")
    , @NamedQuery(name = "Files.findByNameFile", query = "SELECT f FROM Files f WHERE f.nameFile = :nameFile")
    , @NamedQuery(name = "Files.findByIsHead", query = "SELECT f FROM Files f WHERE f.isHead = :isHead")
    , @NamedQuery(name = "Files.findByUrl", query = "SELECT f FROM Files f WHERE f.url = :url")
    , @NamedQuery(name = "Files.findByCreated", query = "SELECT f FROM Files f WHERE f.created = :created")})
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_file", nullable = false)
    private Integer idFile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name_file", nullable = false, length = 255)
    private String nameFile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isHead", nullable = false)
    private boolean isHead;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url", nullable = false, length = 255)
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @ManyToOne(optional = false)
    private Users idUser;

    public Files() {
    }

    public Files(Integer idFile) {
        this.idFile = idFile;
    }

    public Files(Integer idFile, String nameFile, boolean isHead, String url, Date created) {
        this.idFile = idFile;
        this.nameFile = nameFile;
        this.isHead = isHead;
        this.url = url;
        this.created = created;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public boolean getIsHead() {
        return isHead;
    }

    public void setIsHead(boolean isHead) {
        this.isHead = isHead;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFile != null ? idFile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Files)) {
            return false;
        }
        Files other = (Files) object;
        if ((this.idFile == null && other.idFile != null) || (this.idFile != null && !this.idFile.equals(other.idFile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.Files[ idFile=" + idFile + " ]";
    }
    
}
