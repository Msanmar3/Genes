package com.upo.genesmaven.mongo;

import com.upo.genesmaven.models.CupDO;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;



public class CupDao extends BasicDAO<CupDO, ObjectId> {

	public CupDao(Datastore ds) {
		super(ds);
	}

}
