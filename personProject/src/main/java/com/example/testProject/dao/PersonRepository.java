package com.example.testProject.dao;

import com.example.testProject.models.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonRepository extends MongoRepository<Person, String> {
  Person findById (ObjectId id);
  List<Person> findByName(String name);
}

