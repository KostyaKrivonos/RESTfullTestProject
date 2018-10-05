package com.example.personProject.services;

import com.example.personProject.controllers.PersonController;
import com.example.personProject.repository.PersonRepository;
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

    public Person findOneByQuery(String name, String phone){
        LOGGER.info("find one person by name and phone");
        return personRepository.findOneByQuery(name, phone);
    }

    public boolean addPerson(Person p) {
        LOGGER.info("Method add person in DB");
        if(personRepository.findOneByQuery(p.getName(), p.getPhone()) == null){

            personRepository.save(p);
            LOGGER.info("person is add in DB");
            return true;
        }
        LOGGER.warn("Not add person");
        return false ;
    }

    public void updatePerson(Person p) {
        LOGGER.info("person update");
        personRepository.save(p);
    }

    public Person findPersonById(ObjectId id) {
        LOGGER.info("Method findPersonById of DB");
        Person person = personRepository.findById(id);
        if (person == null) {
            LOGGER.warn("Person not found");
        }
        return  person;
    }

    public boolean deletePerson(ObjectId id) {
        Person person = personRepository.findById(id);
        if (person != null) {
            LOGGER.info("person is delete");
            personRepository.deleteById(person.getId());

        } else {
            LOGGER.warn("there is no person with this id!");
        }
        return true;
    }

    public List<Person> showAllPersons() {
        LOGGER.info("Show all person from DB");
        return personRepository.findAll();
    }
}
