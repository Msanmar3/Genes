/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.genemaniareader;

import java.io.IOException;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class Reader {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
           //     new BasicWebCrawler().getPageLinks("http://genemania.org/data/current/");
       //new WebCrawlerWithDepth().getPageLinks("http://genemania.org/data/current/", 0,1,"");
       //Generamos una colección con los datos de todos los identifier_mappins
       //Mónica
       new WebCrawlerWithDepth().getPageLinksIdentifier("http://genemania.org/data/current/", 0,1,"");

    }

}
