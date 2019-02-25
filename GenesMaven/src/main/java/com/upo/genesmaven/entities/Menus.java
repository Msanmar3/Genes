/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mónica Sánchez Martín
 */
@Entity
@Table(name = "menus", catalog = "genevalidator", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_menu"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menus.findAll", query = "SELECT m FROM Menus m")
    , @NamedQuery(name = "Menus.findByIdMenu", query = "SELECT m FROM Menus m WHERE m.idMenu = :idMenu")
    , @NamedQuery(name = "Menus.findByIdParent", query = "SELECT m FROM Menus m WHERE m.idParent = :idParent")
    , @NamedQuery(name = "Menus.findByNameMenu", query = "SELECT m FROM Menus m WHERE m.nameMenu = :nameMenu")
    , @NamedQuery(name = "Menus.findByIcon", query = "SELECT m FROM Menus m WHERE m.icon = :icon")
    , @NamedQuery(name = "Menus.findByPosition", query = "SELECT m FROM Menus m WHERE m.position = :position")
    , @NamedQuery(name = "Menus.findByHasChilds", query = "SELECT m FROM Menus m WHERE m.hasChilds = :hasChilds")
    , @NamedQuery(name = "Menus.findByAction", query = "SELECT m FROM Menus m WHERE m.action = :action")})
public class Menus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu", nullable = false)
    private Integer idMenu;
    @Column(name = "id_parent")
    private Integer idParent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name_menu", nullable = false, length = 45)
    private String nameMenu;
    @Size(max = 45)
    @Column(name = "icon", length = 45)
    private String icon;
    @Column(name = "position")
    private Integer position;
    @Column(name = "hasChilds")
    private Boolean hasChilds;
    @Size(max = 255)
    @Column(name = "action", length = 255)
    private String action;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu")
    private List<RolesMenus> rolesMenusList;

    public Menus() {
    }

    public Menus(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Menus(Integer idMenu, String nameMenu) {
        this.idMenu = idMenu;
        this.nameMenu = nameMenu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getHasChilds() {
        return hasChilds;
    }

    public void setHasChilds(Boolean hasChilds) {
        this.hasChilds = hasChilds;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @XmlTransient
    public List<RolesMenus> getRolesMenusList() {
        return rolesMenusList;
    }

    public void setRolesMenusList(List<RolesMenus> rolesMenusList) {
        this.rolesMenusList = rolesMenusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menus)) {
            return false;
        }
        Menus other = (Menus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.Menus[ idMenu=" + idMenu + " ]";
    }
    
}
