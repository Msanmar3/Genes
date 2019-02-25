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
public class Gen extends BaseMongoDO {
    
    String origin;
    String file;
    String geneA;
    String geneB;
    Double weight;

    public Gen() {
        super();
    }

    public Gen(String origin, String file, String geneA, String geneB, Double weight) {
        super();
        this.origin = origin;
        this.file = file;
        this.geneA = geneA;
        this.geneB = geneB;
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getGeneA() {
        return geneA;
    }

    public void setGeneA(String geneA) {
        this.geneA = geneA;
    }

    public String getGeneB() {
        return geneB;
    }

    public void setGeneB(String geneB) {
        this.geneB = geneB;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.origin);
        hash = 59 * hash + Objects.hashCode(this.file);
        hash = 59 * hash + Objects.hashCode(this.geneA);
        hash = 59 * hash + Objects.hashCode(this.geneB);
        hash = 59 * hash + Objects.hashCode(this.weight);
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
        final Gen other = (Gen) obj;
        if (!Objects.equals(this.origin, other.origin)) {
            return false;
        }
        if (!Objects.equals(this.file, other.file)) {
            return false;
        }
        if (!Objects.equals(this.geneA, other.geneA)) {
            return false;
        }
        if (!Objects.equals(this.geneB, other.geneB)) {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gen{" + "origin=" + origin + ", file=" + file + ", geneA=" + geneA + ", geneB=" + geneB + ", weight=" + weight + '}';
    }
    
    
    
}
