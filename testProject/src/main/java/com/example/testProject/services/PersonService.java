package com.example.testProject.services;

import com.example.testProject.dao.PersonRepository;
import com.example.testProject.models.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findByName(String name){
        return personRepository.findByName(name);
    }
    public void addPerson(Person p) {
        /*if (!p.getName().equals("") | !p.getName().equals(null) | !p.getPhone().equals("") | !p.getPhone().equals(null)) {
            p.setId(ObjectId.get());
            personRepository.save(p);
        }

        else{
            System.out.println("error!");
        }*/
        int count = 0;
        List<Person>personList = personRepository.findByName(p.getName());
        for(Person person: personList){
            if(personList.size() == 0){
                personRepository.save(p);
            }
            if(p.getPhone().equalsIgnoreCase(person.getPhone())) {
                count++;
            }
        }
        if(count == 0){
            personRepository.save(p);
        }
        else{System.out.println("such person already exists");}
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
