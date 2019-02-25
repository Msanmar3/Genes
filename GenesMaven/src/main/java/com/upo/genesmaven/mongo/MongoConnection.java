/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.mongo;

import static java.lang.String.format;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.upo.genesmaven.models.Gen;


/**
 *
 * @author Mónica Sánchez Martín
 */
public class MongoConnection {

    private static Logger logger = Logger.getLogger(MongoConnection.class);
    private static MongoConnection instance = new MongoConnection();

    private MongoClient mongo = null;
    private Datastore dataStore = null;
    private Morphia morphia = null;
    private String dataBaseName=null;

    private MongoConnection() {
    }

    public MongoClient getMongo() throws RuntimeException {
        if (mongo == null) {
            logger.debug("Starting Mongo");
            MongoClientOptions.Builder options = MongoClientOptions.builder()
                    .connectionsPerHost(4)
                    .maxConnectionIdleTime((60 * 1_000))
                    .maxConnectionLifeTime((120 * 1_000));
            ;

            MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/genesvalidator", options);

            logger.info("About to connect to MongoDB @ " + uri.toString());

            try {
                mongo = new MongoClient(uri);
                mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
            } catch (MongoException ex) {
                logger.error("An error occoured when connecting to MongoDB", ex);
            } catch (Exception ex) {
                logger.error("An error occoured when connecting to MongoDB", ex);
            }

            // To be able to wait for confirmation after writing on the DB
            mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        }

        return mongo;
    }

    public MongoClient getMongo(String dataBaseName) throws RuntimeException {
        if (mongo == null) {
            logger.debug("Starting Mongo");
            MongoClientOptions.Builder options = MongoClientOptions.builder()
                    .connectionsPerHost(4)
                    .maxConnectionIdleTime((60 * 1_000))
                    .maxConnectionLifeTime((120 * 1_000));
            ;

            MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/" + dataBaseName, options);

            logger.info("About to connect to MongoDB @ " + uri.toString());

            try {
                mongo = new MongoClient(uri);
                mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
            } catch (MongoException ex) {
                logger.error("An error occoured when connecting to MongoDB", ex);
            } catch (Exception ex) {
                logger.error("An error occoured when connecting to MongoDB", ex);
            }

            // To be able to wait for confirmation after writing on the DB
            mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        }

        return mongo;
    }
    
    public Morphia getMorphia() {
        if (morphia == null) {
            logger.debug("Starting Morphia");
            morphia = new Morphia();

            logger.debug(format("Mapping packages for clases within %s", Gen.class.getName()));
            morphia.mapPackageFromClass(Gen.class);
        }

        return morphia;
    }

    public Datastore getDatastore() {
        if (dataStore == null) {
            String dbName = "genesvalidator";
            logger.debug(format("Starting DataStore on DB: %s", dbName));
            dataStore = getMorphia().createDatastore(getMongo(), dbName);
        }

        return dataStore;
    }

    public Datastore getDatastore(String dbName) {
        if (dataStore == null) {
            logger.debug(format("Starting DataStore on DB: %s", dbName));
            dataStore = getMorphia().createDatastore(getMongo(), dbName);
        }

        return dataStore;
    }
    
    public void init() {
        logger.debug("Bootstraping");
        getMongo();
        getMorphia();
        getDatastore();
    }

    public void close() {
        logger.info("Closing MongoDB connection");
        if (mongo != null) {
            try {
                mongo.close();
                logger.debug("Nulling the connection dependency objects");
                mongo = null;
                morphia = null;
                dataStore = null;
            } catch (Exception e) {
                logger.error(format("An error occurred when closing the MongoDB connection\n%s", e.getMessage()));
            }
        } else {
            logger.warn("mongo object was null, wouldn't close connection");
        }
    }

    public static MongoConnection getInstance() {
        return instance;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    
}
