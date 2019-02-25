package com.upo.genesmaven.services;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.upo.genesmaven.models.CupDO;
import com.upo.genesmaven.mongo.CupDao;
import com.upo.genesmaven.mongo.MongoConnection;



import spark.servlet.SparkApplication;

public class CupService implements SparkApplication {

	Gson gson = new Gson();
	CupDao dao = null;
	
	@Override
	public void init() {
		MongoConnection conn = MongoConnection.getInstance();
		dao = new CupDao(conn.getDatastore());
		
		get("/api/getAll", (req, res) -> {
			return dao.find().asList();
		}, gson::toJson);
		
		post("/api/insert", (req, res) -> {			
			CupDO cup = gson.fromJson(req.body(), CupDO.class);
			DBObject tmp = conn.getMorphia().toDBObject(cup);
			
			WriteResult wResult = dao.getCollection().insert(tmp);

			return wResult.getUpsertedId();
		}, gson::toJson);
		
		after((req, res) -> {
			res.header("content-type", "application/json");
		});
	}

}
