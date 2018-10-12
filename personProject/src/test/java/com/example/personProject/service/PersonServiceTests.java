package com.example.personProject.service;

import com.example.personProject.model.Person;
import com.example.personProject.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PersonServiceTests {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @Test
    public void testFindOneByQuery(){
        Person nikolas = new Person("Nikolas", "+380974585321");
        when(personRepository.findOne("Nikolas", "+380974585321")).thenReturn(nikolas);
        Person testPerson = personService.findOne("Nikolas", "+380974585321");
        assertEquals(nikolas.getName(), testPerson.getName());
        assertEquals(nikolas.getPhone(), testPerson.getPhone());
    }

    @Test
    public void testShowAllPersons(){
        Person nikolas = new Person("Nikolas", "+380974585321");
        Person alex = new Person("Alex", "+380974878963");
        when(personRepository.findAll()).thenReturn(Arrays.asList(nikolas, alex));
        List<Person> personList = personService.findAll();
    }


    @Configuration
    static class InnerConfiguration {

        @Bean
        public PersonService getTestService() {
            return new PersonService();
        }

        @Bean
        public PersonRepository getMockPersonService() {
            PersonRepository mockPersonRepository = mock(PersonRepository.class);
            return mockPersonRepository;
        }
    }
}
