/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.genemaniareader;


import com.upo.genesmaven.models.Gen;
import com.upo.genesmaven.mongo.GenDao;
import com.upo.genesmaven.mongo.MongoConnection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class DBase {

    public DBase() {
    }

    public Connection connect(String db_connect_str,
            String db_userid, String db_password) {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection(db_connect_str,
                    db_userid, db_password);

        } catch (Exception e) {
            e.printStackTrace();
            conn = null;
        }

        return conn;
    }

    public void importData(Connection conn, String path, String dirfilename, String filename) {
        Statement stmt;
        String query;

        try {
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            query = "LOAD DATA LOCAL INFILE '" + path + "' INTO TABLE genes "
                    + "FIELDS TERMINATED BY '\t' "
                    // + "LINES TERMINATED BY '\\n' "
                    + "IGNORE 1 LINES "
                    + "( @Gene_A, @Gene_B, @Weight) "
                    + "SET origen='" + dirfilename + "',"
                    + "fichero='" + filename + "',"
                    + "Gene_A=@Gene_A,"
                    + "Gene_B=@Gene_B,"
                    + "Weight=@Weight,"
                    + "fecha=NOW()";

            System.out.println(query);

            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
            stmt = null;
        }
    }

    public void importDataMongo(String path, String origen, String filename) throws FileNotFoundException, IOException {
        MongoConnection conn = MongoConnection.getInstance();
        GenDao gd = new GenDao(conn.getDatastore());
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\t";
        br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] genSTR = line.split(cvsSplitBy);
            Gen g = new Gen(origen, filename, genSTR[0], genSTR[1], Double.parseDouble(genSTR[2]));
            gd.save(g);

        }

    }
    
    public void importDataUser(String path, String origen, String filename, String dataBaseName) throws FileNotFoundException, IOException {
        MongoConnection conn = MongoConnection.getInstance();
        GenDao gd = new GenDao(conn.getDatastore(dataBaseName));
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\t";
        br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] genSTR = line.split(cvsSplitBy);
            Gen g = new Gen(origen, filename, genSTR[0], genSTR[1], Double.parseDouble(genSTR[2]));
            gd.save(g);

        }

    }
};
