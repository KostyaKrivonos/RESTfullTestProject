package com.example.personProject.services;

import com.example.personProject.repository.PersonRepository;
import com.example.personProject.models.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PersonService {
    @Autowired
    private PersonRepository personRepository;



    public Person findByName(String name, String phone){
        return personRepository.findOneByQuery(name, phone);
    }

    public String addPerson(Person p) {
        String add = "add good";
        String worning = "worning";
        if(personRepository.findOneByQuery(p.getName(), String.valueOf(p.getPhone())) == null){
            personRepository.save(p);
            return add;
        }else{
            return worning;
        }
    }

    public void updatePerson(Person p) {
        personRepository.save(p);
    }

    public Person findPersonById(ObjectId id) {
        Person person = personRepository.findById(id);
        if (person == null) {
            System.out.println("error!Not find person!");
        }
        return person;
    }

    public void deletePerson(ObjectId id) {
        Person person = personRepository.findById(id);
        if (person != null) {
            personRepository.deleteById(person.getId());
        } else {
            System.out.println("error!there is no person with this id!");
        }
    }

    public List<Person> showAllPersons() {
        return personRepository.findAll();
    }
}
