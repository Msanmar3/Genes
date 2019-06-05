/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.genemaniareader;

import com.upo.genesmaven.entities.Users;
import com.upo.genesmaven.models.Gen;
import com.upo.genesmaven.models.Identifier;
import com.upo.genesmaven.mongo.GenDao;
import com.upo.genesmaven.mongo.IdentifierDao;
import com.upo.genesmaven.mongo.MongoConnection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.UUID;
import org.bson.Document;

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
    //Mónica
    public void importDataMongoIdentifier(String path, String specie) throws FileNotFoundException, IOException {
        MongoConnection conn = MongoConnection.getInstance();
        IdentifierDao gd = new IdentifierDao(conn.getDatastore());
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\t";
        br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] genSTR = line.split(cvsSplitBy);
            Identifier g = new Identifier(specie, genSTR[0], genSTR[1], genSTR[2]);
            System.out.println("GGG:" + g);
            gd.save(g);

        }

    }

    public void importDataUser(String path, String origen, String filename, String dataBaseName, Users user) throws FileNotFoundException, IOException {
        MongoConnection conn = MongoConnection.getInstance();
        UUID uuid = UUID.randomUUID();

        String nameCollection = "Gen" + user.getIdUser() + "_" + uuid.toString();
        //Crear la coleccion
        conn.createCollection(dataBaseName, nameCollection);
        //  conn.getMongo(dataBaseName);
        //Esta parte no funciona porque no seleccionamos bien la colleccion
        // GenDao gd = new GenDao(conn.getDatastore(dataBaseName+"."+"Gen" + user.getIdUser()));

        //Mirar en el dataclient
        //Seleccionar la coleccion
        String csvFile = path;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = "\t";
        br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] genSTR = line.split(cvsSplitBy);
            System.out.println("Entra en el while: " + line);
            //Gen g = new Gen(origen, filename, genSTR[0], genSTR[1], Double.parseDouble(genSTR[2]));
            Document documentGen = new Document()
                    .append("className", "es.upo.models.Gen")
                    .append("specie", origen)
                    .append("firstName", filename)
                    .append("secondName", filename)
                    .append("geneA", genSTR[0])
                    .append("geneB", genSTR[1])
                    .append("weight", Double.parseDouble(genSTR[2]));
            System.out.println("DocumentGen:" + documentGen);
            //gd.save(g);
            conn.insertCollection(documentGen, nameCollection, dataBaseName);
        }

        conn.close();

    }
};
