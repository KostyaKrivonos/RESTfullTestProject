package com.example.personProject.controller;

import com.example.personProject.validator.ValidationErrorBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.personProject.model.Person;
import com.example.personProject.service.PersonService;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/person")
@ComponentScan
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> get(@RequestBody Person p) {
		// System.out.println(name + phone);
		return ResponseEntity.badRequest().body(personService.findOne(p.getName(), p.getPhone()));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity create(@Valid @RequestBody Person person, Errors errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
		}
		return ResponseEntity.ok(personService.create(person));
	}

	@RequestMapping(value = "/getAll/{page}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public Page<Person> get(@PathVariable("page") int page) {
		return personService.findAll(page);
	}

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> get(@PathVariable("id") ObjectId id) {
		return ResponseEntity.badRequest().body(personService.findById(id));
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable ObjectId id) {
		personService.delete(id);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") ObjectId id, @Valid @RequestBody Person person) {
		personService.update(person);
	}
}