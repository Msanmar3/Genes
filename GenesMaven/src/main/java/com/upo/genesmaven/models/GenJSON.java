/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.models;

import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class GenJSON {

    private Gen gen;

    private Gson gson;

    public GenJSON() {
        this.gen = new Gen();
        this.gson = new Gson();
    }

    public String convertToJSON(Gen gen) {
        return this.gson.toJson(gen, Gen.class);
    }

    public Gen convertToGen(String obJson) {
        return this.gen = this.gson.fromJson(obJson, Gen.class);
    }
    
    public String convertListToJSON(List<Gen> genes) {
        return this.gson.toJson(genes, Gen.class);
    }

    public List<Gen> convertListToGen(String obJson) {
        return (List<Gen>)this.gson.fromJson(obJson, Gen.class);
    }
}
