package com.example.personProject.repository;

import com.example.personProject.model.Person;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends MongoRepository<Person, String>{
  Person findById (ObjectId id);
  
  @Query("{ 'name' : ?0, 'phone' : ?1 }")
  Person findOne(String name, String phone);
  
}