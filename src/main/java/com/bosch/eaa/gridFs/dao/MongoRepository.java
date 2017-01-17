package com.bosch.eaa.gridFs.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class MongoRepository {
	@Autowired
	GridFsTemplate gridFsTemplate;

	public String save(String base64) {

		byte[] byteArray = Base64Utils.decodeFromString(base64);
		InputStream inputStream = new ByteArrayInputStream(byteArray);
		// if we want some meta data we can add.
		DBObject metaData = new BasicDBObject();
		metaData.put("user", "cose");
		String id = gridFsTemplate.store(inputStream, "test.png", "image/png", metaData).getId().toString();
		return id;

	}

	public InputStream getImage(String id) {
		InputStream in = null;
		GridFSDBFile grid = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		if (grid != null) {
			in = grid.getInputStream();
		}

		return in;

	}

}
