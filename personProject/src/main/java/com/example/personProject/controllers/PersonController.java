package com.example.personProject.controllers;

import com.example.personProject.dto.ValidationErrorBuilder;
import com.example.personProject.models.Person;
import com.example.personProject.services.PersonService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

   /* @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<Person> findByName(@PathVariable("name") String name) {
        return personService.;
    }*/

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addPerson(@Valid @RequestBody Person person, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @RequestMapping(value = "/showAllPersons", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<Person> showAllPersons() {
        LOGGER.error("error");
        LOGGER.info("info");
        LOGGER.warn("warning");
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
