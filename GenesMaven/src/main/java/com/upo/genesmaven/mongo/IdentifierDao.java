/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.mongo;

/**
 *
 * @author Mónica Sánchez Martín
 */
import com.upo.genesmaven.models.Identifier;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class IdentifierDao extends BasicDAO<Identifier, ObjectId> {

    public IdentifierDao(Datastore ds) {
        super(ds);
    }

}
