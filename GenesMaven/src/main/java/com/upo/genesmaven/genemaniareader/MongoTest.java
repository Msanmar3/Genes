/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.genemaniareader;

import com.upo.genesmaven.models.Gen;
import com.upo.genesmaven.mongo.GenDao;
import com.upo.genesmaven.mongo.MongoConnection;



/**
 *
 * @author Mónica Sánchez Martín
 */
public class MongoTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MongoConnection conn = MongoConnection.getInstance();
        GenDao gd = new GenDao(conn.getDatastore());

        for (int i = 0; i < 10; i++) {
            Gen g = new Gen("origin" + i, "file" + i, "genA" + i, "genB" + 1, 1.0 + i);
            System.out.println(g);
            gd.save(g);
        }

    }

}
