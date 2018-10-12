package com.example.personProject.controller;

import com.example.personProject.model.Person;
import com.example.personProject.repository.PersonRepository;
import com.example.personProject.service.PersonService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PersonsControllerUnitTests.Context.class)
public class PersonsControllerUnitTests {

    @Autowired
    private PersonController controller;

    private MockMvc mockMvc;
    @Autowired
    private PersonService personService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testFindOneByQuery() throws Exception {
        String jsonPerson = String.format("{\"name\": \"Nikolas\",\"phone\": \"+380974585321\"}");
        Person nikolas = new Person("Nikolas", "+380974585321");
        nikolas.setId(new ObjectId());
        when(personService.findOne(eq("Nikolas"), eq("+380974585321"))).thenReturn(nikolas);

        mockMvc.perform(get("/person/get/Nikolas/+380974585321")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPerson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonPerson));
    }

    @Test
    public void testFindAll() throws Exception {
        Person nikolas = new Person("Nikolas", "+380974585321");
        nikolas.setId(new ObjectId());
        Person alex = new Person("Alex", "+380974878963");
        alex.setId(new ObjectId());

        when(personService.findAll()).thenReturn(Arrays.asList(nikolas, alex));

        mockMvc.perform(get("/person/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(nikolas.getId())))
                .andExpect(jsonPath("$[0].name", is("Nikolas")))
                .andExpect(jsonPath("$[0].phone", is("+380974585321")))
                .andExpect(jsonPath("$[1].id", is(alex.getId())))
                .andExpect(jsonPath("$[1].name", is("Alex")))
                .andExpect(jsonPath("$[1].phone", is("+380974878963")));

        verify(personService, times(1)).findAll();
       // verifyNoMoreInteractions(personService);
    }

    @Test
    public void testFindById() throws  Exception{
        Person nikolas = new Person();
        nikolas.setId(new ObjectId("507f191e810c19729de860ea"));
        nikolas.setName("Nikolas");
        nikolas.setPhone("+380954789635");

        when(personService.findById(new ObjectId("507f191e810c19729de860ea"))).thenReturn(nikolas);

        mockMvc.perform(get("/person/get/507f191e810c19729de860ea"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is("507f191e810c19729de860ea")))
                .andExpect(jsonPath("$.name", is("Nikolas")))
                .andExpect(jsonPath("$.phone", is("+380954789635")));

        verify(personService, times(1))
                .findById(new ObjectId("507f191e810c19729de860ea"));
        //verifyNoMoreInteractions(personService);
    }


    @Test
    public void testAddPerson() throws Exception{
        String jsonPerson = String.format("{\"name\": \"Nikolas\",\"phone\": \"+380954789635\"}");

       // when(personService.create(nikolas)).thenReturn(response);
        mockMvc.perform(post("/person/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPerson))
                .andExpect(status().isOk());

        verify(personService, times(1)).create(any());

    }
     @Test
     public void testDeletePerson() throws Exception{
         Person nikolas = new Person();
         nikolas.setId(new ObjectId("507f191e810c19729de860ea"));
         nikolas.setName("Nikolas");
         nikolas.setPhone("+380954789635");

         when(personService.delete(new ObjectId("507f191e810c19729de860ea"))).thenReturn(true);

         this.mockMvc.perform(MockMvcRequestBuilders
                 .delete("/person/delete/507f191e810c19729de860ea")
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     }


    @Configuration
    static class Context {

        @Bean
        public PersonController getTestService() {
            return new PersonController();
        }

        @Bean
        public PersonService getMockPersonService() {
            PersonService mockPersonService = mock(PersonService.class);
            return mockPersonService;
        }

        @Bean
        public PersonRepository getMockPersonRepository(){
            PersonRepository mockPersonRepository = mock(PersonRepository.class);
                    return mockPersonRepository;
        }
    }
}
