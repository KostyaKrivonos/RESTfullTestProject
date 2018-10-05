package com.example.personProject.personsIntegrationTests;

import com.example.personProject.models.Person;
import com.example.personProject.repository.PersonRepository;
import com.example.personProject.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTests {
    private static final Logger LOGGER = LogManager.getLogger(PersonService.class);
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testFundOneByName() {
        // given
        Person alex = new Person("alex", "36363636363");
        personRepository.save(alex);

        // when
        Person found = personRepository.findOneByQuery(alex.getName(), alex.getPhone());

        // then
        if(found.getName().equalsIgnoreCase(alex.getName()) & found.getPhone().equalsIgnoreCase(alex.getPhone())){

        }

    }

}
