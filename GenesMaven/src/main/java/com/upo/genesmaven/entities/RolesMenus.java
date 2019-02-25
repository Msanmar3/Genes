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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mónica Sánchez Martín
 */
@Entity
@Table(name = "roles_menus", catalog = "genevalidator", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_role_menu"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesMenus.findAll", query = "SELECT r FROM RolesMenus r")
    , @NamedQuery(name = "RolesMenus.findByIdRoleMenu", query = "SELECT r FROM RolesMenus r WHERE r.idRoleMenu = :idRoleMenu")
    ,@NamedQuery(name = "RolesMenus.findByIdRole", query = "SELECT r FROM RolesMenus r WHERE r.idRole = :idRole ")
})
public class RolesMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_role_menu", nullable = false)
    private Integer idRoleMenu;
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu", nullable = false)
    @ManyToOne(optional = false)
    private Menus idMenu;
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    @ManyToOne(optional = false)
    private Roles idRole;

    public RolesMenus() {
    }

    public RolesMenus(Integer idRoleMenu) {
        this.idRoleMenu = idRoleMenu;
    }

    public Integer getIdRoleMenu() {
        return idRoleMenu;
    }

    public void setIdRoleMenu(Integer idRoleMenu) {
        this.idRoleMenu = idRoleMenu;
    }

    public Menus getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menus idMenu) {
        this.idMenu = idMenu;
    }

    public Roles getIdRole() {
        return idRole;
    }

    public void setIdRole(Roles idRole) {
        this.idRole = idRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoleMenu != null ? idRoleMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesMenus)) {
            return false;
        }
        RolesMenus other = (RolesMenus) object;
        if ((this.idRoleMenu == null && other.idRoleMenu != null) || (this.idRoleMenu != null && !this.idRoleMenu.equals(other.idRoleMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upo.genesmaven.entities.RolesMenus[ idRoleMenu=" + idRoleMenu + " ]";
    }

}
