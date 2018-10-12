package com.example.personProject.service;

import com.example.personProject.repository.PersonRepository;
import com.example.personProject.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


@Component
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    
    private static final Logger LOGGER = LogManager.getLogger(PersonService.class);

    
    public Person findOne(String name, String phone) {
        LOGGER.info("find one person by name: " + name +  " and phone: " + phone);
        return personRepository.findOne(name, phone);
    }

    public boolean create(Person p) {
        LOGGER.info("led the parameters: name: " + p.getName() + "; phone: " + p.getPhone());
        if (personRepository.findOne(p.getName(), p.getPhone()) == null) {
            personRepository.save(p);
            LOGGER.info("person " + "name: " + p.getName() + "; phone: " + p.getPhone() + " - is add in DB");
            return true;
        }
        LOGGER.info("person name: " + p.getName() + "; phone: " + p.getPhone() + " - not add in DB");
        return false;
    }

    public void update(Person p) {
        LOGGER.info("person id: " + p.getId() + "; name: " + p.getName() + "; phone: " + p.getPhone() + " - updated");
        p.setId(new ObjectId(p.getId()));
        personRepository.save(p);
    }

    public Person findById(ObjectId id) {
        LOGGER.info("find person by id: " + id + " from DB");
        Person person = personRepository.findById(id);
        if (person == null) {
        LOGGER.info("Person by id: " + id + " not found");
        }
        return person;
    }

    public boolean delete(ObjectId id) {
        Person person = personRepository.findById(id);
        if (person != null) {
            LOGGER.info("person id: " + id + ", is delete");
            personRepository.deleteById(person.getId());
            return true;

        } else {
            LOGGER.info("there is no person with this id: " + id);
            return false;
        }
    }

    public Page<Person> findAll(int page) {
        LOGGER.info("Show 3 person on each page from DB ");
        return personRepository.findAll(new PageRequest(page, 3));
    }
}