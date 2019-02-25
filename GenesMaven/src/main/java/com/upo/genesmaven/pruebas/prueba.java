package com.upo.genesmaven.pruebas;



import com.upo.genesmaven.genemaniareader.DBase;
import java.io.IOException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author winlatop
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DBase d= new DBase();
        String geneDir="data";
        String geneFile="genes.txt";
        String geneOut = "C:\\Users\\winlatop\\Documents\\NetBeansProjects\\Monica\\Genes\\GenesMaven\\src\\main\\webapp\\" + geneDir + "\\" + geneFile;
        d.importDataUser(geneOut, geneDir + "_" + geneFile, geneFile, "genesUser");
    }
    
}
