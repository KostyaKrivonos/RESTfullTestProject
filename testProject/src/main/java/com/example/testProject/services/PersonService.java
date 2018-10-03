package com.example.testProject.services;

import com.example.testProject.dao.PersonDao;
import com.example.testProject.models.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonService {
    @Autowired
    private PersonDao personDao;

    public void addPerson(Person p){
        personDao.insert(p);
    }

    public void updatePerson(Person p){
        personDao.save(p);
    }

    public Person findById(ObjectId id){
        return personDao.findById(id);
    }

    public void deletePerson(ObjectId id){
        personDao.deleteById(id.toHexString());
    }

    public List<Person> showAll(){
        return personDao.findAll();
    }
}
