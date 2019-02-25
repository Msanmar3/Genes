/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.services;

import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.upo.genesmaven.genemaniareader.DBase;
import com.upo.genesmaven.models.Gen;
import com.upo.genesmaven.mongo.GenDao;
import com.upo.genesmaven.mongo.MongoConnection;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.servlet.SparkApplication;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class GenService implements SparkApplication {

    Gson gson = new Gson();
    GenDao dao = null;

    @Override
    public void init() {
        MongoConnection conn = MongoConnection.getInstance();
        dao = new GenDao(conn.getDatastore());

        get("/api/getAll", (req, res) -> {
            return dao.find().asList();
        }, gson::toJson);

        post("/api/insert", (req, res) -> {
            Gen gen = gson.fromJson(req.body(), Gen.class);
            DBObject tmp = conn.getMorphia().toDBObject(gen);

            WriteResult wResult = dao.getCollection().insert(tmp);

            return wResult.getUpsertedId();
        }, gson::toJson);

        after((req, res) -> {
            res.header("content-type", "application/json");
        });
    }

    public void convertToJson() {
//                DBase d= new DBase();
//
//        String geneOut = currentDir + "/" + geneDir + "_" + geneFile;
//        db.importDataMongo(geneOut, geneDir + "_" + geneFile, geneFile);

    }
}
