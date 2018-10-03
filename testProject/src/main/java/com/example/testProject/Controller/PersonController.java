package com.example.testProject.Controller;

import com.example.testProject.models.Person;
import com.example.testProject.services.PersonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Person addPerson(@Valid @RequestBody Person person) {

        personService.addPerson(person);
        return person;
    }

    @RequestMapping(value = "/showAllPersons", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<Person> showAllPersons() {
        return personService.showAllPersons();
    }

    @RequestMapping(value = "/findPerson/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Person findPersonById(@PathVariable("id") ObjectId id) {
        return personService.findPersonById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable ObjectId id) {
        personService.deletePerson(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Person person) {
        person.setId(id);
        personService.updatePerson(person);
    }
}
