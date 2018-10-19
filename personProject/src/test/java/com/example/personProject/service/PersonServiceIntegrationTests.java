package com.example.personProject.service;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodProcess;

public class PersonServiceIntegrationTests {
	private static final String LOCALHOST = "127.0.0.1";
    private static final String DB_NAME = "itest";
    private static final int MONGO_TEST_PORT = 27028;
    
    private PersonService personService;

    private static MongodProcess mongoProcess;
    private static Mongo mongo;
    
    private MongoTemplate template;
    
}
