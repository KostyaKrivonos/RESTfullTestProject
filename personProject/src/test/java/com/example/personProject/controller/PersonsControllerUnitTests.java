package com.example.personProject.controller;

import com.example.personProject.model.Person;
import com.example.personProject.service.PersonService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import static org.mockito.Mockito.*;

import java.util.Arrays;

public class PersonsControllerUnitTests {

	private PersonController controller;

	@Before
	public void setup() throws Exception {
		PersonService personService = mock(PersonService.class);
		controller = new PersonController(personService);

	}

	@Test
	public void testFindOneByQuery() throws Exception {
		PersonService personService = mock(PersonService.class);
		String jsonPerson = String.format("{\"name\": \"Nikolas\",\"phone\": \"+380974585321\"}");
		Person nikolas = new Person();
		nikolas.setName("Nikolas");
		nikolas.setPhone("+380974585321");
		nikolas.setId(new ObjectId());
		when(personService.findOne(eq("Nikolas"), eq("+380974585321"))).thenReturn(nikolas);
		controller.get(nikolas);

	}

	@Test
	public void testFindAll() throws Exception {
		PersonService personService = mock(PersonService.class);
		Person nikolas = new Person();
		nikolas.setName("Nikolas");
		nikolas.setPhone("+380974585321");
		nikolas.setId(new ObjectId());
		Person alex = new Person();
		alex.setName("Alex");
		alex.setPhone("+380974878963");
		alex.setId(new ObjectId());
		Page<Person> page = new PageImpl<>(Arrays.asList(nikolas, alex));
		when(personService.findAll(0)).thenReturn(page);
		controller.get(0);

		//verify(personService, times(1)).findAll(0);
	}

	@Test
	public void testFindById() throws Exception {
		PersonService personService = mock(PersonService.class);
		Person nikolas = new Person();
		nikolas.setId(new ObjectId("507f191e810c19729de860ea"));
		nikolas.setName("Nikolas");
		nikolas.setPhone("+380954789635");
		controller.get(new ObjectId("507f191e810c19729de860ea"));
		when(personService.findById(new ObjectId("507f191e810c19729de860ea"))).thenReturn(nikolas);
		//verify(personService, times(1)).findById(new ObjectId("507f191e810c19729de860ea"));
	}

	@Test
	public void testCreate() throws Exception {
		PersonService personService = mock(PersonService.class);
		Person nikolas = new Person();
		nikolas.setName("Nikolas");
		nikolas.setPhone("+380974585321");
		when(personService.create(nikolas)).thenReturn(true);
	}

	@Test
	public void testDeletePerson() throws Exception {
		PersonService personService = mock(PersonService.class);
		Person nikolas = new Person();
		nikolas.setId(new ObjectId("507f191e810c19729de860ea"));
		nikolas.setName("Nikolas");
		nikolas.setPhone("+380954789635");
		controller.delete(new ObjectId("507f191e810c19729de860ea"));
		when(personService.delete(new ObjectId("507f191e810c19729de860ea"))).thenReturn(true);
	}
}
