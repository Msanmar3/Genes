/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.models;

import java.util.Objects;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class Identifier extends BaseMongoDO {

    String specie;
    String preferredName;
    String name;
    String source;

    public Identifier() {
        super();
    }

    public Identifier(String specie, String preferredName, String name, String source) {
        this.specie = specie;
        this.preferredName = preferredName;
        this.name = name;
        this.source = source;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.specie);
        hash = 29 * hash + Objects.hashCode(this.preferredName);
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.source);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Identifier other = (Identifier) obj;
        if (!Objects.equals(this.specie, other.specie)) {
            return false;
        }
        if (!Objects.equals(this.preferredName, other.preferredName)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Identifier{" + "specie=" + specie + ", preferredName=" + preferredName + ", name=" + name + ", source=" + source + '}';
    }

}
