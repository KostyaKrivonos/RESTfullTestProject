package com.example.testProject.services;

import com.example.testProject.dao.PersonDao;
import com.example.testProject.models.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonService {
    @Autowired
    private PersonDao personDao;


    public void addPerson(Person p) {
        if (!p.getName().equals("") | !p.getName().equals(null) | !p.getPhone().equals("") | !p.getPhone().equals(null)) {
            p.setId(ObjectId.get());
            personDao.save(p);
        }
        else{
            System.out.println("error!");
        }
    }

    public void updatePerson(Person p) {
        personDao.save(p);
    }

    public Person findPersonById(ObjectId id) {
        Person person = personDao.findById(id);
        if (person == null) {
            System.out.println("error!Not find person!");
        }
        return person;
    }

    public void deletePerson(ObjectId id) {
        Person person = personDao.findById(id);
        if (person != null) {
            personDao.deleteById(person.getId());
        } else {
            System.out.println("error!there is no person with this id!");
        }
    }

    public List<Person> showAllPersons() {
        return personDao.findAll();
    }
}
