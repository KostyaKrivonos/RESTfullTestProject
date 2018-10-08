package com.example.personProject.controllers;

import com.example.personProject.dto.ValidationErrorBuilder;
import com.example.personProject.models.Person;
import com.example.personProject.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    private static final Logger LOGGER = LogManager.getLogger(PersonController.class);

    @RequestMapping(value = "/findOne/{name}/{phone}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Person findByName(@PathVariable("name") String name, @PathVariable("phone") String phone) {
        return personService.findOne(name, phone);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addPerson(@Valid @RequestBody Person person, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        return ResponseEntity.ok(personService.create(person));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<Person> showAllPersons() {
        return personService.findAll();
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Person findPersonById(@PathVariable("id") ObjectId id) {

        return personService.findById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable ObjectId id) {
        personService.deleteById(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Person person) {
        person.setId(id);
        personService.update(person);
    }
}
