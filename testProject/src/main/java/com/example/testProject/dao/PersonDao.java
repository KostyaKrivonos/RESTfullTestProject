package com.example.testProject.dao;

import com.example.testProject.models.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PersonDao extends MongoRepository<Person, String> {
  Person findById (ObjectId id);
}

