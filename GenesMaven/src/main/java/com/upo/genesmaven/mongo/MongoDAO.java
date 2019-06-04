/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.mongo;

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;

/**
 *
 * @author winlatop
 */
public class MongoDAO {

    private static Logger logger = Logger.getLogger(MongoConnection.class);
    private static MongoConnection instance = MongoConnection.getInstance();

    private MongoClient mongo = null;
    private Datastore dataStore = null;
    private String dataBaseName = null;

    public MongoDAO() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        MongoDAO.logger = logger;
    }

    public static MongoConnection getInstance() {
        return instance;
    }

    public static void setInstance(MongoConnection instance) {
        MongoDAO.instance = instance;
    }

    public MongoClient getMongo() {
        return mongo;
    }

    public void setMongo(MongoClient mongo) {
        this.mongo = mongo;
    }

    public Datastore getDataStore() {
        return dataStore;
    }

    public void setDataStore(Datastore dataStore) {
        this.dataStore = dataStore;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
    
    
    
    

}
