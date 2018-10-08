package com.example.personProject.services;

import com.example.personProject.repositorys.PersonRepository;
import com.example.personProject.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    private static final Logger LOGGER = LogManager.getLogger(PersonService.class);

    public Person findOne(String name, String phone) {
        LOGGER.info("find one person by name and phone");
        return personRepository.findOne(name, phone);
    }

    public boolean create(Person p) {
        LOGGER.info("Method add person in DB");
        if (personRepository.findOne(p.getName(), p.getPhone()) == null) {
            personRepository.save(p);
            LOGGER.info("person is add in DB");
            return true;
        }
        LOGGER.warn("Not add person");
        return false;
    }

    public void update(Person p) {
        LOGGER.info("person update");
        personRepository.save(p);
    }

    public Person findById(ObjectId id) {
        LOGGER.info("Method findById of DB");
        Person person = personRepository.findById(id);
        if (person == null) {
            LOGGER.warn("Person not found");
        }
        return person;
    }

    public boolean deleteById(ObjectId id) {
        Person person = personRepository.findById(id);
        if (person != null) {
            LOGGER.info("person is delete");
            personRepository.deleteById(person.getId());

        } else {
            LOGGER.warn("there is no person with this id!");
        }
        return true;
    }

    public List<Person> findAll() {
        LOGGER.info("Show all person from DB");
        return personRepository.findAll();
    }
}
