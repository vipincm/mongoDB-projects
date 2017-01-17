package com.bosch.eaa.gridFs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class GridFsTemplateConfig extends AbstractMongoConfiguration {
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
	    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}
	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "test";
	}

	@Override
	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
		 return new MongoClient("127.0.0.1");
	}

}
